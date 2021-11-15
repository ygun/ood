package graphics_lib_agapter

import graphics_lib.ICanvas
import modern_graphics_lib.ModernGraphicsRenderer
import modern_graphics_lib.Point

open class Coordinates(
    private var x: Int,
    private var y: Int
) {
    fun point(): Point = Point(x, y)
}

class GraphicLibAdapter(
    private val renderer: ModernGraphicsRenderer
) : ICanvas, AutoCloseable {

    private var coordinates = Coordinates(0, 0)

    init {
        renderer.beginDraw()
    }

    override fun moveTo(x: Int, y: Int) {
        coordinates = Coordinates(x, y)
    }

    override fun lineTo(x: Int, y: Int) {
        renderer.drawLine(
            coordinates.point(),
            Point(x, y)
        )
    }

    override fun close() = renderer.close()
}