package com.app.cypher

import kotlin.random.Random

internal fun getSubstitutionTable(key: Long): Map<Int, Int> {
    return (0..255)
        .shuffled(Random(key))
        .mapIndexed(::Pair)
        .toMap()
}

internal fun getReversedSubstitutionTable(key: Long): Map<Int, Int> {
    return (0..255)
        .shuffled(Random(key))
        .mapIndexed { index, it -> Pair(it, index) }
        .toMap()
}