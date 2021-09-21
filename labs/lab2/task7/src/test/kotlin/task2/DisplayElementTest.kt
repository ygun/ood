package task2

import display.DisplayElement
import observer.Observer
import subject.Subject
import util.WeatherMeasurement

class DisplayElementTest(private val weatherData: Subject<WeatherMeasurement>)
    : Observer<WeatherMeasurement>, DisplayElement {

    init {
        weatherData.registerObserver(this, 0, WeatherMeasurement().getFieldNames())
    }

    override fun update(context: WeatherMeasurement) {
        weatherData.removeObserver(this, context.getFieldNames())

        display()
    }

    override fun display() {
        println("Remove observer in update")
    }
}