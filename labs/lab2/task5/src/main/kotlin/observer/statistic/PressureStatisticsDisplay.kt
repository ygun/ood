package observer.statistic

import observer.Observer
import subject.WeatherData
import util.ContextBasicFields

class PressureStatisticsDisplay(
    private val weatherData: WeatherData,
    order: Int? = null
) : StatisticsDisplay(ContextBasicFields.PRESSURE), Observer {

    init {
        weatherData.registerObserver(this, order)
    }
}