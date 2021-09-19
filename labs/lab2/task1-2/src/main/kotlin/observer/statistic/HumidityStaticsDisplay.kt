package observer.statistic

import subject.Subject
import util.ContextFields

class HumidityStaticsDisplay(
    private val weatherData: Subject
) : StatisticsDisplay(ContextFields.HUMIDITY) {

    init {
        weatherData.registerObserver(this)
    }
}