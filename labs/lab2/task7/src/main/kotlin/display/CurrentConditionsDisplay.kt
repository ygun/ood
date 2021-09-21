package display

import observer.Observer
import subject.Subject
import util.WeatherMeasurement

class CurrentConditionsDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int = 0
) : Observer<WeatherMeasurement>, DisplayElement {

    private var temperature = 0.0
    private var humidity = 0.0

    init {
        observableSubject.registerObserver(this, priority, WeatherMeasurement().getFieldNames())
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