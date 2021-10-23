package shape.style

class Fill(
    val color: RGBA,
    var isEnable: Boolean = true
) : Style {
    override fun svgStyle(): String = "fill=\"$color\""
}