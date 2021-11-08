package slide

import canvas.ICanvas
import shape.IShape

class Slide(
    val width: Double,
    val height: Double
) : ISlide {

    private val shapes = mutableListOf<IShape>()

    override fun getShapesCount(): Int = shapes.size

    override fun insertShape(shape: IShape, position: Int) {
        if (position == Int.MAX_VALUE) {
            shapes.add(shape)
            return
        }
        if (position != 0) {
            ensurePositionIsValid(position)
        }
        shapes.add(position, shape)
    }

    override fun getShape(position: Int): IShape {
        ensurePositionIsValid(position)
        return shapes[position]
    }

    override fun removeShape(position: Int) {
        ensurePositionIsValid(position)
        shapes.removeAt(position)
    }

    override fun draw(canvas: ICanvas) {
        shapes.forEach { shape -> shape.draw(canvas) }
    }

    private fun ensurePositionIsValid(position: Int) {
        if (position < 0)
            throw IllegalArgumentException("Position($position) cannot be less than zero")
        if (position >= shapes.size)
            throw IllegalArgumentException("Position($position) is out of range")
    }
}