package observer.pro

enum class WindDirection {
    NORTH {
        override fun toString(): String = "direction to north the direction angle is 0"
        override fun getDegrees(): Int = 0
    },
    EAST {
        override fun toString(): String = "direction to east the direction angle is 90"
        override fun getDegrees(): Int = 90
    },
    SOUTH {
        override fun toString(): String = "direction to south the direction angle is 180"
        override fun getDegrees(): Int = 180
    },
    WEST {
        override fun toString(): String = "direction to west the direction angle is 270"
        override fun getDegrees(): Int = 270
    };

    abstract fun getDegrees(): Int
}