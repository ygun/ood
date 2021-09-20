package subject

import observer.Observer
import observer.ObserverWithPriority
import java.util.*

abstract class SubjectImpl<Context> : Subject<Context> {

    private val observers: SortedSet<ObserverWithPriority<Context>> = TreeSet()

    override fun registerObserver(observer: Observer<Context>, priority: Int) {
        observers.add(ObserverWithPriority(observer, priority))
    }

    override fun removeObserver(observer: Observer<Context>) {
        observers.remove(ObserverWithPriority(observer, 0))
    }

    override fun notifyObservers(context: Context) {
        observers.toSortedSet().forEach {
            it.observer.update(context)
        }
    }

    protected fun contextChanged(context: Context) {
        notifyObservers(context)
    }
}