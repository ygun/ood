package shape

import shape.frame.Frame
import shape.frame.Point
import shape.style.BLACK_STROKE
import shape.style.Fill
import shape.style.Stroke
import shape.style.WHITE_FILL

class Ellipse(
    private var center: Point,
    private var radiusX: Double,
    private var radiusY: Double,
    fill: Fill? = WHITE_FILL,
    stroke: Stroke? = BLACK_STROKE
) : Shape(fill, stroke) {

    override fun getFrame(): Frame {
        val leftTop = Point(center.x - radiusX, center.y - radiusY)
        return Frame(leftTop, radiusX * 2, radiusY * 2)
    }

    override fun setFrame(frame: Frame) {
        center = Point(frame.leftTop.x + frame.width / 2, frame.leftTop.y + frame.height / 2)
        radiusX = frame.width / 2
        radiusY = frame.height / 2
    }

    override fun getAsSvg(): String =
        "<ellipse cx=\"${center.x}\" cy=\"${center.y}\" rx=\"$radiusX\" ry=\"$radiusY\" ${getStyleSvg()}/>"
}