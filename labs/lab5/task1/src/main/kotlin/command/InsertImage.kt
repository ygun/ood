package command

import command.ICommand
import document.IDocument
import resource.IResourceRepository
import java.io.File
import java.nio.file.Path

class InsertImage(
    private val repository: IResourceRepository,
    private val position: Int? = null,
    private val width: Int,
    private val height: Int,
    private val path: Path
) : ICommand {

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
        repository.delete(File(path.toUri()))
    }
}