package task3

import display.DisplayElement
import observer.Observer
import subject.Subject
import util.WeatherMeasurement

class ElementWithOrderDisplay(
    private val weatherData: Subject<WeatherMeasurement>,
    private var order: Int
) : Observer<WeatherMeasurement>, DisplayElement {

    init {
        weatherData.registerObserver(this::update, order)
    }

    override fun update(subject: Subject<WeatherMeasurement>, context: WeatherMeasurement) {
        display()
    }

    override fun display() {
        println("My order is: $order")
    }
}