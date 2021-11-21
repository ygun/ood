package document.element.image

import java.nio.file.Path

class Image(
    private var path: Path,
    private var width: Int,
    private var height: Int
) : IImage {

    override fun getPath(): Path = path

    override fun getWidth() = width

    override fun getHeight() = height

    override fun resize(width: Int, height: Int) {
        this.width = width
        this.height = height
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        if (path != other.path) return false
        if (width != other.width) return false
        if (height != other.height) return false

        return true
    }

    override fun hashCode(): Int = "$path $width $height".hashCode()

    override fun toString(): String = "Image: ${getWidth()} ${getHeight()} ${getPath()}"
}