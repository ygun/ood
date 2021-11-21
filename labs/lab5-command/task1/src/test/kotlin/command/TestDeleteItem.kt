package command

import document.Document
import document.element.paragraph.Paragraph
import document.item.DocumentItem
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestDeleteItem {
    @Test
    fun `execute and revert change document items`() {
        val document = Document()

        val paragraph = Paragraph("test")
        val documentItem = DocumentItem(null, paragraph)
        document.insertParagraph(paragraph.getText())

        val deleteItem = DeleteItem(documentItem, 0)

        assertEquals(1, document.getItemsCount())

        deleteItem.execute(document)
        assertEquals(0, document.getItemsCount())

        deleteItem.revert(document)
        assertEquals(1, document.getItemsCount())
    }
}