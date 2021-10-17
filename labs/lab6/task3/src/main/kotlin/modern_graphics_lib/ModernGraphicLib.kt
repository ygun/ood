package modern_graphics_lib

import java.io.PrintStream

class Point(
    val x: Int,
    val y: Int
) {}

const val DEFAULT_ALPHA_CHANNEL = 1

class RGBAColor(
    val r: Int,
    val g: Int,
    val b: Int,
    val a: Int = DEFAULT_ALPHA_CHANNEL
) {
    override fun toString(): String = "RGBAColor(r=$r, g=$g, b=$b, a=$a)"
}

val BLACK_RGBA = RGBAColor(0, 0, 0, 1)

class ModernGraphicsRenderer(private val out: PrintStream = System.out) : AutoCloseable {

    private var isDrawing = false

    fun beginDraw() {
        if (isDrawing) {
            throw IllegalAccessException("Drawing has already begun")
        }
        out.println("<draw>")
        isDrawing = true
    }

    fun drawLine(start: Point, end: Point, color: RGBAColor = BLACK_RGBA) {
        if (!isDrawing) {
            throw IllegalAccessException("drawLine is allowed between beginDraw()/endDraw() only")
        }
        out.println(
            "<line fromX=${start.x} fromY=${start.y} toX=${end.x} toY=${end.y}>\n" +
                    "  <color r=${color.r} g=${color.g} b=${color.b} a=${color.a} />\n" +
                    "</line>"
        )
    }

    fun endDraw() {
        if (!isDrawing) {
            throw IllegalAccessException("Drawing has not been started")
        }
        out.println("</draw>")
        isDrawing = false
    }

    override fun close() {
        if (isDrawing) {
            endDraw()
        }
    }
}