package display.statistic

import subject.Subject
import util.WeatherMeasurement

class TemperatureStatisticsDisplay(
    subject: Subject<WeatherMeasurement>
) : StatisticsDisplay<WeatherMeasurement>() {

    init {
        subject.registerObserver(this)
    }

    override fun getFieldName() = "temperature"
    override fun getFieldValue(context: WeatherMeasurement) = context.temperature
}