package task3

import display.DisplayElement
import observer.Observer
import subject.Subject
import util.WeatherMeasurement
import java.io.PrintStream

class ElementWithOrderDisplay(
    weatherData: Subject<WeatherMeasurement>,
    private var order: Int,
    private var output: PrintStream = System.out
) : Observer<WeatherMeasurement>, DisplayElement {

    init {
        weatherData.registerObserver(this::update, order)
    }

    override fun update(subject: Subject<WeatherMeasurement>, context: WeatherMeasurement) {
        display()
    }

    override fun display() {
        output.println("My order is: $order")
    }
}