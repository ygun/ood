package subject

import observer.Observer

abstract class SubjectImpl<Context> : Subject<Context> {

    private val observers = mutableListOf<Observer<Context>>()

    override fun registerObserver(observer: Observer<Context>) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer<Context>) {
        observers.remove(observer)
    }

    override fun notifyObservers(context: Context) {
        val copyOfObservers = observers.toList()
        copyOfObservers.forEach {
            it.update(context)
        }
    }

    protected fun contextChanged(context: Context) {
        notifyObservers(context)
    }
}