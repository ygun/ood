import canvas.CanvasSVG
import shape.Ellipse
import shape.IShape
import shape.Rectangle
import shape.frame.Frame
import shape.frame.Point
import shape.group.ShapeGroup
import shape.style.*
import slide.Slide
import java.io.File
import java.io.FileOutputStream
import java.io.PrintStream

const val PATH_TO_FOLDER = "D:/GitHub/ood/labs//lab7-composite/src/test/svg/"

fun main() {

    writeSoloSvgs()

    writeSlide()

    fillPolygon("polygon.svg")

    test()
}

fun test() {
    val slide = Slide(1000.0, 1000.0)

    val shapeGroup1 = ShapeGroup()
    val rectangle1 = Rectangle(Point(50.0, 20.0), 150.0, 150.0)
    val ellipse1 = Ellipse(Point(200.0, 200.0), 150.0, 150.0)
    shapeGroup1.insertShape(rectangle1)
    shapeGroup1.insertShape(ellipse1)

    val shapeGroup2 = ShapeGroup()
    val rectangle2 = Rectangle(Point(400.0, 30.0), 40.0, 30.0)
    shapeGroup1.insertShape(rectangle2)

    shapeGroup1.setFill(Fill(RGBA(255, 50, 20, 1.0)))
    shapeGroup1.setFrame(Frame(Point(0.0, 0.0), 30.0, 70.0))

    //shapeGroup1.insertShape(shapeGroup2,0)

    shapeGroup2.insertShape(shapeGroup1)
    /*val tempShapeGroup = shapeGroup1.getShape(0)
    if (tempShapeGroup is ShapeGroup) {
        tempShapeGroup.insertShape(shapeGroup1)
    }*/
    //shapeGroup1.insertShape(shapeGroup2)

    slide.insertShape(shapeGroup1)

    val output = PrintStream(File(PATH_TO_FOLDER + "test.svg"))
    CanvasSVG(output).use { slide.draw(it) }
}

fun writeSlide() {
    val slide = Slide(1000.0, 1000.0)

    val rectangle = Rectangle(Point(50.0, 20.0), 150.0, 150.0)
    val ellipse = Ellipse(Point(200.0, 200.0), 150.0, 150.0)
    rectangle.setFill(Fill(RGBA(255, 50, 20, 1.0)))
    ellipse.setFill(Fill(RGBA(255, 250, 20, 1.0)))

    val shapeGroup = ShapeGroup()
    shapeGroup.insertShape(rectangle, 0)
    shapeGroup.insertShape(ellipse)

    shapeGroup.setStroke(Stroke(RGBA(255, 20, 255, 1.0), 4))
    shapeGroup.setFill(Fill(RGBA(20, 20, 255, 1.0)))
    shapeGroup.setFill(Fill(null))
    shapeGroup.setFrame(Frame(Point(50.0, 20.0), 150.0, 150.0))

    slide.insertShape(shapeGroup)
    val rectangle2 = Rectangle(Point(500.0, 200.0), 150.0, 150.0)
    slide.insertShape(rectangle2)

    val output = PrintStream(File(PATH_TO_FOLDER + "slide.svg"))
    CanvasSVG(output).use { slide.draw(it) }
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

    val ellipse = Ellipse(Point(200.0, 200.0), 150.0, 150.0, Fill(WHITE), Stroke(BLACK, 1))
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
    CanvasSVG(output).use(shape::draw)
}

private fun fillPolygon(fileName: String) {
    val points = mutableListOf(Point(0.0, 100.0), Point(50.0, 25.0), Point(50.0, 75.0), Point(100.0, 0.0))
    val writeToFile = PrintStream(FileOutputStream(PATH_TO_FOLDER + fileName))
    CanvasSVG(writeToFile).use {
        it.fillPolygon(points)
    }
}