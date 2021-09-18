package subject

import util.Context
import observer.Observer

class WeatherData : Subject {

    private val observers = mutableListOf<Observer>()
    private var context: Context = Context(0f, 0f, 0f)

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach {
            it.update(context)
        }
    }

    private fun contextChanged() {
        notifyObservers()
    }

    fun setContext(context: Context) {
        this.context = context
        contextChanged()
    }
}