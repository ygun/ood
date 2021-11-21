package document.element.paragraph

class Paragraph(
    private var text: String
) : IParagraph {

    override fun setText(text: String) {
        this.text = text
    }

    override fun getText(): String = text


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Paragraph

        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int = text.hashCode()

    override fun toString(): String = "Paragraph: ${getText()}"
}