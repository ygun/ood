package subject

import observer.Observer
import util.Context

class WeatherData(private val subjectType: SubjectType) : Subject {

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
            when (subjectType) {
                SubjectType.INPUT -> observer.updateIn(context)
                SubjectType.OUTPUT -> observer.updateOut(context)
            }
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