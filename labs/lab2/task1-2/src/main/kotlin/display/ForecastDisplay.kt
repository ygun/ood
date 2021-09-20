package display

import observer.Observer
import subject.Subject
import util.WeatherMeasurement

class ForecastDisplay(
    private val weatherData: Subject<WeatherMeasurement>
) : Observer<WeatherMeasurement>, DisplayElement {

    private var currentPressure = 0.0
    private var lastPressure = 0.0

    init {
        weatherData.registerObserver(this)
    }

    override fun update(context: WeatherMeasurement) {
        lastPressure = currentPressure
        currentPressure = context.pressure

        display()
    }

    override fun display() {
        println("Forecast: ${getWeatherMessage()}")
    }

    private fun getWeatherMessage(): String {
        return when {
            currentPressure > lastPressure -> "Improving weather on the way!"
            currentPressure < lastPressure -> "The weather will be cooler and rain is expected."
            else -> "The weather won't change."
        }
    }
}