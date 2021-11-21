package command

import document.Document
import document.element.image.Image
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.test.assertEquals

class TestResizeImage {
    @Test
    fun `ResizeImage with incorrect size throw IllegalArgumentException`() {
        val image = Image(Path.of("/"), 200, 200)

        assertThrows<IllegalArgumentException> { ResizeImage(image, 0, 0) }
        assertThrows<IllegalArgumentException> { ResizeImage(image, -120, -1) }
        assertThrows<IllegalArgumentException> { ResizeImage(image, 11111, 12) }
        assertThrows<IllegalArgumentException> { ResizeImage(image, 12, 111111) }
        assertThrows<IllegalArgumentException> { ResizeImage(image, 12, 0) }
        assertThrows<IllegalArgumentException> { ResizeImage(image, 0, 12) }
        assertThrows<IllegalArgumentException> { ResizeImage(image, 12, -12) }
        assertThrows<IllegalArgumentException> { ResizeImage(image, -12, 8) }
    }

    @Test
    fun `execute and revert change image size`() {
        val document = Document()

        val image = Image(Path.of("/"), 200, 200)

        val resizeImage = ResizeImage(image, 330, 330)

        resizeImage.execute(document)
        assertEquals(Image(Path.of("/"), 330, 330), image)

        resizeImage.revert(document)
        assertEquals(Image(Path.of("/"), 200, 200), image)
    }
}