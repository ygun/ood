package shape

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class TestColor {
    @Test
    fun `getColor by name throw IllegalArgumentException when not correct name`() {
        assertThrows<IllegalArgumentException> { Color.getShape("") }
        assertThrows<IllegalArgumentException> { Color.getShape("test") }
    }

    @Test
    fun `getColor by name return color`() {
        assertEquals(Color.GREEN, Color.getShape("green"))
        assertEquals(Color.RED, Color.getShape("red"))
        assertEquals(Color.BLUE, Color.getShape("blue"))
        assertEquals(Color.YELLOW, Color.getShape("yellow"))
        assertEquals(Color.PINK, Color.getShape("pink"))
        assertEquals(Color.BLACK, Color.getShape("black"))
    }
}