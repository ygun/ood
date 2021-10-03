package shape

import canvas.ICanvas
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

class RegularPolygon(
    color: Color,
    private val center: Point,
    private val radius: Int,
    private val vertexCount: Int
) : Shape(color) {

    init {
        if (!isPolygonValid()) {
            throw IllegalArgumentException("Not valid Regular Polygon")
        }
    }

    private fun isPolygonValid() = vertexCount > 0 && radius > 0

    override fun draw(canvas: ICanvas): String {
        var res = ""
        val angle = 2 * Math.PI / vertexCount

        var firstPoint: Point? = null
        var prevPoint: Point? = null
        for (i in 0..vertexCount) {
            val x = center.x + radius * sin((i * angle))
            val y = center.y + radius * cos((i * angle))
            val nextPoint = Point(x, y)

            if (prevPoint != null) {
                res += canvas.drawLine(prevPoint, nextPoint)
            } else {
                firstPoint = nextPoint
            }

            prevPoint = nextPoint
        }
        if (prevPoint != null && firstPoint != null)
            return res + canvas.drawLine(prevPoint, firstPoint)
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RegularPolygon

        if (center != other.center) return false
        if (radius != other.radius) return false
        if (vertexCount != other.vertexCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = center.hashCode()
        result = 31 * result + radius
        result = 31 * result + vertexCount
        return result
    }


}