package observer

import subject.Subject
import util.Context

class CurrentConditionsDisplay(private val weatherData: Subject) : Observer, DisplayElement {

    private var temperature = 0f
    private var humidity = 0f

    init {
        weatherData.registerObserver(this)
    }

    override fun update(context: Context) {
        temperature = context.temperature
        humidity = context.humidity

        display()
    }

    override fun display() {
        println("Current conditions: ${temperature}F degrees and ${humidity}% humidity")
    }
}