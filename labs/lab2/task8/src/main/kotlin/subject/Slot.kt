package subject

open class Signal<T> {
    private val callbacks = mutableListOf<(Subject<T>, T) -> Unit>()

    open fun addCallback(slot: (Subject<T>, T) -> Unit, index: Int = Int.MAX_VALUE) {
        if (index == Int.MAX_VALUE || index > callbacks.lastIndex) {
            callbacks.add(slot)
        } else {
            callbacks.add(index, slot)
        }
    }

    open fun removeCallback(slot: (Subject<T>, T) -> Unit) {
        callbacks.remove(slot)
    }

    open fun emit(subject: Subject<T>, param: T) {
        callbacks.toList().forEach { call ->
            call(subject, param)
        }
    }
}