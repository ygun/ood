package observer.statistic

import observer.Observer
import subject.WeatherData
import util.ContextFields

class TemperatureStatisticsDisplay(
    private val weatherData: WeatherData,
    order: Int? = null
) : StatisticsDisplay(ContextFields.TEMPERATURE), Observer {

    init {
        weatherData.registerObserver(this, order)
    }
}