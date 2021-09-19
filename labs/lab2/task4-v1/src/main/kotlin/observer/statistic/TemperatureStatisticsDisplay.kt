package observer.statistic

import observer.Observer
import subject.Subject
import subject.WeatherData
import util.ContextFields

class TemperatureStatisticsDisplay(
    private val weatherDataIn: WeatherData,
    orderIn: Int? = null,
    private val weatherDataOut: Subject,
    orderOut: Int? = null
) : StatisticsDisplay(ContextFields.TEMPERATURE), Observer {

    init {
        weatherDataIn.registerObserver(this, orderIn)
        weatherDataOut.registerObserver(this, orderOut)
    }
}