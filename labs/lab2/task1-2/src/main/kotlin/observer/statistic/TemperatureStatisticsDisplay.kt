package observer.statistic

import observer.Observer
import subject.Subject
import util.ContextFields

class TemperatureStatisticsDisplay(
    private val weatherData: Subject
) : StatisticsDisplay(ContextFields.TEMPERATURE), Observer {

    init {
        weatherData.registerObserver(this)
    }
}