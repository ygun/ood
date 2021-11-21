package subject

interface Subject<T> {
    fun registerObserver(slot: (Subject<T>, T) -> Unit, index: Int = Int.MAX_VALUE)
    fun removeObserver(index: Int = Int.MAX_VALUE)
    fun notifyObservers(context: T)
}