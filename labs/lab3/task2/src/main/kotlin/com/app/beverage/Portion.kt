package com.app.beverage

enum class Portion {
    STANDARD {
        override fun toString() = "Standard"
    },
    DOUBLE {
        override fun toString() = "Double"
    }
}