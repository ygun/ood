package command

import document.IDocument
import document.element.paragraph.Paragraph

class ReplaceText(
    private val paragraph: Paragraph,
    private val newText: String
) : ICommand {

    private val oldText: String = paragraph.getText()

    override fun execute(document: IDocument) = paragraph.setText(newText)

    override fun revert(document: IDocument) = paragraph.setText(oldText)
}