package observer.statistic

import observer.Observer
import subject.Subject
import util.ContextFields

class PressureStatisticsDisplay(
    private val weatherData: Subject
) : StatisticsDisplay(ContextFields.PRESSURE), Observer {

    init {
        weatherData.registerObserver(this)
    }
}