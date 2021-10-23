package shape.style

class Stroke(
    var color: RGBA,
    var width: Int,
    var isEnable: Boolean = true
) : Style {
    override fun svgStyle(): String = "stroke=\"$color\" stroke-width=\"$width\""
}