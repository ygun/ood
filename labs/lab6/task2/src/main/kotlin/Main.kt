import app.paintPictureOnCanvas
import app.paintPictureOnModernGraphicsRenderer

fun main() {
    println("Should we use new API (y)?")
    val userAnswer = readLine()
    when {
        userAnswer.equals("y") || userAnswer.equals("Y") -> paintPictureOnModernGraphicsRenderer()
        else -> paintPictureOnCanvas()
    }
}