package history

import command.InsertParagraph
import command.ResizeImage
import document.Document
import document.element.paragraph.Paragraph
import document.item.DocumentItem
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class TestCommandHistory {
    @Test
    fun `addCommandAndExecute change history and execute command`() {
        val document = Document()

        val insertParagraph = InsertParagraph(null, "text")

        val commandHistory = CommandHistory()

        commandHistory.addCommandAndExecute(insertParagraph, document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(0))

        commandHistory.addCommandAndExecute(insertParagraph, document)
        assertEquals(2, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(1))
    }

    @Test
    fun `undo revert command`() {
        val document = Document()

        val insertParagraph = InsertParagraph(null, "text")

        val commandHistory = CommandHistory()

        commandHistory.addCommandAndExecute(insertParagraph, document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(0))

        commandHistory.addCommandAndExecute(insertParagraph, document)
        assertEquals(2, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(1))

        commandHistory.undo(document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(0))

        commandHistory.undo(document)
        assertEquals(0, document.getItemsCount())
    }

    @Test
    fun `redo execute command after undo`() {
        val document = Document()

        val insertParagraph = InsertParagraph(null, "text")

        val commandHistory = CommandHistory()

        commandHistory.addCommandAndExecute(insertParagraph, document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(0))

        commandHistory.addCommandAndExecute(insertParagraph, document)
        assertEquals(2, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(1))

        commandHistory.undo(document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(0))

        commandHistory.undo(document)
        assertEquals(0, document.getItemsCount())

        commandHistory.redo(document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(0))

        commandHistory.redo(document)
        assertEquals(2, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(1))
    }

    @Test
    fun `undo throw RuntimeException if isEmpty or atBottom`() {
        val document = Document()

        val commandHistory = CommandHistory()

        assert(commandHistory.isEmpty())
        assertThrows<RuntimeException> { commandHistory.undo(document) }

        val insertParagraph = InsertParagraph(null, "text")
        commandHistory.addCommandAndExecute(insertParagraph, document)
        commandHistory.undo(document)

        assert(commandHistory.atBottom())
        assertThrows<RuntimeException> { commandHistory.undo(document) }
    }

    @Test
    fun `redo throw RuntimeException if isEmpty or atTop`() {
        val document = Document()

        val commandHistory = CommandHistory()

        assert(commandHistory.isEmpty())
        assertThrows<RuntimeException> { commandHistory.redo(document) }

        val insertParagraph = InsertParagraph(null, "text")
        commandHistory.addCommandAndExecute(insertParagraph, document)

        assert(commandHistory.atTop())
        assertThrows<RuntimeException> { commandHistory.redo(document) }
    }

    @Test
    fun `change history branch delete all commands in other branch`() {
        val document = Document()

        val insertParagraph = InsertParagraph(null, "text")

        val commandHistory = CommandHistory()

        commandHistory.addCommandAndExecute(insertParagraph, document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(0))

        commandHistory.addCommandAndExecute(insertParagraph, document)
        assertEquals(2, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(1))

        commandHistory.undo(document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("text")), document.getItem(0))

        commandHistory.undo(document)
        assertEquals(0, document.getItemsCount())

        val changeBranch = InsertParagraph(null, "change branch")

        commandHistory.addCommandAndExecute(changeBranch, document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(null, Paragraph("change branch")), document.getItem(0))

        assert(commandHistory.atTop())
        assertThrows<RuntimeException> { commandHistory.redo(document) }
    }
}