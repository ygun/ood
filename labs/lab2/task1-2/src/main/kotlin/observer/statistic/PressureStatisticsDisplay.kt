package observer.statistic

import observer.Observer
import subject.WeatherData
import util.ContextFields

class PressureStatisticsDisplay(
    private val weatherData: WeatherData
) : StatisticsDisplay(ContextFields.PRESSURE), Observer {

    init {
        weatherData.registerObserver(this)
    }
}