import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import slide.Slide
import kotlin.test.assertEquals

class TestSlide {
    @Test
    fun `insertShape throw IllegalArgumentException if position is not valid`() {
        val slide = Slide(100.0, 100.0)
        assertThrows<IllegalArgumentException> { slide.insertShape(DEFAULT_RECTANGLE, -1) }
        assertThrows<IllegalArgumentException> { slide.insertShape(DEFAULT_RECTANGLE, 1) }
        assertThrows<IllegalArgumentException> { slide.insertShape(DEFAULT_RECTANGLE, 3) }
    }

    @Test
    fun `insertShape able to add shape without position`() {
        val slide = Slide(100.0, 100.0)

        slide.insertShape(DEFAULT_RECTANGLE)
        assertEquals(1, slide.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, slide.getShape(0))

        slide.insertShape(DEFAULT_ELLIPSE)
        assertEquals(2, slide.getShapesCount())
        assertEquals(DEFAULT_ELLIPSE, slide.getShape(1))
    }

    @Test
    fun `insertShape able to add shape with position`() {
        val slide = Slide(100.0, 100.0)

        slide.insertShape(DEFAULT_RECTANGLE, 0)
        assertEquals(1, slide.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, slide.getShape(0))

        slide.insertShape(DEFAULT_ELLIPSE, 0)
        assertEquals(2, slide.getShapesCount())
        assertEquals(DEFAULT_ELLIPSE, slide.getShape(0))
    }

    @Test
    fun `getShape throw IllegalArgumentException if position is not valid`() {
        val slide = Slide(100.0, 100.0)
        assertThrows<IllegalArgumentException> { slide.getShape(-1) }
        assertThrows<IllegalArgumentException> { slide.getShape(0) }
        assertThrows<IllegalArgumentException> { slide.getShape(1) }
        assertThrows<IllegalArgumentException> { slide.getShape(3) }
    }

    @Test
    fun `getShape return IShape by position`() {
        val slide = Slide(100.0, 100.0)

        slide.insertShape(DEFAULT_RECTANGLE, 0)
        assertEquals(1, slide.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, slide.getShape(0))

        slide.insertShape(DEFAULT_ELLIPSE, 0)
        assertEquals(2, slide.getShapesCount())
        assertEquals(DEFAULT_ELLIPSE, slide.getShape(0))

        slide.insertShape(ELLIPSE_WITH_STYLE)
        assertEquals(3, slide.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, slide.getShape(2))
    }

    @Test
    fun `removeShape throw IllegalArgumentException if position is not valid`() {
        val slide = Slide(100.0, 100.0)
        assertThrows<IllegalArgumentException> { slide.removeShape(-1) }
        assertThrows<IllegalArgumentException> { slide.removeShape(0) }
        assertThrows<IllegalArgumentException> { slide.removeShape(1) }
        assertThrows<IllegalArgumentException> { slide.removeShape(3) }
    }

    @Test
    fun `removeShape reduce shapes size`() {
        val slide = Slide(100.0, 100.0)

        slide.insertShape(DEFAULT_RECTANGLE, 0)
        assertEquals(1, slide.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, slide.getShape(0))

        slide.insertShape(DEFAULT_ELLIPSE, 0)
        assertEquals(2, slide.getShapesCount())
        assertEquals(DEFAULT_ELLIPSE, slide.getShape(0))

        slide.insertShape(ELLIPSE_WITH_STYLE)
        assertEquals(3, slide.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, slide.getShape(2))

        slide.insertShape(ELLIPSE_WITH_STYLE)
        assertEquals(4, slide.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, slide.getShape(3))

        slide.removeShape(0)
        assertEquals(3, slide.getShapesCount())
        assertEquals(DEFAULT_RECTANGLE, slide.getShape(0))

        slide.removeShape(1)
        assertEquals(2, slide.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, slide.getShape(1))

        slide.removeShape(0)
        assertEquals(1, slide.getShapesCount())
        assertEquals(ELLIPSE_WITH_STYLE, slide.getShape(0))

        slide.removeShape(0)
        assertEquals(0, slide.getShapesCount())
    }
}