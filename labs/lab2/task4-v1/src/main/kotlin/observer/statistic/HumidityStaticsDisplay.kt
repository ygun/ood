package observer.statistic

import subject.Subject
import util.ContextFields

class HumidityStaticsDisplay(
    private val weatherDataIn: Subject,
    orderIn: Int? = null,
    private val weatherDataOut: Subject,
    orderOut: Int? = null
) : StatisticsDisplay(ContextFields.HUMIDITY) {

    init {
        weatherDataIn.registerObserver(this, orderIn)
        weatherDataOut.registerObserver(this, orderOut)
    }
}