package shape.frame

class Frame(val leftTop: Point, val width: Double, val height: Double) {
    init {
        if (width <= 0 || height <= 0) {
            throw IllegalArgumentException("Width and height in frame are not able to be less or equals to zero")
        }
    }

    override fun toString(): String = "Frame(leftTop=$leftTop, width=$width, height=$height)"
}