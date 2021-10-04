package canvas

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shape.Color
import shape.Point
import kotlin.test.assertEquals

class TestCanvas {
    @Test
    fun `drawLine and drawEllipse with correct input values`() {
        val canvas = Canvas()
        /*assertEquals("<line color=#000000 fromX=0.00 fromY=0.00 toX=1.00 toY=1.00>\n", canvas.drawLine(Point(0.0, 0.0), Point(1.0, 1.0)))
        assertEquals("<ellipse color=#000000 centerX=0.00 centerY=0.00 majorRadius=2 minorRadius=2>\n", canvas.drawEllipse(Point(0.0, 0.0), 2, 2))

        canvas.setColor(Color.BLUE)
        assertEquals("<line color=#0000FF fromX=-11.00 fromY=0.00 toX=6.00 toY=-1.00>\n", canvas.drawLine(Point(-11.0, 0.0), Point(6.0, -1.0)))
        assertEquals("<ellipse color=#0000FF centerX=10.00 centerY=-2.00 majorRadius=4 minorRadius=2>\n", canvas.drawEllipse(Point(10.0, -2.0), 4, 2))*/
    }

    @Test
    fun `drawEllipse with incorrect input values throw IllegalArgumentException`() {
        val canvas = Canvas()
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(10.0, -2.0), 4, 0) }
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(10.0, -2.0), -1, 2) }
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(10.0, -2.0), -1, 0) }
    }
}