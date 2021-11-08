package shape.style

class RGBA(
    var red: Int,
    var green: Int,
    var blue: Int,
    var alpha: Double
) {
    override fun toString(): String = "rgba($red,$green,$blue,$alpha)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RGBA

        if (red != other.red) return false
        if (green != other.green) return false
        if (blue != other.blue) return false
        if (alpha != other.alpha) return false

        return true
    }

    override fun hashCode(): Int = "$red $green $blue $alpha".hashCode()
}