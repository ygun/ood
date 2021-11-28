package observer

import subject.Subject

class ObserverSlotWithPriority<T>(
    var slot: (Subject<T>, T) -> Unit,
    var priority: Int
) : Comparable<ObserverSlotWithPriority<T>> {

    override fun compareTo(other: ObserverSlotWithPriority<T>): Int = when {
        other.slot == slot -> 0
        other.priority > priority -> 1
        else -> -1
    }
}