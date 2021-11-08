package shape.group

import canvas.ICanvas
import shape.IShape
import shape.Shape
import shape.frame.Frame
import shape.frame.Point
import shape.style.Fill
import shape.style.Stroke
import java.util.*
import kotlin.math.max
import kotlin.math.min

class ShapeGroup(
    fill: Fill = Fill(null),
    stroke: Stroke = Stroke(null)
) : IShapeGroup, Shape(fill, stroke), Comparable<IShapeGroup> {

    private val shapes = mutableListOf<IShape>()

    override fun getShapesCount(): Int = shapes.size

    override fun insertShape(shape: IShape, position: Int) {
        if (shape is IShapeGroup && isNestedShapeGroup(shape)) {
            throw IllegalArgumentException("Shape: $shape, is nested")
        }
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

    override fun getFrame(): Frame? {
        if (shapes.isEmpty()) return null

        var minX = 0.0
        var minY = 0.0
        var maxX = 0.0
        var maxY = 0.0
        var isInit = false

        shapes.forEach {
            val shapeFrame = it.getFrame()
            if (shapeFrame != null) {

                if (!isInit) {
                    minX = shapeFrame.leftTop.x
                    minY = shapeFrame.leftTop.y
                    maxX = minX + shapeFrame.width
                    maxY = minY + shapeFrame.height
                    isInit = true
                } else {
                    minX = min(minX, shapeFrame.leftTop.x)
                    minY = min(minY, shapeFrame.leftTop.y)

                    maxX = max(maxX, shapeFrame.leftTop.x + shapeFrame.width)
                    maxY = max(maxY, shapeFrame.leftTop.y + shapeFrame.height)
                }
            }
        }
        return if (isInit) Frame(Point(minX, minY), maxX - minX, maxY - minY) else null
    }

    override fun setFrame(frame: Frame) {
        val currFrame: Frame = getFrame() ?: return
        shapes.forEach {
            val shapeFrame = it.getFrame()
            if (shapeFrame != null) {
                val newLeft: Double =
                    frame.leftTop.x + (shapeFrame.leftTop.x - currFrame.leftTop.x) / currFrame.width * frame.width
                val newTop: Double =
                    frame.leftTop.y + (shapeFrame.leftTop.y - currFrame.leftTop.y) / currFrame.height * frame.height
                val newWidth: Double = shapeFrame.width / currFrame.width * frame.width
                val newHeight: Double = shapeFrame.height / currFrame.height * frame.height

                it.setFrame(Frame(Point(newLeft, newTop), newWidth, newHeight))
            }
        }
    }

    override fun draw(canvas: ICanvas) = canvas.drawGroup(shapes.toMutableList(), getFill(), getStroke())

    override fun isNestedShapeGroup(insertedGroup: IShapeGroup): Boolean {
        val setShapes = getNestedShapeGroups()
        val insertedSetShapes = insertedGroup.getNestedShapeGroups()
        return when {
            setShapes.contains(insertedGroup) || insertedSetShapes.contains(this) -> true
            else -> false
        }
    }

    override fun getNestedShapeGroups(): Set<ShapeGroup> {
        val setShapes = TreeSet<ShapeGroup>()
        shapes.forEach { shape ->
            if (shape is ShapeGroup) {
                setShapes.add(shape)
                setShapes.addAll(shape.getNestedShapeGroups())
            }
        }
        return setShapes
    }

    override fun compareTo(other: IShapeGroup): Int {
        if (this == other) {
            return 0
        }
        return when {
            getShapesCount() > other.getShapesCount() -> 1
            else -> -1
        }
    }

    override fun getFill(): Fill {
        val fill = super.getFill()
        if (fill.color != null) {
            return fill
        }

        var isAllEquals = true
        var tempFill = Fill(null)
        shapes.forEach {
            val shapeFill = it.getFill()
            if (shapeFill.color != null) {
                if (tempFill.color == null) {
                    tempFill = shapeFill
                } else {
                    isAllEquals = false
                }
            }
        }

        return if (isAllEquals) {
            setFill(tempFill)
            tempFill
        } else {
            fill
        }
    }

    override fun setFill(fill: Fill) {
        shapes.forEach { it.getFill().isEnable = false }
        super.setFill(fill)
    }

    override fun getStroke(): Stroke {
        val stroke = super.getStroke()
        if (stroke.color != null) {
            return stroke
        }

        var isAllEquals = true
        var tempStroke = Stroke(null)
        shapes.forEach {
            val shapeStroke = it.getStroke()
            if (shapeStroke.color != null) {
                if (tempStroke.color == null) {
                    tempStroke = shapeStroke
                } else if (shapeStroke != tempStroke) {
                    isAllEquals = false
                }
            }
        }

        return if (isAllEquals) {
            setStroke(tempStroke)
            tempStroke
        } else {
            stroke
        }
    }

    override fun setStroke(stroke: Stroke) {
        shapes.forEach { it.getStroke().isEnable = false }
        super.setStroke(stroke)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShapeGroup

        if (shapes != other.shapes) return false

        return true
    }

    override fun hashCode(): Int = shapes.hashCode()

    override fun toString(): String = "ShapeGroup(shapes=$shapes)"

    private fun ensurePositionIsValid(position: Int) {
        if (position < 0)
            throw IllegalArgumentException("Position($position) cannot be less than zero")
        if (position >= shapes.size)
            throw IllegalArgumentException("Position($position) is out of range")
    }
}
