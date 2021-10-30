package canvas

import shape.Ellipse
import shape.IShape
import shape.Rectangle
import shape.frame.Point
import shape.style.RGBA

interface ICanvas : AutoCloseable {
    fun drawLine(from: Point, to: Point)
    fun fillPolygon(points: List<Point>)

    fun drawRectangle(leftTop: Point, weight: Double, height: Double, styleStr: String = "")
    fun fillRectangle(rectangle: Rectangle)

    fun drawEllipse(center: Point, radiusX: Double, radiusY: Double, styleStr: String = "")
    fun fillEllipse(ellipse: Ellipse)

    fun drawGroup(shapes: List<IShape>, styleStr: String = "")
}