package shape

import canvas.ICanvas

class Triangle(
    color: Color,
    private val vertex1: Point,
    private val vertex2: Point,
    private val vertex3: Point
) : Shape(color) {

    init {
        if (!isTriangleValid(vertex1, vertex2, vertex3)) {
            throw IllegalArgumentException("Not valid triangle")
        }
    }

    private fun isTriangleValid(vertex1: Point, vertex2: Point, vertex3: Point): Boolean {
        val triangleArea: Double = vertex1.x * (vertex2.y - vertex3.y) +
                vertex2.x * (vertex3.y - vertex1.y) +
                vertex3.x * (vertex1.y - vertex2.y)
        return triangleArea != 0.0
    }

    override fun draw(canvas: ICanvas) = canvas.drawLine(vertex1, vertex2) +
            canvas.drawLine(vertex2, vertex3) +
            canvas.drawLine(vertex3, vertex1)


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Triangle

        if (vertex1 != other.vertex1) return false
        if (vertex2 != other.vertex2) return false
        if (vertex3 != other.vertex3) return false

        return true
    }

    override fun hashCode(): Int {
        var result = vertex1.hashCode()
        result = 31 * result + vertex2.hashCode()
        result = 31 * result + vertex3.hashCode()
        return result
    }
}