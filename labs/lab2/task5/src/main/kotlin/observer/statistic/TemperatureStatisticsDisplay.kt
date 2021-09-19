package observer.statistic

import observer.Observer
import subject.Subject
import util.ContextBasicFields

class TemperatureStatisticsDisplay(
    private val weatherData: Subject,
    order: Int? = null
) : StatisticsDisplay(ContextBasicFields.TEMPERATURE), Observer {

    init {
        weatherData.registerObserver(this, order)
    }
}