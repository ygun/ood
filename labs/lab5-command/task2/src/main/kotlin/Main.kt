import command.*
import command.disposable.InsertImage
import document.Document
import history.CommandHistory
import store.CommandStore
import resource.FileRepository
import util.CommandType.*
import util.TEMP_DIRECTORY
import java.nio.file.Path

fun main() {
    val commandHistory = CommandHistory()
    val document = Document(commandHistory)
    val fileRepository = FileRepository(Path.of(TEMP_DIRECTORY))

    val app = Application(document, fileRepository, commandHistory)
    app.run()
}

fun createCommandStore(): CommandStore {
    val commandStore = CommandStore()
    commandStore.add(INSERT_PARAGRAPH, InsertParagraph::class)
    commandStore.add(INSERT_IMAGE, InsertImage::class)
    commandStore.add(SET_TITLE, SetTitle::class)
    commandStore.add(REPLACE_TEXT, ReplaceText::class)
    commandStore.add(RESIZE_IMAGE, ResizeImage::class)
    commandStore.add(DELETE_ITEM, DeleteItem::class)
    commandStore.add(UNDO, Undo::class)
    commandStore.add(REDO, Redo::class)
    commandStore.add(SAVE, Save::class)
    return commandStore
}