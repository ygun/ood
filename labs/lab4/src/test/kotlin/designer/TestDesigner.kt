package designer

import factory.ShapeFactory
import org.junit.jupiter.api.Test
import picture.PictureDraft
import shape.*
import kotlin.test.assertEquals

class TestDesigner {
    @Test
    fun `createShape return correct Shape`() {
        val factory = ShapeFactory()
        val designer = Designer(factory)

        val input = "rectangle green 0.0 0.0 1.0 1.0\n" +
                "triangle green 0.0 0.0 1.0 1.0 4.2 5.1\n" +
                "ellipse green 0.0 0.0 4 2\n" +
                "regular-polygon green 0.0 0.0 4 2\n" +
                "end\n"
        assertEquals(
            PictureDraft(
                listOf(
                    Rectangle(Color.GREEN, Point(0.0, 0.0), Point(1.0, 1.0)),
                    Triangle(Color.GREEN, Point(0.0, 0.0), Point(1.0, 1.0), Point(4.2, 5.1)),
                    Ellipse(Color.GREEN, Point(0.0, 0.0), 4, 2),
                    RegularPolygon(Color.GREEN, Point(0.0, 0.0), 4, 2)
                )
            ),
            designer.createDraft(
                input.byteInputStream()
            )
        )
    }
}