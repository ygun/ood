package observer

class ObserverWithPriority<T>(
    var observer: Observer<T>,
    var priority: Int
) : Comparable<ObserverWithPriority<T>> {

    override fun compareTo(other: ObserverWithPriority<T>): Int = when {
        other.observer == observer -> 0
        other.priority > priority -> 1
        else -> -1
    }
}