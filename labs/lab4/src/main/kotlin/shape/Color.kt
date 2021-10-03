package shape

enum class Color {
    GREEN {
        override fun getHex(): String = "#00FF00"
    },
    RED {
        override fun getHex(): String = "#FF0000"
    },
    BLUE {
        override fun getHex(): String = "#0000FF"
    },
    YELLOW {
        override fun getHex(): String = "#FFFF00"
    },
    PINK {
        override fun getHex(): String = "#FFC0CB"
    },
    BLACK {
        override fun getHex(): String = "#000000"
    };

    abstract fun getHex(): String

    companion object {
        fun getShape(name: String): Color {
            return when (name) {
                "green" -> Color.GREEN
                "red" -> Color.RED
                "blue" -> Color.BLUE
                "yellow" -> Color.YELLOW
                "pink" -> Color.PINK
                "black" -> Color.BLACK
                else -> throw IllegalArgumentException("Not correct color: $name")
            }
        }
    }
}