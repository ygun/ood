package canvas

import shape.Ellipse
import shape.IShape
import shape.Rectangle
import shape.frame.Point
import shape.style.*
import java.io.PrintStream

class CanvasSVG(private val output: PrintStream) : ICanvas, AutoCloseable {

    var lineColor: RGBA = BLACK
    var fillColor: RGBA = WHITE
    var lineWith: Double = 1.0

    init {
        output.println("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"100%\" height=\"100%\">")
    }

    override fun drawLine(from: Point, to: Point) {
        output.println(
            "<line x1=\"${from.x}\" y1=\"${from.y}\" x2=\"${to.x}\" y2=\"${to.y}\" " +
                    "stroke=\"$lineColor\" stroke-width=\"$lineWith\"/>"
        )
    }

    override fun fillPolygon(points: List<Point>) {
        val pointsStr = points.joinToString(" ") {
            it.x.toInt().toString() + "," + it.y.toInt().toString()
        }
        output.println(
            "  <polygon points=\"$pointsStr\"" +
                    " fill=\"$fillColor\" stroke=\"$lineColor\" stroke-width=\"$lineWith\" />"
        )
    }

    override fun drawRectangle(
        leftTop: Point,
        weight: Double,
        height: Double,
        fill: Fill,
        stroke: Stroke
    ) {
        output.println(
            "<rect x=\"${leftTop.x}\" y=\"${leftTop.y}\" width=\"$weight\" height=\"$height\" " +
                    " ${getStyle(fill, stroke)}/>"
        )
    }

    override fun fillRectangle(rectangle: Rectangle) = rectangle.setFill(Fill(fillColor))

    override fun drawEllipse(
        center: Point,
        radiusX: Double,
        radiusY: Double,
        fill: Fill,
        stroke: Stroke
    ) {
        output.println(
            "<ellipse cx=\"${center.x}\" cy=\"${center.y}\" rx=\"${radiusX}\" " +
                    "ry=\"${radiusY}\" ${getStyle(fill, stroke)}/>"
        )
    }

    override fun fillEllipse(ellipse: Ellipse) = ellipse.setFill(Fill(fillColor))

    override fun drawGroup(shapes: List<IShape>, fill: Fill, stroke: Stroke) {
        output.println("<g ${getStyle(fill, stroke)} >")
        shapes.forEach {
            output.print("\t")
            it.draw(this)
        }
        output.println("</g>")
    }

    override fun close() = output.println("</svg>")

    private fun getStyle(fill: Fill, stroke: Stroke): String {
        var res = ""
        if (fill.isEnable && fill.color != null) {
            res += " fill=\"${fill.color}\" "
        }
        if (stroke.isEnable && stroke.color != null) {
            res += " stroke=\"${stroke.color}\" stroke-width=\"${stroke.width}\" "
        }
        return res
    }
}