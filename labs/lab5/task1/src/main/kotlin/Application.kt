import command.helper.IHelperCommand
import util.CommandType.*
import document.IDocument
import history.CommandHistory
import registry.CommandRegistry
import resource.FileRepository
import util.CommandType
import java.io.InputStream
import java.io.PrintStream
import java.nio.file.Path
import java.util.*

class Application(
    private val document: IDocument,
    private val fileRepository: FileRepository,
    private val history: CommandHistory = CommandHistory(),
    private val registry: CommandRegistry = createCommandRegistry()
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
                    fileRepository.clear()
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
        }
    }

    private fun processSave(args: List<String>) {
        ensureCountArgsCorrect(args, 1, SAVE)

        document.save(Path.of(args[1]))
    }

    private fun processRedo() = document.redo()

    private fun processUndo() = document.undo()

    private fun processHelp(output: PrintStream) {
        val commandClass = registry.get(HELP)
        val commandInstance = commandClass.call()
        if (commandInstance is IHelperCommand) commandInstance.executeHelper(document, output)
    }

    private fun processDeleteItem(args: List<String>) {
        ensureCountArgsCorrect(args, 2, DELETE_ITEM)

        val commandClass = registry.get(DELETE_ITEM)
        val commandInstance = commandClass.call(args[1])
        history.addCommandAndExecute(commandInstance, document)
    }

    private fun processResizeImage(args: List<String>) {
        ensureCountArgsCorrect(args, 4, RESIZE_IMAGE)

        val commandClass = registry.get(RESIZE_IMAGE)
        val commandInstance = commandClass.call(args[1], args[2], args[3])
        history.addCommandAndExecute(commandInstance, document)
    }

    private fun processReplaceText(args: List<String>) {
        ensureCountArgsCorrect(args, 3, REPLACE_TEXT)

        val commandClass = registry.get(REPLACE_TEXT)
        val commandInstance = commandClass.call(args[1], args[2])
        history.addCommandAndExecute(commandInstance, document)
    }

    private fun processList(output: PrintStream) {
        val commandClass = registry.get(LIST)
        val commandInstance = commandClass.call()
        if (commandInstance is IHelperCommand) commandInstance.executeHelper(document, output)
    }

    private fun processSetTitle(args: List<String>) {
        ensureCountArgsCorrect(args, 2, SET_TITLE)

        val commandClass = registry.get(SET_TITLE)
        val commandInstance = commandClass.call(args[1], document.getTitle())
        history.addCommandAndExecute(commandInstance, document)
    }

    private fun processInsertImage(args: List<String>) {
        ensureCountArgsCorrect(args, 5, INSERT_IMAGE)

        val commandClass = registry.get(INSERT_IMAGE)
        val position = getPosition(args[1])
        val commandInstance = commandClass.call(fileRepository, position, args[2].toInt(), args[3].toInt(), Path.of(args[4]))
        history.addCommandAndExecute(commandInstance, document)
    }

    private fun processInsertParagraph(args: List<String>) {
        ensureCountArgsCorrect(args, 3, INSERT_PARAGRAPH)

        val commandClass = registry.get(INSERT_PARAGRAPH)
        val position = getPosition(args[1])
        val commandInstance = commandClass.call(position, args[2])
        history.addCommandAndExecute(commandInstance, document)
    }

    private fun getPosition(arg: String) = if (arg != "end") arg.toInt() else null

    private fun ensureCountArgsCorrect(args: List<String>, count: Int, type: CommandType) {
        val countNotEmptyArgs = args.stream()
            .filter { it != "" }
            .count()
        if (countNotEmptyArgs < count) {
            throw IllegalArgumentException("Please use form: ${type.getCommandWithArgs()}")
        }
    }
}
