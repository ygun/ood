package command

import document.Document
import document.element.paragraph.Paragraph
import document.item.DocumentItem
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestInsertParagraph {
    @Test
    fun `execute and revert with position change document item by postion`() {
        val document = Document()

        val insertParagraph = InsertParagraph(0, "text")

        insertParagraph.execute(document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(0))

        insertParagraph.revert(document)
        assertEquals(0, document.getItemsCount())
    }

    @Test
    fun `execute and revert without position change the last document item`() {
        val document = Document()

        val insertParagraph = InsertParagraph(null, "text")

        insertParagraph.execute(document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(0))

        insertParagraph.revert(document)
        assertEquals(0, document.getItemsCount())
    }
}