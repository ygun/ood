package shape.group

import shape.IShape

interface IShapeGroup : IShape {
    fun getShapesCount(): Int

    fun insertShape(shape: IShape, position: Int = Int.MAX_VALUE)
    fun getShape(position: Int): IShape
    fun removeShape(position: Int)
    fun isNestedShapeGroup(insertedGroup: IShapeGroup): Boolean
    fun getNestedShapeGroups(): Set<ShapeGroup>
}