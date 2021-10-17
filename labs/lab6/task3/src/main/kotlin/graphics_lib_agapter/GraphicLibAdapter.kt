package graphics_lib_agapter

import graphics_lib.ICanvas
import modern_graphics_lib.ModernGraphicsRenderer
import modern_graphics_lib.Point
import modern_graphics_lib.RGBAColor
import java.awt.Color




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
        val convertedColor: Color = Color.decode(color.toString())

        this.color = RGBAColor(
            convertedColor.red,
            convertedColor.green,
            convertedColor.blue,
            convertedColor.alpha
        )
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