package canvas

import shape.Color
import shape.Point
import java.io.PrintStream

class Canvas(
    private val output: PrintStream = System.out,
    private var color: Color = Color.BLACK
) : ICanvas {

    override fun setColor(color: Color) {
        this.color = color
    }

    override fun drawLine(from: Point, to: Point): String {
        val line = "<line color=${color.getHex()} fromX=${String.format("%.2f", from.x)} fromY=${String.format("%.2f", from.y)}" +
                " toX=${String.format("%.2f", to.x)} toY=${String.format("%.2f", to.y)}>\n"
        output.print(line)
        return line
    }

    override fun drawEllipse(center: Point, majorRadius: Int, minorRadius: Int): String {
        if (majorRadius <= 0 || minorRadius <= 0)
            throw IllegalArgumentException("Radius cannot be less or equals to zero")

        val ellipse = "<ellipse color=${color.getHex()} centerX=${String.format("%.2f", center.x)} " +
                "centerY=${String.format("%.2f", center.y)} majorRadius=$majorRadius minorRadius=$minorRadius>\n"
        output.print(ellipse)
        return ellipse
    }
}