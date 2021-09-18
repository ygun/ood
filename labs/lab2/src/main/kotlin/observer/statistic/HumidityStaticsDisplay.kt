package observer.statistic

import subject.WeatherData
import util.ContextFields

class HumidityStaticsDisplay(
    private val weatherData: WeatherData
) : StatisticsDisplay(ContextFields.HUMIDITY) {

    init {
        weatherData.registerObserver(this)
    }
}