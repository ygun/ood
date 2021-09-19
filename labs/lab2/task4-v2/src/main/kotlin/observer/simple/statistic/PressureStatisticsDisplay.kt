package observer.simple.statistic

import observer.simple.Observer
import subject.WeatherData
import util.ContextFields

class PressureStatisticsDisplay(
    private val weatherData: WeatherData,
    order: Int? = null
) : StatisticsDisplay(ContextFields.PRESSURE), Observer {

    init {
        weatherData.registerObserver(this, order)
    }
}