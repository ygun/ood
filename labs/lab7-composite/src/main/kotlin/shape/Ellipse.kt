package shape

import canvas.ICanvas
import shape.frame.Frame
import shape.frame.Point
import shape.style.Fill
import shape.style.Stroke

class Ellipse(
    private var center: Point,
    private var radiusX: Double,
    private var radiusY: Double,
    fill: Fill = Fill(),
    stroke: Stroke = Stroke()
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

    override fun draw(canvas: ICanvas) = canvas.drawEllipse(center, radiusX, radiusY, getFill(), getStroke())
}