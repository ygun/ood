package command

import document.IDocument

class Save : ICommand {
    override fun execute(document: IDocument) {
        TODO("Not yet implemented")
    }

    override fun revert(document: IDocument) {
        TODO("Not yet implemented")
    }

    override fun dispose() = Unit
}