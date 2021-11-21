package task2

import display.DisplayElement
import observer.Observer
import subject.Subject
import util.WeatherMeasurement

class DisplayElementTest(
    private val weatherData: Subject<WeatherMeasurement>
) : Observer<WeatherMeasurement>, DisplayElement {

    init {
        weatherData.registerObserver(this::update, 8)
    }

    override fun update(subject: Subject<WeatherMeasurement>, context: WeatherMeasurement) {
        weatherData.removeObserver(8)

        display()
    }

    override fun display() {
        println("Remove observer in update")
    }
}