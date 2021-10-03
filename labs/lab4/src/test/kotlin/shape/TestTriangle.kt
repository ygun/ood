package shape

import canvas.Canvas
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class TestTriangle {
    @Test
    fun `Triangle build with incorrect input values throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> {
            Triangle(Color.GREEN, Point(0.0, 0.0), Point(0.0, 0.0), Point(0.0, 0.0))
        }
        assertThrows<IllegalArgumentException> {
            Triangle(Color.GREEN, Point(0.0, 0.0), Point(0.0, 1.0), Point(0.0, 4.0))
        }
        assertThrows<IllegalArgumentException> {
            Triangle(Color.GREEN, Point(1.0, 1.0), Point(1.0, 4.0), Point(1.0, 5.0))
        }
    }

    @Test
    fun `draw with canvas return tags for triangle`() {
        val canvas = Canvas()
        val triangle = Triangle(Color.GREEN, Point(0.0, 0.0), Point(1.0, 1.0), Point(3.0, 4.0))

        assertEquals(triangle.getColor(), Color.GREEN)
        canvas.setColor(triangle.getColor())
        assertEquals(
            "<line color=#00FF00 fromX=0.00 fromY=0.00 toX=1.00 toY=1.00>\n" +
                    "<line color=#00FF00 fromX=1.00 fromY=1.00 toX=3.00 toY=4.00>\n" +
                    "<line color=#00FF00 fromX=3.00 fromY=4.00 toX=0.00 toY=0.00>\n", triangle.draw(canvas)
        )

        triangle.setColor(Color.PINK)

        assertEquals(triangle.getColor(), Color.PINK)
        canvas.setColor(triangle.getColor())
        assertEquals(
            "<line color=#FFC0CB fromX=0.00 fromY=0.00 toX=1.00 toY=1.00>\n" +
                    "<line color=#FFC0CB fromX=1.00 fromY=1.00 toX=3.00 toY=4.00>\n" +
                    "<line color=#FFC0CB fromX=3.00 fromY=4.00 toX=0.00 toY=0.00>\n", triangle.draw(canvas)
        )
    }
}