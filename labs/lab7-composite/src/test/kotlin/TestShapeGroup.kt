import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import shape.frame.Frame
import shape.frame.Point
import shape.group.ShapeGroup
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class TestShapeGroup {
    @Test
    fun `insertShape throw IllegalArgumentException if position is not valid`() {
        val shapeGroup = ShapeGroup()
        assertThrows<IllegalArgumentException> { shapeGroup.insertShape(DEFAULT_RECTANGLE, -1) }
        assertThrows<IllegalArgumentException> { shapeGroup.insertShape(DEFAULT_RECTANGLE, 1) }
        assertThrows<IllegalArgumentException> { shapeGroup.insertShape(DEFAULT_RECTANGLE, 3) }
    }

    @Test
    fun `insertShape able to add shape without position`() {
        val shapeGroup = ShapeGroup()

        shapeGroup.insertShape(DEFAULT_RECTANGLE)
        assertEquals(1, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, shapeGroup.getShape(0))

        shapeGroup.insertShape(DEFAULT_ELLIPSE)
        assertEquals(2, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_ELLIPSE, shapeGroup.getShape(1))
    }

    @Test
    fun `insertShape able to add shape with position`() {
        val shapeGroup = ShapeGroup()

        shapeGroup.insertShape(DEFAULT_RECTANGLE, 0)
        assertEquals(1, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, shapeGroup.getShape(0))

        shapeGroup.insertShape(DEFAULT_ELLIPSE, 0)
        assertEquals(2, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_ELLIPSE, shapeGroup.getShape(0))
    }

    @Test
    fun `getShape throw IllegalArgumentException if position is not valid`() {
        val shapeGroup = ShapeGroup()
        assertThrows<IllegalArgumentException> { shapeGroup.getShape(-1) }
        assertThrows<IllegalArgumentException> { shapeGroup.getShape(0) }
        assertThrows<IllegalArgumentException> { shapeGroup.getShape(1) }
        assertThrows<IllegalArgumentException> { shapeGroup.getShape(3) }
    }

    @Test
    fun `getShape return IShape by position`() {
        val shapeGroup = ShapeGroup()

        shapeGroup.insertShape(DEFAULT_RECTANGLE, 0)
        assertEquals(1, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, shapeGroup.getShape(0))

        shapeGroup.insertShape(DEFAULT_ELLIPSE, 0)
        assertEquals(2, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_ELLIPSE, shapeGroup.getShape(0))

        shapeGroup.insertShape(ELLIPSE_WITH_STYLE)
        assertEquals(3, shapeGroup.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, shapeGroup.getShape(2))
    }

    @Test
    fun `removeShape throw IllegalArgumentException if position is not valid`() {
        val shapeGroup = ShapeGroup()
        assertThrows<IllegalArgumentException> { shapeGroup.removeShape(-1) }
        assertThrows<IllegalArgumentException> { shapeGroup.removeShape(0) }
        assertThrows<IllegalArgumentException> { shapeGroup.removeShape(1) }
        assertThrows<IllegalArgumentException> { shapeGroup.removeShape(3) }
    }

    @Test
    fun `removeShape reduce shapes size`() {
        val shapeGroup = ShapeGroup()

        shapeGroup.insertShape(DEFAULT_RECTANGLE, 0)
        assertEquals(1, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, shapeGroup.getShape(0))

        shapeGroup.insertShape(DEFAULT_ELLIPSE, 0)
        assertEquals(2, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_ELLIPSE, shapeGroup.getShape(0))

        shapeGroup.insertShape(ELLIPSE_WITH_STYLE)
        assertEquals(3, shapeGroup.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, shapeGroup.getShape(2))

        shapeGroup.insertShape(ELLIPSE_WITH_STYLE)
        assertEquals(4, shapeGroup.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, shapeGroup.getShape(3))

        shapeGroup.removeShape(0)
        assertEquals(3, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, shapeGroup.getShape(0))

        shapeGroup.removeShape(1)
        assertEquals(2, shapeGroup.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, shapeGroup.getShape(1))

        shapeGroup.removeShape(0)
        assertEquals(1, shapeGroup.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, shapeGroup.getShape(0))

        shapeGroup.removeShape(0)
        assertEquals(0, shapeGroup.getShapesCount())
    }

    @Test
    fun `getFrame return valid Frame`() {
        val shapeGroup = ShapeGroup()

        assertEquals(null, shapeGroup.getFrame())

        shapeGroup.insertShape(DEFAULT_RECTANGLE, 0)
        assertEquals(1, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, shapeGroup.getShape(0))

        assertEquals("Frame(leftTop=Point(x=0.0, y=0.0), width=100.0, height=100.0)", shapeGroup.getFrame().toString())

        shapeGroup.insertShape(DEFAULT_ELLIPSE, 0)
        assertEquals(2, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_ELLIPSE, shapeGroup.getShape(0))

        assertEquals("Frame(leftTop=Point(x=0.0, y=0.0), width=550.0, height=550.0)", shapeGroup.getFrame().toString())

        shapeGroup.insertShape(ELLIPSE_WITH_STYLE)
        assertEquals(3, shapeGroup.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, shapeGroup.getShape(2))

        assertEquals("Frame(leftTop=Point(x=0.0, y=0.0), width=550.0, height=550.0)", shapeGroup.getFrame().toString())
    }

    @Test
    fun `setFrame change frame in all shapes`() {
        val shapeGroup = ShapeGroup()

        assertEquals(null, shapeGroup.getFrame())

        shapeGroup.insertShape(DEFAULT_RECTANGLE, 0)
        assertEquals(1, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, shapeGroup.getShape(0))

        assertEquals("Frame(leftTop=Point(x=0.0, y=0.0), width=100.0, height=100.0)", shapeGroup.getFrame().toString())

        shapeGroup.insertShape(DEFAULT_ELLIPSE, 0)
        assertEquals(2, shapeGroup.getShapesCount())
        assertEquals(DEFAULT_ELLIPSE, shapeGroup.getShape(0))

        assertEquals("Frame(leftTop=Point(x=0.0, y=0.0), width=550.0, height=550.0)", shapeGroup.getFrame().toString())

        val prevFirstFrame = shapeGroup.getShape(0).getFrame()
        val prevSecondFrame = shapeGroup.getShape(1).getFrame()

        val newFrame = Frame(Point(0.0, 0.0), 700.0, 700.0)
        shapeGroup.setFrame(newFrame)
        assertEquals("Frame(leftTop=Point(x=0.0, y=0.0), width=700.0, height=700.0)", shapeGroup.getFrame().toString())

        assert(prevFirstFrame != shapeGroup.getShape(0).getFrame())
        assert(prevSecondFrame != shapeGroup.getShape(1).getFrame())
    }
}