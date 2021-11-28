package command.disposable

import command.ICommand
import document.IDocument
import resource.IResourceRepository
import util.TEMP_DIRECTORY
import java.io.File
import java.nio.file.Path
import kotlin.io.path.name

class InsertImage(
    private val repository: IResourceRepository,
    private val position: Int? = null,
    private val width: Int,
    private val height: Int,
    private val path: Path
) : ICommand, IDisposable {

    override fun execute(document: IDocument) {
        document.insertImage(path, width, height, position)
        repository.add(File(path.toUri()))
    }

    override fun revert(document: IDocument) {
        when {
            position != null -> document.deleteItem(position)
            else -> document.deleteItem(document.getItemsCount() - 1)
        }
    }

    override fun dispose() {
        val tempPath = TEMP_DIRECTORY + "/" + path.name
        repository.delete(File(tempPath))
    }
}