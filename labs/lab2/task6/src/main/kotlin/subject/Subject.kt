package subject

import observer.Observer

interface Subject {
    fun registerObserver(observer: Observer, orderNum: Int? = null)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}