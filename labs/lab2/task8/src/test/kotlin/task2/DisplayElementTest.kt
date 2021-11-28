package task2

import display.DisplayElement
import observer.Observer
import subject.Subject
import util.WeatherMeasurement
import java.io.PrintStream

class DisplayElementTest(
    private val weatherData: Subject<WeatherMeasurement>,
    private val output: PrintStream = System.out
) : Observer<WeatherMeasurement>, DisplayElement {

    init {
        weatherData.registerObserver(this::update, 8)
    }

    override fun update(subject: Subject<WeatherMeasurement>, context: WeatherMeasurement) {
        weatherData.removeObserver(this::update)

        display()
    }

    override fun display() {
        output.println("Remove observer in update")
    }
}