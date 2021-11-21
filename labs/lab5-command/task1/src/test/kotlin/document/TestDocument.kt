package document

import document.element.image.Image
import document.element.paragraph.Paragraph
import document.item.DocumentItem
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.io.path.Path
import kotlin.test.assertEquals

class TestDocument {
    @Test
    fun `insertParagraph with correct position return Paragraph`() {
        val document = Document()

        assertEquals(0, document.getItemsCount())

        assertEquals(Paragraph("test"), document.insertParagraph("test", 0))
        assertEquals(1, document.getItemsCount())
        assertEquals(document.getItem(0), DocumentItem(null, Paragraph("test")))

        assertEquals(Paragraph("test2"), document.insertParagraph("test2", 1))
        assertEquals(2, document.getItemsCount())
        assertEquals(document.getItem(1), DocumentItem(null, Paragraph("test2")))
    }

    @Test
    fun `insertParagraph with existed position put Paragraph to given position`() {
        val document = Document()

        assertEquals(0, document.getItemsCount())

        assertEquals(Paragraph("test"), document.insertParagraph("test", 0))
        assertEquals(1, document.getItemsCount())
        assertEquals(document.getItem(0), DocumentItem(null, Paragraph("test")))

        assertEquals(Paragraph("test2"), document.insertParagraph("test2", 0))
        assertEquals(2, document.getItemsCount())
        assertEquals(document.getItem(0), DocumentItem(null, Paragraph("test2")))
        assertEquals(document.getItem(1), DocumentItem(null, Paragraph("test")))
    }

    @Test
    fun `insertParagraph with incorrect position throw IllegalArgumentException`() {
        val document = Document()

        assertEquals(0, document.getItemsCount())

        assertThrows<IllegalArgumentException> { document.insertParagraph("test", 2) }
        assertThrows<IllegalArgumentException> { document.insertParagraph("test", -1) }
    }

    @Test
    fun `insertImage with correct position return Image`() {
        val document = Document()
        val firstImg = Image(Path("/"), 200, 200)
        val secondImg = Image(Path("/test"), 330, 330)

        assertEquals(0, document.getItemsCount())

        assertEquals(firstImg, document.insertImage(Path("/"), 200, 200))
        assertEquals(1, document.getItemsCount())
        assertEquals(document.getItem(0), DocumentItem(firstImg, null))

        assertEquals(secondImg, document.insertImage(Path("/test"), 330, 330))
        assertEquals(2, document.getItemsCount())
        assertEquals(document.getItem(1), DocumentItem(secondImg, null))
    }

    @Test
    fun `insertImage with existed position put Image to given position`() {
        val document = Document()
        val firstImg = Image(Path("/"), 200, 200)
        val secondImg = Image(Path("/test"), 330, 330)

        assertEquals(0, document.getItemsCount())

        assertEquals(firstImg, document.insertImage(Path("/"), 200, 200))
        assertEquals(1, document.getItemsCount())
        assertEquals(document.getItem(0), DocumentItem(firstImg, null))

        assertEquals(secondImg, document.insertImage(Path("/test"), 330, 330, 0))
        assertEquals(2, document.getItemsCount())
        assertEquals(document.getItem(0), DocumentItem(secondImg, null))
        assertEquals(document.getItem(1), DocumentItem(firstImg, null))
    }

    @Test
    fun `insertImage with incorrect position throw IllegalArgumentException`() {
        val document = Document()

        assertEquals(0, document.getItemsCount())

        assertThrows<IllegalArgumentException> { document.insertImage(Path("/"), 200, 200, 2) }
        assertThrows<IllegalArgumentException> { document.insertImage(Path("/"), 200, 200, -1) }
    }

    @Test
    fun `able to get and change title in document`() {
        val document = Document()

        assertEquals("New Document", document.getTitle())

        document.setTitle("Test")

        assertEquals("Test", document.getTitle())
    }

    @Test
    fun `able to save document`() {
        val document = Document()
        val firstImg = Image(Path("data/img.jpg"), 200, 200)
        val secondImg = Image(Path("data/img2.jpg"), 330, 330)

        assertEquals(0, document.getItemsCount())

        assertEquals(
            firstImg,
            document.insertImage(Path("data/img.jpg"), 200, 200)
        )
        assertEquals(1, document.getItemsCount())
        assertEquals(document.getItem(0), DocumentItem(firstImg, null))

        assertEquals(
            secondImg,
            document.insertImage(Path("data/img2.jpg"), 330, 330, 0)
        )
        assertEquals(2, document.getItemsCount())
        assertEquals(document.getItem(0), DocumentItem(secondImg, null))
        assertEquals(document.getItem(1), DocumentItem(firstImg, null))

        assertEquals(Paragraph("test"), document.insertParagraph("test", 0))
        assertEquals(3, document.getItemsCount())
        assertEquals(document.getItem(0), DocumentItem(null, Paragraph("test")))

        document.save(Path("src/test/kotlin/document/saved_docs/savedDoc.html"))
    }
}