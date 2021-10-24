import canvas.Canvas
import shape.Ellipse
import shape.IShape
import shape.Rectangle
import shape.frame.Point
import shape.group.ShapeGroup
import shape.style.BLACK_STROKE
import shape.style.Fill
import shape.style.RGBA
import shape.style.Stroke
import slide.Slide
import java.io.File
import java.io.FileOutputStream
import java.io.PrintStream

const val PATH_TO_FOLDER = "D:/GitHub/ood/labs//lab7-composite/src/test/svg/"

fun main() {

    writeSoloSvgs()

    writeSlide()

    fillPolygon("polygon.svg")
}

fun writeSlide() {
    val slide = Slide(1000.0, 1000.0)

    val rectangle = Rectangle(Point(50.0, 20.0), 150.0, 150.0, null, null)
    val ellipse = Ellipse(Point(200.0, 200.0), 150.0, 150.0, null, null)

    val shapeGroup = ShapeGroup()
    shapeGroup.insertShape(rectangle, 0)
    shapeGroup.insertShape(ellipse)

    shapeGroup.setFill(Fill(RGBA(255, 250, 20, 1.0)))
    shapeGroup.setStroke(Stroke(RGBA(255, 20, 255, 1.0), 4))

    slide.insertShape(shapeGroup)
    val rectangle2 = Rectangle(Point(500.0, 200.0), 150.0, 150.0, null, null)
    slide.insertShape(rectangle2)

    val output = PrintStream(File(PATH_TO_FOLDER + "slide.svg"))
    val canvas = Canvas(output)

    slide.draw(canvas)
}

fun writeSoloSvgs() {
    val rectangle = Rectangle(Point(50.0, 20.0), 150.0, 150.0)
    writeShapeToSvg(rectangle, "rectangle.svg")

    val rectangleWithStyle = Rectangle(
        Point(0.0, 0.0),
        1000.0,
        100.0,
        Fill(RGBA(2, 3, 4, 0.5)),
        Stroke(RGBA(20, 3, 2, 1.0), 10)
    )
    writeShapeToSvg(rectangleWithStyle, "rectangleWithStyle.svg")

    val ellipse = Ellipse(Point(200.0, 200.0), 150.0, 150.0)
    writeShapeToSvg(ellipse, "ellipse.svg")

    val ellipseWithStyle = Ellipse(
        Point(400.0, 400.0), 150.0, 150.0,
        Fill(RGBA(2, 3, 4, 0.5)),
        Stroke(RGBA(20, 3, 2, 1.0), 10)
    )
    writeShapeToSvg(ellipseWithStyle, "ellipseWithStyle.svg")
}

fun writeShapeToSvg(shape: IShape, fileName: String) {
    val output = PrintStream(File(PATH_TO_FOLDER + fileName))
    Canvas(output).use {
        it.draw(shape)
    }
}

private fun fillPolygon(fileName: String) {
    val points = mutableListOf(Point(0.0, 100.0), Point(50.0, 25.0), Point(50.0, 75.0), Point(100.0, 0.0))
    val writeToFile = PrintStream(FileOutputStream(PATH_TO_FOLDER + fileName))
    Canvas(writeToFile).use {
        it.fillPolygon(points)
    }
}