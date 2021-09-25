package com.app.component.tea

enum class TeaType {
    GREEN {
        override fun toString() = "Green"
        override fun getCost() = 1.10
    },
    BLACK {
        override fun toString() = "Black"
        override fun getCost(): Double = 1.00
    },
    WHITE {
        override fun toString() = "White"
        override fun getCost() = 1.25
    },
    OOLONG {
        override fun toString() = "Oolong"
        override fun getCost() = 1.50
    };

    abstract fun getCost(): Double
}