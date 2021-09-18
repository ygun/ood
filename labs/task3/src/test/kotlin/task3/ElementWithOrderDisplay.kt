package task3

import observer.DisplayElement
import observer.Observer
import subject.WeatherData
import util.Context

class ElementWithOrderDisplay(
    private val weatherData: WeatherData,
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