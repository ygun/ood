package task3

import display.DisplayElement
import observer.Observer
import subject.Subject
import util.WeatherMeasurement

class ElementWithOrderDisplay(
    private val weatherData: Subject<WeatherMeasurement>,
    private var order: Int = 0
) : Observer<WeatherMeasurement>, DisplayElement {

    init {
        weatherData.registerObserver(this, order)
    }

    override fun update(context: WeatherMeasurement) {
        display()
    }

    override fun display() {
        println("My order is: $order")
    }
}