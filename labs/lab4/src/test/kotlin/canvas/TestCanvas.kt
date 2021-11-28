package canvas

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shape.Point

class TestCanvas {
    @Test
    fun `drawEllipse with incorrect input values throw IllegalArgumentException`() {
        val canvas = Canvas()
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(10.0, -2.0), 4, 0) }
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(10.0, -2.0), -1, 2) }
        assertThrows<IllegalArgumentException> { canvas.drawEllipse(Point(10.0, -2.0), -1, 0) }
    }
}