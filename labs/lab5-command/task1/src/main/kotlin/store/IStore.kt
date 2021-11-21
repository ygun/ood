package store

import kotlin.reflect.KClass
import kotlin.reflect.KFunction

interface IStore<T, I : Any> {
    fun add(type: T, instance: KClass<out I>)
    fun get(type: T): KFunction<I>
}