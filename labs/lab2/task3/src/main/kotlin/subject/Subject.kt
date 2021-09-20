package subject

import observer.Observer

interface Subject<T> {
    fun registerObserver(observer: Observer<T>, priority: Int = 0)
    fun removeObserver(observer: Observer<T>)
    fun notifyObservers(context: T)
}