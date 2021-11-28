package subject

import observer.ObserverSlotWithPriority
import java.util.*

open class Signal<T> {
    private val callbacks: SortedSet<ObserverSlotWithPriority<T>> = TreeSet()

    open fun addCallback(slot: (Subject<T>, T) -> Unit, index: Int = Int.MAX_VALUE) {
        callbacks.add(ObserverSlotWithPriority(slot, index))
    }

    open fun removeCallback(slot: (Subject<T>, T) -> Unit) {
        callbacks.remove(ObserverSlotWithPriority(slot, 0))
    }

    open fun emit(subject: Subject<T>, param: T) {
        callbacks.toList().forEach { call ->
            call.slot(subject, param)
        }
    }
}