package factory

import shape.*

class ShapeFactory : IShapeFactory {

    companion object {
        const val RECTANGLE = "rectangle"
        const val TRIANGLE = "triangle"
        const val ELLIPSE = "ellipse"
        const val REGULAR_POLYGON = "regular-polygon"
    }

    override fun createShape(description: String): Shape {
        if (description.isEmpty()) {
            throw IllegalArgumentException("Description should not be empty")
        }

        val splitDescription = description.split(" ")

        val shapeName = splitDescription[0]
        val params = splitDescription.subList(1, splitDescription.size)
        return when (shapeName) {
            RECTANGLE -> createRectangle(params)
            TRIANGLE -> createTriangle(params)
            ELLIPSE -> createEllipse(params)
            REGULAR_POLYGON -> createRegularPolygon(params)
            else -> throw IllegalArgumentException("Not valid shapeName: $shapeName")
        }
    }

    private fun createRectangle(params: List<String>): Shape {
        if (params.size != 5) {
            throw IllegalArgumentException("Not correct parameters for Rectangle")
        }

        return Rectangle(
            Color.getShape(params[0]),
            Point(params[1].toDouble(), params[2].toDouble()),
            Point(params[3].toDouble(), params[4].toDouble())
        )
    }

    private fun createTriangle(params: List<String>): Shape {
        if (params.size != 7) {
            throw IllegalArgumentException("Not correct parameters for Triangle")
        }

        return Triangle(
            Color.getShape(params[0]),
            Point(params[1].toDouble(), params[2].toDouble()),
            Point(params[3].toDouble(), params[4].toDouble()),
            Point(params[5].toDouble(), params[6].toDouble())
        )
    }

    private fun createEllipse(params: List<String>): Shape {
        if (params.size != 5) {
            throw IllegalArgumentException("Not correct parameters for Ellipse")
        }

        return Ellipse(
            Color.getShape(params[0]),
            Point(params[1].toDouble(), params[2].toDouble()),
            params[3].toInt(),
            params[4].toInt()
        )
    }

    private fun createRegularPolygon(params: List<String>): Shape {
        if (params.size != 5) {
            throw IllegalArgumentException("Not correct parameters for RegularPolygon")
        }

        return RegularPolygon(
            Color.getShape(params[0]),
            Point(params[1].toDouble(), params[2].toDouble()),
            params[3].toInt(),
            params[4].toInt()
        )
    }
}