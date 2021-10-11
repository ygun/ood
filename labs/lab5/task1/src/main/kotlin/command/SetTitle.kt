package command

import document.IDocument

class SetTitle(
    private val newTitle: String,
    private val oldTitle: String
) : ICommand {

    override fun execute(document: IDocument) = document.setTitle(newTitle)

    override fun revert(document: IDocument) = document.setTitle(oldTitle)

    override fun dispose() = Unit
}