package com.app

enum class Operation() {
    ENCRYPT {
        override fun toString(): String = "--encrypt"
    },
    DECRYPT {
        override fun toString(): String = "--decrypt"
    },
    COMPRESS {
        override fun toString(): String = "--compress"
    },
    DECOMPRESS {
        override fun toString(): String = "--decompress"
    };
}