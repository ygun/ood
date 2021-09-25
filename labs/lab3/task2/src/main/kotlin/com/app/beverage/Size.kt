package com.app.beverage

enum class Size {
    SMALL {
        override fun toString() = "Small"
    },
    MIDDLE {
        override fun toString() = "Middle"
    },
    BIG {
        override fun toString() = "Big"
    }
}