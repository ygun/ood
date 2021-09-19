package task2

import observer.DisplayElement
import observer.Observer
import subject.Subject
import util.Context

class DisplayElementTest(
    private val weatherDataIn: Subject,
    private val weatherDataOut: Subject
) : Observer, DisplayElement {

    init {
        weatherDataIn.registerObserver(this)
        weatherDataOut.registerObserver(this)
    }

    override fun updateIn(context: Context) {
        weatherDataIn.removeObserver(this)

        display()
    }

    override fun updateOut(context: Context) {
        weatherDataOut.removeObserver(this)

        display()
    }

    override fun display() {
        println("Remove observer in update")
    }
}