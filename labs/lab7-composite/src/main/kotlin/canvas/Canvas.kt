package canvas

import shape.Ellipse
import shape.IShape
import shape.frame.Point
import shape.style.BLACK
import shape.style.Fill
import shape.style.RGBA
import shape.style.WHITE
import java.io.PrintStream

class Canvas(private val output: PrintStream) : ICanvas, AutoCloseable {

    var lineColor: RGBA = BLACK
    var fillColor: RGBA = WHITE
    var lineWith: Double = 1.0

    init {
        output.println("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"100%\" height=\"100%\">")
    }

    override fun draw(shape: IShape) = output.println("  " + shape.getAsSvg())

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
        println(pointsStr)
        output.println("  <polygon points=\"$pointsStr\"" +
                " fill=\"$fillColor\" stroke=\"$lineColor\" stroke-width=\"$lineWith\" />")
    }

    override fun drawEllipse(ellipse: Ellipse) = output.println(ellipse.getAsSvg())

    override fun fillEllipse(ellipse: Ellipse) = ellipse.setFill(Fill(fillColor))

    override fun close() = output.println("</svg>")
}