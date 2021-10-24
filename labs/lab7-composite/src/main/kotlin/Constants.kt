import shape.Ellipse
import shape.Rectangle
import shape.frame.Point
import shape.style.Fill
import shape.style.RGBA
import shape.style.Stroke

val DEFAULT_RECTANGLE = Rectangle(
    Point(0.0, 0.0),
    100.0,
    100.0
)

val RECTANGLE_WITH_STYLE = Rectangle(
    Point(0.0, 0.0),
    100.0,
    100.0,
    Fill(RGBA(2, 3, 4, 0.5)),
    Stroke(RGBA(20, 3, 2, 1.0), 10)
)

val DEFAULT_ELLIPSE = Ellipse(Point(400.0, 400.0), 150.0, 150.0)

val ELLIPSE_WITH_STYLE = Ellipse(
    Point(400.0, 400.0), 150.0, 150.0,
    Fill(RGBA(2, 3, 4, 0.5)),
    Stroke(RGBA(20, 3, 2, 1.0), 10)
)