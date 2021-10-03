package shape

import canvas.ICanvas

class Rectangle(
    color: Color,
    private val leftTop: Point,
    private val rightBottom: Point
) : Shape(color) {

    init {
        if (!ifRectangleValid(leftTop, rightBottom)) {
            throw IllegalArgumentException("Not valid coordinates, Rectangle cannot be a point")
        }
    }

    private fun ifRectangleValid(leftTop: Point, rightBottom: Point): Boolean {
        return leftTop.x != rightBottom.x && leftTop.y != rightBottom.y
    }

    override fun draw(canvas: ICanvas): String =
        canvas.drawLine(leftTop, Point(rightBottom.x, leftTop.y)) +
                canvas.drawLine(Point(rightBottom.x, leftTop.y), rightBottom) +
                canvas.drawLine(rightBottom, Point(leftTop.x, rightBottom.y)) +
                canvas.drawLine(Point(leftTop.x, rightBottom.y), leftTop)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rectangle

        if (leftTop != other.leftTop) return false
        if (rightBottom != other.rightBottom) return false

        return true
    }

    override fun hashCode(): Int {
        var result = leftTop.hashCode()
        result = 31 * result + rightBottom.hashCode()
        return result
    }
}