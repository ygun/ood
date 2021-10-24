package slide

import canvas.IDrawable
import shape.IShape

interface ISlide : IDrawable{

    fun getShapesCount(): Int
    fun insertShape(shape: IShape, position: Int = Int.MAX_VALUE)

    fun getShape(position: Int): IShape
    fun removeShape(position: Int)
}