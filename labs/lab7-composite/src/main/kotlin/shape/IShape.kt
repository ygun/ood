package shape

import canvas.ICanvas
import shape.frame.Frame
import shape.style.Fill
import shape.style.Stroke

interface IShape {

    fun getFrame(): Frame?
    fun setFrame(frame: Frame)

    fun getFill(): Fill?
    fun setFill(fill: Fill)

    fun getStroke(): Stroke?
    fun setStroke(stroke: Stroke)

    fun draw(canvas: ICanvas)

    fun getStyle(): String
}