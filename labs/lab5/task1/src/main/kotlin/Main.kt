import command.*
import command.helper.Help
import command.helper.List
import util.CommandType.*
import command.InsertImage
import document.Document
import history.CommandHistory
import registry.CommandRegistry
import resource.FileRepository
import java.nio.file.Path

fun main() {
    val commandHistory = CommandHistory()
    val document = Document(commandHistory)
    val fileRepository = FileRepository(Path.of("temp"))

    val app = Application(document, fileRepository, commandHistory)
    app.run()
}

fun createCommandRegistry(): CommandRegistry {
    val commandRegistry = CommandRegistry()
    commandRegistry.register(INSERT_PARAGRAPH, InsertParagraph::class)
    commandRegistry.register(INSERT_IMAGE, InsertImage::class)
    commandRegistry.register(SET_TITLE, SetTitle::class)
    commandRegistry.register(LIST, List::class)
    commandRegistry.register(REPLACE_TEXT, ReplaceText::class)
    commandRegistry.register(RESIZE_IMAGE, ResizeImage::class)
    commandRegistry.register(DELETE_ITEM, DeleteItem::class)
    commandRegistry.register(HELP, Help::class)
    commandRegistry.register(UNDO, Undo::class)
    commandRegistry.register(REDO, Redo::class)
    commandRegistry.register(SAVE, Save::class)
    return commandRegistry
}