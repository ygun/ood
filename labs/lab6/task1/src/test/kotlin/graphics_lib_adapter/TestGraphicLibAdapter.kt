package graphics_lib_adapter

import graphics_lib_agapter.GraphicLibAdapter
import modern_graphics_lib.ModernGraphicsRenderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class TestGraphicLibAdapter {

    @Test
    fun `lineTo with correct coordinates draw line`() {
        val os = ByteArrayOutputStream()
        val ps = PrintStream(os)

        ModernGraphicsRenderer(ps).use {
            val adapter = GraphicLibAdapter(it)
            adapter.moveTo(0, 0)
            adapter.lineTo(1, 1)
        }

        val ins: InputStream = File("./src/test/kotlin/graphics_lib_adapter/lineTo.txt").inputStream()
        val expectedStr = ins.bufferedReader().use { it.readText() }

        assertEquals(
            expectedStr,
            os.toString()
        )
    }
}