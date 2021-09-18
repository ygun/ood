package observer

import subject.WeatherData
import util.Context

class ForecastDisplay(private val weatherData: WeatherData) : Observer, DisplayElement {

    private var currentPressure = 0f
    private var lastPressure = 0f

    init {
        weatherData.registerObserver(this)
    }

    override fun update(context: Context) {
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