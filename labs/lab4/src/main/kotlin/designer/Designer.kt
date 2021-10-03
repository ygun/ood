package designer

import factory.IShapeFactory
import picture.PictureDraft
import shape.Shape
import java.io.InputStream
import java.util.*

class Designer(private val factory: IShapeFactory) : IDesigner {

    companion object {
        const val END: String = "end"
    }

    override fun createDraft(inputStream: InputStream): PictureDraft {
        val input = Scanner(inputStream)
        val shapes = LinkedList<Shape>()

        var line = ""
        while (line != END) {
            line = input.nextLine()
            if (line != END) {
                shapes.add(factory.createShape(line))
            }
        }
        return PictureDraft(shapes)
    }
}