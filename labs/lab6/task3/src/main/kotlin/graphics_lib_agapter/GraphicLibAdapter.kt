package graphics_lib_agapter

import graphics_lib.ICanvas
import modern_graphics_lib.ModernGraphicsRenderer
import modern_graphics_lib.Point
import modern_graphics_lib.RGBAColor

open class Coordinates(
    private var x: Int,
    private var y: Int
) {
    fun point(): Point = Point(x, y)
}

class GraphicLibAdapter(
    private val renderer: ModernGraphicsRenderer
) : ICanvas {

    private var coordinates: Coordinates? = null
    private var color: RGBAColor? = null

    init {
        renderer.beginDraw()
    }

    override fun setColor(color: UInt) {
        val converter: (UInt) -> Float = { it: UInt -> ((it and 255u) / 255u).toFloat() }
        val r = ((color shr 16 and 255u) / (255u)).toFloat()
        val g = ((color shr 8 and 255u) / (255u)).toFloat()
        println(r)
        println(g)
        val teest = (color shr 16)

    /*this.color = RGBAColor(
            converter((color / 0x10000u).toInt()),
            converter((color / 0x100u % 0x100u).toInt()),
            converter((color % 0x100u).toInt())
        )*/
    }

    override fun moveTo(x: Int, y: Int) {
        coordinates = Coordinates(x, y)
    }

    override fun lineTo(x: Int, y: Int) {
        when {
            color != null -> renderer.drawLine(
                coordinates?.point() ?: throw RuntimeException("Please call moveTo before drawing"),
                Point(x, y),
                color!!
            )
            else -> renderer.drawLine(
                coordinates?.point() ?: throw RuntimeException("Please call moveTo before drawing"),
                Point(x, y)
            )
        }
    }
}