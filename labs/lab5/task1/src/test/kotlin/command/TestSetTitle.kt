package command

import document.Document
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestSetTitle {
    @Test
    fun `execute and revert change document title`() {
        val document = Document()

        val setTitle = SetTitle("Test", document.getTitle())

        assertEquals("New Document", document.getTitle())

        setTitle.execute(document)
        assertEquals("Test", document.getTitle())

        setTitle.revert(document)
        assertEquals("New Document", document.getTitle())
    }
}