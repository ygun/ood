package subject

import observer.Observer

interface Subject<T> {
    fun registerObserver(observer: Observer<T>, priority: Int = 0, observableFields: Set<String>)
    fun removeObserver(observer: Observer<T>, observableFields: Set<String>)
    fun notifyObservers(context: T, observableFields: Set<String>)
}