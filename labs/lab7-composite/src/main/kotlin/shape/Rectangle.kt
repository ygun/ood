package shape

import canvas.ICanvas
import shape.frame.Frame
import shape.frame.Point
import shape.style.Fill
import shape.style.Stroke

class Rectangle(
    private var leftTop: Point,
    private var weight: Double,
    private var height: Double,
    fill: Fill = Fill(),
    stroke: Stroke = Stroke()
) : Shape(fill, stroke) {

    override fun getFrame() = Frame(leftTop, weight, height)

    override fun setFrame(frame: Frame) {
        leftTop = frame.leftTop
        weight = frame.width
        height = frame.height
    }

    override fun draw(canvas: ICanvas) = canvas.drawRectangle(leftTop, weight, height, getFill(), getStroke())
}