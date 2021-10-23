package shape

import shape.style.BLACK_FILL
import shape.style.BLACK_STROKE
import shape.style.Fill
import shape.style.Stroke

abstract class Shape(
    private var fill: Fill?,
    private var stroke: Stroke?
) : IShape {

    override fun getFill() = fill
    override fun setFill(fill: Fill?) {
        this.fill = fill
    }

    override fun getStroke() = stroke
    override fun setStroke(stroke: Stroke?) {
        this.stroke = stroke
    }


    fun getStyleSvg(): String = getFillSvg() + " " + getStrokeSvg()

    private fun getFillSvg(): String {
        return when {
            fill != null -> fill!!.svgStyle()
            else -> BLACK_FILL.svgStyle()
        }
    }

    private fun getStrokeSvg(): String {
        return when {
            stroke != null -> stroke!!.svgStyle()
            else -> BLACK_STROKE.svgStyle()
        }
    }
}