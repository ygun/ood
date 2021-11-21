package command.insert_image

import command.disposable.InsertImage
import document.Document
import document.element.image.Image
import document.item.DocumentItem
import org.junit.jupiter.api.Test
import resource.FileRepository
import java.nio.file.Path
import kotlin.test.assertEquals

class TestInsertImage {
    @Test
    fun `execute and revert with position change document item by postion`() {
        val document = Document()

        val fileRepository = FileRepository(Path.of("D://GitHub/ood/labs/lab5-command/task1/src/test/kotlin/command/insert_image/copy_to"))
        val img = Image(Path.of("D://GitHub/ood/labs/lab5-command/task1/data/img.jpg"), 200, 200)
        val insertImage = InsertImage(fileRepository, 0, img.getWidth(), img.getHeight(), img.getPath())

        insertImage.execute(document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(img, null), document.getItem(0))

        insertImage.revert(document)
        assertEquals(0, document.getItemsCount())
    }

    @Test
    fun `execute and revert without position change the last document item`() {
        val document = Document()

        val fileRepository = FileRepository(Path.of("D://GitHub/ood/labs/lab5-command-command/task1/src/test/kotlin/command/insert_image/copy_to"))
        val img = Image(Path.of("D://GitHub/ood/labs/lab5-command/task1/data/img2.jpg"), 200, 200)
        val insertImage = InsertImage(fileRepository, 0, img.getWidth(), img.getHeight(), img.getPath())

        insertImage.execute(document)
        assertEquals(1, document.getItemsCount())
        assertEquals(DocumentItem(img, null), document.getItem(0))

        insertImage.revert(document)
        assertEquals(0, document.getItemsCount())
    }
}