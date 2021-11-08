package shape.style

class Fill(
    val color: RGBA? = WHITE,
    var isEnable: Boolean = true
) {

    override fun hashCode(): Int = "$color".hashCode()

    override fun toString(): String = "Fill(color=$color)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Fill

        if (color != other.color) return false

        return true
    }
}