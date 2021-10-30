package shape

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

    override fun getStyle() = getFillStyle() + " " + getStrokeStyle()

    private fun getFillStyle() = when (fill) {
        null -> ""
        else -> fill!!.getStyle()
    }

    private fun getStrokeStyle() = when (stroke) {
        null -> ""
        else -> stroke!!.getStyle()
    }
}