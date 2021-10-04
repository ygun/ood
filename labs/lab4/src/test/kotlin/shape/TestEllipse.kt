package shape

import canvas.Canvas
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class TestEllipse {
    @Test
    fun `Ellipse build with incorrect input values throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> { Ellipse(Color.GREEN, Point(0.0, 0.0), 0, 1) }
        assertThrows<IllegalArgumentException> { Ellipse(Color.GREEN, Point(0.0, 0.0), 1, 0) }
        assertThrows<IllegalArgumentException> { Ellipse(Color.GREEN, Point(0.0, 0.0), -2, 2) }
        assertThrows<IllegalArgumentException> { Ellipse(Color.GREEN, Point(0.0, 0.0), -2, -2) }
    }

    @Test
    fun `draw with canvas return ellipse tag`() {
        val outputStream = ByteArrayOutputStream();
        val canvas = Canvas(PrintStream(outputStream, true))
        val ellipse = Ellipse(Color.GREEN, Point(0.0, 0.0), 1, 1)

        assertEquals(ellipse.getColor(), Color.GREEN)
        canvas.setColor(ellipse.getColor())
        ellipse.draw(canvas)
        assertEquals("<ellipse color=#00FF00 centerX=0.00 centerY=0.00 majorRadius=1 minorRadius=1>\n", outputStream.toString())

        outputStream.reset()

        ellipse.setColor(Color.PINK)
        assertEquals(ellipse.getColor(), Color.PINK)
        canvas.setColor(ellipse.getColor())

        ellipse.draw(canvas)
        assertEquals("<ellipse color=#FFC0CB centerX=0.00 centerY=0.00 majorRadius=1 minorRadius=1>\n", outputStream.toString())
    }
}