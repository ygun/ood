package shape

import shape.style.Fill
import shape.style.Stroke

abstract class Shape(
    private var fill: Fill = Fill(),
    private var stroke: Stroke = Stroke()
) : IShape {

    override fun getFill() = fill
    override fun setFill(fill: Fill) {
        this.fill = fill
    }

    override fun getStroke() = stroke
    override fun setStroke(stroke: Stroke) {
        this.stroke = stroke
    }
}