package command

import document.IDocument

class Undo : ICommand {
    override fun execute(document: IDocument) = document.undo()

    override fun revert(document: IDocument) = document.redo()

    override fun dispose() = Unit
}