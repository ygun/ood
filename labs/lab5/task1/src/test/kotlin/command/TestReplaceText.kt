package command

import document.Document
import document.element.image.Image
import document.element.paragraph.Paragraph
import org.junit.jupiter.api.Test
import java.nio.file.Path
import kotlin.test.assertEquals

class TestReplaceText {
    @Test
    fun `execute and revert change text`() {
        val document = Document()

        val image = Image(Path.of("/"), 200, 200)

        val paragraph = Paragraph("test")
        val replaceText = ReplaceText(paragraph, "new text")
        replaceText.execute(document)

        assertEquals("new text", paragraph.getText())

        replaceText.revert(document)
        assertEquals("test", paragraph.getText())
    }
}