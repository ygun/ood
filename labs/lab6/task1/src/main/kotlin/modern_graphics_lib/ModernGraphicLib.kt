package modern_graphics_lib

import java.io.PrintStream

open class Point(
    val x: Int,
    val y: Int
) {}

class ModernGraphicsRenderer(private val out: PrintStream = System.out) : AutoCloseable {

    private var isDrawing = false

    fun beginDraw() {
        if (isDrawing) {
            throw IllegalAccessException("Drawing has already begun")
        }
        out.println("<draw>")
        isDrawing = true
    }

    fun drawLine(start: Point = Point(0, 0), end: Point) {
        if (!isDrawing) {
            throw IllegalAccessException("drawLine is allowed between beginDraw()/endDraw() only")
        }
        out.println("<line fromX=${start.x} fromY=${start.y} toX=${end.x} toY=${end.y}>")
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