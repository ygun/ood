package command

import document.IDocument

class InsertParagraph(
    private val position: Int?,
    private val text: String
) : ICommand {

    override fun execute(document: IDocument) {
        document.insertParagraph(text, position)
    }

    override fun revert(document: IDocument) {
        when {
            position != null -> document.deleteItem(position)
            else -> document.deleteItem(document.getItemsCount() - 1)
        }
    }
}