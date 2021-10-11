package document.converter

import document.Document
import document.element.image.Image
import document.element.paragraph.Paragraph
import document.item.DocumentItem
import org.junit.jupiter.api.Test
import kotlin.io.path.Path
import kotlin.test.assertEquals

class TestHtmlConverter {
    @Test
    fun `convert document with image and paragraph return valid html`() {
        val document = Document()
        val htmlConverter = HtmlConverter()
        val img = Image(Path("/"), 200, 200)
        val paragraph = Paragraph("test")

        assertEquals(0, document.getItemsCount())

        assertEquals(img, document.insertImage(Path("/"), 200, 200))
        assertEquals(1, document.getItemsCount())
        assertEquals(document.getItem(0), DocumentItem(img, null))

        assertEquals(paragraph, document.insertParagraph("test"))
        assertEquals(2, document.getItemsCount())
        assertEquals(document.getItem(1), DocumentItem(null, paragraph))

        val documentHtml = "<!DOCTYPE html>\n" +
                "<html lang=\"\">\n" +
                "<head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<title>New Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\t<img src=\"images/\" width=\"200\" height=\"200\">\n" +
                "\t<br>\n" +
                "\t<p>test</p>\n" +
                "\t<br>\n" +
                "</body>\n" +
                "</html>"
        assertEquals(documentHtml, htmlConverter.convert(document))
    }
}