package task3

import observer.DisplayElement
import observer.Observer
import subject.Subject
import util.Context
import util.ContextBasic

class ElementWithOrderDisplay(
    private val weatherData: Subject,
    private var order: Int?
) : Observer, DisplayElement {

    init {
        weatherData.registerObserver(this, order)
    }

    override fun update(context: Context) {
        display()
    }

    override fun display() {
        println("My oder is: $order")
    }
}