package display.statistic

import subject.Subject
import util.WeatherMeasurement

class HumidityStatisticsDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int = 0
) : StatisticsDisplay<WeatherMeasurement>() {

    init {
        observableSubject.registerObserver(this, priority)
    }

    override fun getFieldName() = "humidity"
    override fun getFieldValue(context: WeatherMeasurement) = context.humidity
}