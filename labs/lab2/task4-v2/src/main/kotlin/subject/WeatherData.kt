package subject

import observer.simple.Observer
import util.Context

class WeatherData : Subject {

    private val observers = hashMapOf<Observer, Int?>()
    private var context: Context = Context(0f, 0f, 0f)

    override fun registerObserver(observer: Observer, orderNum: Int?) {
        observers.putIfAbsent(observer, orderNum)
    }

    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        val copyOfObservers = observers.toList().sortedWith(compareBy(nullsLast()) { it.second }).toMap()
        copyOfObservers.forEach { (observer, _) ->
            observer.update(context)
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