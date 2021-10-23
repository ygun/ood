package shape.style

class RGBA(
    var red: Int,
    var green: Int,
    var blue: Int,
    var alpha: Double
) {
    override fun toString(): String = "rgba($red,$green,$blue,$alpha)"
}