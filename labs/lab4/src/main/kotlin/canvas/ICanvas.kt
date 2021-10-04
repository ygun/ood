package canvas

import shape.Color
import shape.Point

interface ICanvas {
    fun setColor(color: Color)
    fun drawLine(from: Point, to: Point)
    fun drawEllipse(center: Point, majorRadius: Int, minorRadius: Int)
}