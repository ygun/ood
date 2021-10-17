package app

import graphics_lib.Canvas
import graphics_lib_agapter.GraphicLibAdapter
import modern_graphics_lib.ModernGraphicsRenderer
import shape_drawing_lib.CanvasPainter
import shape_drawing_lib.Point
import shape_drawing_lib.Rectangle
import shape_drawing_lib.Triangle
import java.io.PrintStream

fun paintPicture(painter: CanvasPainter, output: PrintStream = System.out) {
    val triangle = Triangle(Point(10, 15), Point(100, 200), Point(150, 250), 0x020F10u)
    val rectangle = Rectangle(Point(30, 40), 18, 24, 0x86001Au)

    painter.draw(triangle)
    output.println()
    painter.draw(rectangle)
}

fun paintPictureOnCanvas(output: PrintStream = System.out) {
    val canvas = Canvas(output)
    val painter = CanvasPainter(canvas)
    paintPicture(painter)
}

fun paintPictureOnModernGraphicsRenderer(output: PrintStream = System.out) {
    ModernGraphicsRenderer(output).use {
        val adapter = GraphicLibAdapter(it)

        val painter = CanvasPainter(adapter)

        paintPicture(painter, output)
    }
}