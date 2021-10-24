package shape

import shape.frame.Frame
import shape.frame.Point
import shape.style.BLACK_STROKE
import shape.style.Fill
import shape.style.Stroke
import shape.style.WHITE_FILL

class Rectangle(
    private var leftTop: Point,
    private var weight: Double,
    private var height: Double,
    fill: Fill? = WHITE_FILL,
    stroke: Stroke? = BLACK_STROKE
) : Shape(fill, stroke) {

    override fun getFrame() = Frame(leftTop, weight, height)

    override fun setFrame(frame: Frame) {
        leftTop = frame.leftTop
        weight = frame.width
        height = frame.height
    }

    override fun getAsSvg(): String =
        "<rect x=\"${leftTop.x}\" y=\"${leftTop.y}\" width=\"$weight\" height=\"$height\" ${getStyleSvg()}/>"
}