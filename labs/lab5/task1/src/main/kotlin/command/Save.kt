package command

import document.IDocument
import java.nio.file.Path

class Save(private val path: Path) : ICommand {

    override fun execute(document: IDocument) = document.save(path)

    override fun revert(document: IDocument) = Unit
}