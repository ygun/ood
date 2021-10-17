package app

import graphics_lib.Canvas
import org.junit.jupiter.api.Test
import shape_drawing_lib.CanvasPainter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.io.PrintStream
import kotlin.test.assertEquals


class TestApp {
    @Test
    fun `paintPicture draw triangle and rectangle`() {
        val os = ByteArrayOutputStream()
        val ps = PrintStream(os)

        val canvas = Canvas(ps)
        val painter = CanvasPainter(canvas)

        paintPicture(painter, ps)

        val ins: InputStream = File("./src/test/kotlin/app/paintPicture-res.txt").inputStream()
        val expectedStr = ins.bufferedReader().use { it.readText() }

        assertEquals(
            expectedStr,
            os.toString()
        )
    }

    @Test
    fun `paintPictureOnModernGraphicsRenderer draw triangle and rectangle`() {
        val os = ByteArrayOutputStream()
        val ps = PrintStream(os)

        paintPictureOnModernGraphicsRenderer(ps)

        val ins: InputStream = File("./src/test/kotlin/app/paintPictureOnModernGraphicsRenderer-res.txt").inputStream()
        val expectedStr = ins.bufferedReader().use { it.readText() }

        assertEquals(
            expectedStr,
            os.toString()
        )
    }
}