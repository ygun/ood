package observer.simple.statistic

import observer.simple.Observer
import subject.Subject
import util.ContextFields

class TemperatureStatisticsDisplay(
    private val weatherData: Subject,
    order: Int? = null
) : StatisticsDisplay(ContextFields.TEMPERATURE), Observer {

    init {
        weatherData.registerObserver(this, order)
    }
}