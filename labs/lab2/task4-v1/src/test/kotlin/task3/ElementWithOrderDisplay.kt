package task3

import observer.DisplayElement
import observer.Observer
import subject.Subject
import util.Context

class ElementWithOrderDisplay(
    private val weatherDataIn: Subject,
    private var orderIn: Int?,
    private val weatherDataOut: Subject,
    private var orderOut: Int?
) : Observer, DisplayElement {

    init {
        weatherDataIn.registerObserver(this, orderIn)
        weatherDataOut.registerObserver(this, orderOut)
    }

    override fun updateIn(context: Context) {
        display()
    }

    override fun updateOut(context: Context) {
        display()
    }

    override fun display() {
        print("My oder Input is: $orderIn")
        println("My oder Output is: $orderOut")
    }
}