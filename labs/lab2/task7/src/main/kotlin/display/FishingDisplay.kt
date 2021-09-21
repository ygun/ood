package display

import observer.Observer
import subject.Subject
import util.WeatherMeasurement

class FishingDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int = 0
) : Observer<WeatherMeasurement>, DisplayElement {

    private var currentTemperature = 0.0
    private var lastTemperature = 0.0

    private var currentPressure = 0.0
    private var lastPressure = 0.0

    init {
        observableSubject.registerObserver(this, priority, setOf("temperature", "pressure"))
    }

    override fun update(context: WeatherMeasurement) {
        lastPressure = currentPressure
        currentPressure = context.pressure

        lastTemperature = currentTemperature
        currentTemperature = context.temperature

        display()
    }

    override fun display() {
        println("Fishing Display: ${getWeatherMessage()}")
    }

    private fun getWeatherMessage(): String {
        val pressureMsg = when {
            currentPressure > lastPressure -> "Fish will ignore you!"
            currentPressure < lastPressure -> "Fish want to eat! Be ready!"
            else -> "Fish behaviour is default"
        }
        val temperatureMsg = when {
            currentPressure > lastTemperature -> "Water is too hot for fish"
            currentPressure < lastPressure -> "Water is too cold for fish"
            else -> "Water is cool"
        }
        return "Pressure data: $pressureMsg; Temperature data: $temperatureMsg"
    }
}