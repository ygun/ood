package command

import document.IDocument
import document.item.DocumentItem

class DeleteItem(
    private val item: DocumentItem,
    private val position: Int
) : ICommand {

    override fun execute(document: IDocument) = document.deleteItem(position)

    override fun revert(document: IDocument) {
        if (item.image == null && item.paragraph == null) {
            throw RuntimeException("Unknown document")
        }

        when {
            item.image != null ->
                document.insertImage(
                    item.image!!.getPath(),
                    item.image!!.getWidth(),
                    item.image!!.getHeight(),
                    position
                )
            item.paragraph != null ->
                document.insertParagraph(
                    item.paragraph!!.getText(),
                    position
                )
        }
    }

    override fun dispose() = Unit
}