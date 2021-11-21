package subject

interface Subject<T> {
    fun registerObserver(slot: (Subject<T>, T) -> Unit, index: Int = Int.MAX_VALUE)
    fun removeObserver(slot: (Subject<T>, T) -> Unit)
    fun notifyObservers(context: T)
}