package factory

import shape.Shape

interface IShapeFactory {
    fun createShape(description: String): Shape
}