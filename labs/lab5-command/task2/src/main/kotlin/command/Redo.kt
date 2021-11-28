package command

import document.IDocument

class Redo : ICommand {
    override fun execute(document: IDocument) = document.redo()

    override fun revert(document: IDocument) = document.undo()
}