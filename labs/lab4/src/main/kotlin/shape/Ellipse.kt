package shape

import canvas.ICanvas

class Ellipse(
    color: Color,
    private val center: Point,
    private val majorRadius: Int,
    private val minorRadius: Int
) : Shape(color) {

    init {
        if (!isEllipseValid()) {
            throw IllegalArgumentException("Radius cannot be less or equals to zero")
        }
    }

    private fun isEllipseValid() = majorRadius > 0 && minorRadius > 0

    override fun draw(canvas: ICanvas) = canvas.drawEllipse(center, majorRadius, minorRadius)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ellipse

        if (center != other.center) return false
        if (majorRadius != other.majorRadius) return false
        if (minorRadius != other.minorRadius) return false

        return true
    }

    override fun hashCode(): Int {
        var result = center.hashCode()
        result = 31 * result + majorRadius
        result = 31 * result + minorRadius
        return result
    }


}