import canvas.Canvas
import designer.Designer
import factory.ShapeFactory
import painter.Painter

fun main() {
    try {
        val factory = ShapeFactory()
        val designer = Designer(factory)

        val draft = designer.createDraft(System.`in`)
        val canvas = Canvas(System.out)
        val painter = Painter()
        painter.drawPictures(draft, canvas)
    } catch (e: Exception) {
        println(e.message)
    }
}