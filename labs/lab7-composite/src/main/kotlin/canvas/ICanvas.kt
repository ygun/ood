package canvas

import shape.Ellipse
import shape.IShape
import shape.Rectangle
import shape.frame.Point
import shape.style.Fill
import shape.style.Stroke

interface ICanvas {
    fun drawLine(from: Point, to: Point)
    fun fillPolygon(points: List<Point>)

    fun drawRectangle(
        leftTop: Point,
        weight: Double,
        height: Double,
        fill: Fill,
        stroke: Stroke
    )

    fun fillRectangle(rectangle: Rectangle)

    fun drawEllipse(
        center: Point,
        radiusX: Double,
        radiusY: Double,
        fill: Fill,
        stroke: Stroke
    )

    fun fillEllipse(ellipse: Ellipse)

    fun drawGroup(shapes: List<IShape>, fill: Fill, stroke: Stroke)
}