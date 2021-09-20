package display

import observer.Observer
import subject.Subject
import util.WeatherMeasurement

class CurrentConditionsDisplay(
    private val weatherData: Subject<WeatherMeasurement>
) : Observer<WeatherMeasurement>, DisplayElement {

    private var temperature = 0.0
    private var humidity = 0.0

    init {
        weatherData.registerObserver(this)
    }

    override fun update(context: WeatherMeasurement) {
        temperature = context.temperature
        humidity = context.humidity

        display()
    }

    override fun display() {
        println("Current conditions: ${temperature}F degrees and ${humidity}% humidity")
    }
}