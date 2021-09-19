package observer.statistic

import subject.Subject
import context.ContextBasicFields

class HumidityStaticsDisplay(
    private val weatherData: Subject,
    order: Int? = null
) : StatisticsDisplay(ContextBasicFields.HUMIDITY) {

    init {
        weatherData.registerObserver(this, order)
    }
}