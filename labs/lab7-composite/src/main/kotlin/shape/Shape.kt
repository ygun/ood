package shape

import canvas.ICanvas
import shape.style.BLACK_STROKE
import shape.style.Fill
import shape.style.Stroke
import shape.style.WHITE_FILL

abstract class Shape(
    private var fill: Fill? = WHITE_FILL,
    private var stroke: Stroke? = BLACK_STROKE
) : IShape {

    override fun getFill() = fill
    override fun setFill(fill: Fill) {
        this.fill = fill
    }

    override fun getStroke() = stroke
    override fun setStroke(stroke: Stroke) {
        this.stroke = stroke
    }


    override fun draw(canvas: ICanvas) = canvas.draw(this)


    fun getStyleSvg(): String = getFillSvg() + " " + getStrokeSvg()

    private fun getFillSvg(): String = fill?.svgStyle() ?: ""

    private fun getStrokeSvg(): String = stroke?.svgStyle() ?: ""
}