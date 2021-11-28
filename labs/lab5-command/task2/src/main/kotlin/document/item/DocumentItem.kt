package document.item

import document.element.image.IImage
import document.element.paragraph.IParagraph

class DocumentItem(
    var image: IImage?,
    var paragraph: IParagraph?
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DocumentItem

        if (image != other.image) return false
        if (paragraph != other.paragraph) return false

        return true
    }

    override fun hashCode(): Int = "${image.hashCode()} ${paragraph.hashCode()}".hashCode()

    override fun toString(): String = if (image != null) image.toString() else paragraph.toString()
}