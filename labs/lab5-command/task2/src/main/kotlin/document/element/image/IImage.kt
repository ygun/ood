package document.element.image

import java.nio.file.Path

interface IImage {

    fun getPath(): Path

    fun getWidth(): Int

    fun getHeight(): Int

    fun resize(width: Int, height: Int)
}