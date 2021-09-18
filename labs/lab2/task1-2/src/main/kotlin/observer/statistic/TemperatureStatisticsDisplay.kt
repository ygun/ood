package observer.statistic

import observer.Observer
import subject.WeatherData
import util.ContextFields

class TemperatureStatisticsDisplay(
    private val weatherData: WeatherData
) : StatisticsDisplay(ContextFields.TEMPERATURE), Observer {

    init {
        weatherData.registerObserver(this)
    }
}