package util

enum class ContextFields {
    TEMPERATURE {
        override fun toString(): String = "temperature"
    },
    HUMIDITY {
        override fun toString(): String = "humidity"
    },
    PRESSURE {
        override fun toString(): String = "pressure"
    }
}