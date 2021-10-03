package shape

import canvas.Canvas
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class TestRegularPolygon {
    @Test
    fun `RegularPolygon build with incorrect input values throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.GREEN, Point(0.0, 0.0), 0, 0) }
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.GREEN, Point(0.0, 0.0), 0, 1) }
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.GREEN, Point(0.0, 0.0), 1, 0) }
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.GREEN, Point(0.0, 0.0), -1, -1) }
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.GREEN, Point(0.0, 0.0), 2, -1) }
        assertThrows<IllegalArgumentException> { RegularPolygon(Color.GREEN, Point(0.0, 0.0), -2, 1) }
    }

    @Test
    fun `draw with canvas return tags for regularPolygon`() {
        val canvas = Canvas()
        val regularPolygon = RegularPolygon(Color.GREEN, Point(0.0, 0.0), 2, 7)

        assertEquals(regularPolygon.getColor(), Color.GREEN)
        canvas.setColor(regularPolygon.getColor())
        assertEquals(
            "<line color=#00FF00 fromX=0.00 fromY=2.00 toX=1.56 toY=1.25>\n" +
                    "<line color=#00FF00 fromX=1.56 fromY=1.25 toX=1.95 toY=-0.45>\n" +
                    "<line color=#00FF00 fromX=1.95 fromY=-0.45 toX=0.87 toY=-1.80>\n" +
                    "<line color=#00FF00 fromX=0.87 fromY=-1.80 toX=-0.87 toY=-1.80>\n" +
                    "<line color=#00FF00 fromX=-0.87 fromY=-1.80 toX=-1.95 toY=-0.45>\n" +
                    "<line color=#00FF00 fromX=-1.95 fromY=-0.45 toX=-1.56 toY=1.25>\n" +
                    "<line color=#00FF00 fromX=-1.56 fromY=1.25 toX=-0.00 toY=2.00>\n" +
                    "<line color=#00FF00 fromX=-0.00 fromY=2.00 toX=0.00 toY=2.00>\n", regularPolygon.draw(canvas)
        )

        regularPolygon.setColor(Color.PINK)

        assertEquals(regularPolygon.getColor(), Color.PINK)
        canvas.setColor(regularPolygon.getColor())
        assertEquals(
            "<line color=#FFC0CB fromX=0.00 fromY=2.00 toX=1.56 toY=1.25>\n" +
                    "<line color=#FFC0CB fromX=1.56 fromY=1.25 toX=1.95 toY=-0.45>\n" +
                    "<line color=#FFC0CB fromX=1.95 fromY=-0.45 toX=0.87 toY=-1.80>\n" +
                    "<line color=#FFC0CB fromX=0.87 fromY=-1.80 toX=-0.87 toY=-1.80>\n" +
                    "<line color=#FFC0CB fromX=-0.87 fromY=-1.80 toX=-1.95 toY=-0.45>\n" +
                    "<line color=#FFC0CB fromX=-1.95 fromY=-0.45 toX=-1.56 toY=1.25>\n" +
                    "<line color=#FFC0CB fromX=-1.56 fromY=1.25 toX=-0.00 toY=2.00>\n" +
                    "<line color=#FFC0CB fromX=-0.00 fromY=2.00 toX=0.00 toY=2.00>\n", regularPolygon.draw(canvas)
        )
    }
}