package picture

import shape.Shape

class PictureDraft(private var shapes: List<Shape>) {

    fun getShapeCount(): Int = shapes.size

    fun getShape(index: Int): Shape {
        if (index >= shapes.size) {
            throw IllegalArgumentException("Not valid index: $index")
        }
        return shapes[index]
    }

    fun getShapeIterator() = shapes.iterator()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PictureDraft

        if (shapes != other.shapes) return false
        return true
    }

    override fun hashCode(): Int = shapes.hashCode()
}