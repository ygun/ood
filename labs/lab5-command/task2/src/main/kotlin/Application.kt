import command.ICommand
import document.IDocument
import history.CommandHistory
import history.IHistory
import resource.FileRepository
import store.IStore
import util.CommandType
import util.CommandType.*
import java.io.InputStream
import java.io.PrintStream
import java.nio.file.Path
import java.util.*

class Application(
    private val document: IDocument,
    private val fileRepository: FileRepository,
    private val history: IHistory = CommandHistory(),
    private val store: IStore<CommandType, ICommand> = createCommandStore()
) {

    companion object {
        const val EXIT = "Exit"
    }

    fun run(input: InputStream = System.`in`, output: PrintStream = System.out) {
        output.println("Run, for exit type: Exit\n")

        val sc = Scanner(input)
        var line: String
        while (sc.hasNextLine()) {
            try {
                line = sc.nextLine()
                if (line == EXIT) {
                    fileRepository.deleteFolder()
                    break
                }

                val args = line.split(" ")
                processUserCommand(args[0], args, output)
                output.println("Done\n")
            } catch (e: Exception) {
                output.println(e.message + "\n")
            }
        }
    }

    private fun processUserCommand(userCommand: String, args: List<String>, output: PrintStream) {
        when (CommandType.getCommand(userCommand)) {
            INSERT_PARAGRAPH -> processInsertParagraph(args)
            INSERT_IMAGE -> processInsertImage(args)
            SET_TITLE -> processSetTitle(args)
            LIST -> processList(output)
            REPLACE_TEXT -> processReplaceText(args)
            RESIZE_IMAGE -> processResizeImage(args)
            DELETE_ITEM -> processDeleteItem(args)
            HELP -> processHelp(output)
            UNDO -> processUndo()
            REDO -> processRedo()
            SAVE -> processSave(args)
            BEGIN_MACROS -> processCreteMacros(args)
        }
    }

    private fun processCreteMacros(args: List<String>) {
        ensureCountArgsCorrect(args, 1, BEGIN_MACROS)

        val commandName = args[1]
        println(commandName)
    }

    private fun processSave(args: List<String>) {
        ensureCountArgsCorrect(args, 1, SAVE)

        val commandClass = store.get(SAVE)
        val commandInstance = commandClass.call(Path.of(args[1]))
        commandInstance.execute(document)
    }

    private fun processRedo() = document.redo()

    private fun processUndo() = document.undo()

    private fun processHelp(output: PrintStream) {
        output.println("List of all commands and their arguments:")
        CommandType.values().forEach {
            output.println("\t" + it.getCommandWithArgs())
        }
    }

    private fun processDeleteItem(args: List<String>) {
        ensureCountArgsCorrect(args, 2, DELETE_ITEM)

        val commandClass = store.get(DELETE_ITEM)
        val position = args[1].toInt()
        val commandInstance = commandClass.call(document.getItem(position), position)
        history.addAndExecuteCommand(commandInstance, document)
    }

    private fun processResizeImage(args: List<String>) {
        ensureCountArgsCorrect(args, 4, RESIZE_IMAGE)

        val commandClass = store.get(RESIZE_IMAGE)
        val position = args[1].toInt()
        val image = document.getItem(position).image
        val commandInstance = commandClass.call(image, args[2].toInt(), args[3].toInt())
        history.addAndExecuteCommand(commandInstance, document)
    }

    private fun processReplaceText(args: List<String>) {
        ensureCountArgsCorrect(args, 3, REPLACE_TEXT)

        val commandClass = store.get(REPLACE_TEXT)
        val position = args[1].toInt()
        val paragraph = document.getItem(position).paragraph
        val commandInstance = commandClass.call(paragraph, args[2])
        history.addAndExecuteCommand(commandInstance, document)
    }

    private fun processList(output: PrintStream) {
        output.println("Title: ${document.getTitle()}")
        (0 until document.getItemsCount()).forEach {
            output.println("\t$it: ${document.getItem(it)}")
        }
    }

    private fun processSetTitle(args: List<String>) {
        ensureCountArgsCorrect(args, 2, SET_TITLE)

        val commandClass = store.get(SET_TITLE)
        val commandInstance = commandClass.call(args[1], document.getTitle())
        history.addAndExecuteCommand(commandInstance, document)
    }

    private fun processInsertImage(args: List<String>) {
        ensureCountArgsCorrect(args, 5, INSERT_IMAGE)

        val commandClass = store.get(INSERT_IMAGE)
        val position = getPositionOfItem(args[1])
        val commandInstance =
            commandClass.call(fileRepository, position, args[2].toInt(), args[3].toInt(), Path.of(args[4]))
        history.addAndExecuteCommand(commandInstance, document)
    }

    private fun processInsertParagraph(args: List<String>) {
        ensureCountArgsCorrect(args, 3, INSERT_PARAGRAPH)

        val commandClass = store.get(INSERT_PARAGRAPH)
        val position = getPositionOfItem(args[1])
        val commandInstance = commandClass.call(position, args[2])
        history.addAndExecuteCommand(commandInstance, document)
    }

    private fun getPositionOfItem(arg: String) = if (arg != "end") arg.toInt() else null

    private fun ensureCountArgsCorrect(args: List<String>, count: Int, type: CommandType) {
        val countNotEmptyArgs = args.stream()
            .filter { it != "" }
            .count()
        if (countNotEmptyArgs < count) {
            throw IllegalArgumentException("Please use form: ${type.getCommandWithArgs()}")
        }
    }
}
