package display.statistic

import subject.Subject
import util.WeatherMeasurement

class TemperatureStatisticsDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int = 0
) : StatisticsDisplay<WeatherMeasurement>() {

    init {
        observableSubject.registerObserver(this, priority, WeatherMeasurement().getFieldNames())
    }

    override fun getFieldName() = "temperature"
    override fun getFieldValue(context: WeatherMeasurement) = context.temperature
}