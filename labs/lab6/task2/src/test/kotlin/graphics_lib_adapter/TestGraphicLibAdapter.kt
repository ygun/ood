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
    fun `lineTo with null coordinates throw RuntimeException`() {
        val os = ByteArrayOutputStream()
        val ps = PrintStream(os)

        val adapter = GraphicLibAdapter(ps)

        assertThrows<RuntimeException> { adapter.lineTo(0, 1) }
    }

    @Test
    fun `lineTo with correct coordinates draw line`() {
        val os = ByteArrayOutputStream()
        val ps = PrintStream(os)


        GraphicLibAdapter(ps).use {
            it.moveTo(0, 0)
            it.lineTo(1, 1)
        }

        val ins: InputStream = File("./src/test/kotlin/graphics_lib_adapter/lineTo.txt").inputStream()
        val expectedStr = ins.bufferedReader().use { it.readText() }

        assertEquals(
            expectedStr,
            os.toString()
        )
    }
}