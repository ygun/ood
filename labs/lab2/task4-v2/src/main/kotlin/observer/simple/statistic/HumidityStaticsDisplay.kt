package observer.simple.statistic

import subject.Subject
import util.ContextFields

class HumidityStaticsDisplay(
    private val weatherData: Subject,
    order: Int? = null
) : StatisticsDisplay(ContextFields.HUMIDITY) {

    init {
        weatherData.registerObserver(this, order)
    }
}