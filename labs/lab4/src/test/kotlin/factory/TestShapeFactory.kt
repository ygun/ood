package factory

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shape.*
import kotlin.test.assertEquals

class TestShapeFactory {
    @Test
    fun `ShapeFactory with empty input throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> { ShapeFactory().createShape("") }
    }

    @Test
    fun `createShape with incorrect params throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> { ShapeFactory().createShape("rectangle green test") }
        assertThrows<IllegalArgumentException> { ShapeFactory().createShape("rectangle green     1     test") }
        assertThrows<IllegalArgumentException> { ShapeFactory().createShape("ellipse green     1     1") }
        assertThrows<IllegalArgumentException> { ShapeFactory().createShape("regular-polygon green     1.1     1 test") }
        assertThrows<IllegalArgumentException> { ShapeFactory().createShape("triangle green     1.1     1 test") }
    }

    @Test
    fun `createShape return correct Shape`() {
        val factory = ShapeFactory()

        assertEquals(
            Rectangle(Color.GREEN, Point(0.0, 0.0), Point(1.0, 1.0)),
            factory.createShape("rectangle green 0.0 0.0 1.0 1.0")
        )

        assertEquals(
            Triangle(Color.GREEN, Point(0.0, 0.0), Point(1.0, 1.0), Point(4.2, 5.1)),
            factory.createShape("triangle green 0.0 0.0 1.0 1.0 4.2 5.1")
        )

        assertEquals(
            Ellipse(Color.GREEN, Point(0.0, 0.0), 4, 2),
            factory.createShape("ellipse green 0.0 0.0 4 2")
        )

        assertEquals(
            RegularPolygon(Color.GREEN, Point(0.0, 0.0), 4, 2),
            factory.createShape("regular-polygon green 0.0 0.0 4 2")
        )
    }
}