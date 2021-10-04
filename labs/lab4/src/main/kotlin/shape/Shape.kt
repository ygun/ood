package shape

import canvas.ICanvas

abstract class Shape(private var color: Color) {

    fun getColor() = color

    fun setColor(color: Color) {
        this.color = color
    }

    abstract fun draw(canvas: ICanvas)
}