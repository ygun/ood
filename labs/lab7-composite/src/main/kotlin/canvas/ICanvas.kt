package canvas

import shape.Ellipse
import shape.IShape
import shape.frame.Point
import shape.style.RGBA

interface ICanvas : AutoCloseable {
    fun draw(shape: IShape)

    fun drawLine(from: Point, to: Point)
    fun fillPolygon(points: List<Point>)

    fun drawEllipse(ellipse: Ellipse)
    fun fillEllipse(ellipse: Ellipse)
}