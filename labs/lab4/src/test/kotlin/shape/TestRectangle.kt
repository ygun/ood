package shape

import canvas.Canvas
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class TestRectangle {
    @Test
    fun `Rectangle build with incorrect input values throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> { Rectangle(Color.GREEN, Point(0.0, 0.0), Point(0.0, 0.0)) }
        assertThrows<IllegalArgumentException> { Rectangle(Color.GREEN, Point(4.0, 4.0), Point(4.0, 4.0)) }
    }

    @Test
    fun `draw with canvas return tags for rectangle`() {
        val canvas = Canvas()
        val rectangle = Rectangle(Color.GREEN, Point(2.0, 8.0), Point(0.0, 0.0))

        assertEquals(rectangle.getColor(), Color.GREEN)
        canvas.setColor(rectangle.getColor())
        assertEquals(
            "<line color=#00FF00 fromX=2.00 fromY=8.00 toX=0.00 toY=8.00>\n" +
                    "<line color=#00FF00 fromX=0.00 fromY=8.00 toX=0.00 toY=0.00>\n" +
                    "<line color=#00FF00 fromX=0.00 fromY=0.00 toX=2.00 toY=0.00>\n" +
                    "<line color=#00FF00 fromX=2.00 fromY=0.00 toX=2.00 toY=8.00>\n", rectangle.draw(canvas)
        )

        rectangle.setColor(Color.PINK)

        assertEquals(rectangle.getColor(), Color.PINK)
        canvas.setColor(rectangle.getColor())
        assertEquals(
            "<line color=#FFC0CB fromX=2.00 fromY=8.00 toX=0.00 toY=8.00>\n" +
                    "<line color=#FFC0CB fromX=0.00 fromY=8.00 toX=0.00 toY=0.00>\n" +
                    "<line color=#FFC0CB fromX=0.00 fromY=0.00 toX=2.00 toY=0.00>\n" +
                    "<line color=#FFC0CB fromX=2.00 fromY=0.00 toX=2.00 toY=8.00>\n", rectangle.draw(canvas)
        )
    }
}