package shape.style

class Fill(
    val color: RGBA,
    var isEnable: Boolean = true
) : Style {
    override fun getStyle(): String = " fill=\"$color\""
}