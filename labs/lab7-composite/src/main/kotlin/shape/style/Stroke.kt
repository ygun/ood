package shape.style

class Stroke(
    var color: RGBA? = BLACK,
    var width: Int = 1,
    var isEnable: Boolean = true
){

    override fun toString(): String = "Stroke(color=$color, width=$width)"

    override fun hashCode(): Int = "$color $width".hashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Stroke

        if (color != other.color) return false
        if (width != other.width) return false

        return true
    }
}