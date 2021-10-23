package shape

import canvas.Canvas
import shape.frame.Frame
import shape.frame.Point
import shape.style.Fill
import shape.style.Stroke

class Rectangle(
    private var leftTop: Point,
    private var weight: Double,
    private var height: Double,
    fill: Fill? = null,
    stroke: Stroke? = null
) : Shape(fill, stroke) {

    override fun getFrame() = Frame(leftTop, weight, height)

    override fun setFrame(frame: Frame) {
        leftTop = frame.leftTop
        weight = frame.width
        height = frame.height
    }

    override fun draw(canvas: Canvas) = canvas.draw(this.getAsSvg())

    override fun getAsSvg(): String =
        "<rect x=\"${leftTop.x}\" y=\"${leftTop.y}\" width=\"$weight\" height=\"$height\" ${getStyleSvg()}/>"
}