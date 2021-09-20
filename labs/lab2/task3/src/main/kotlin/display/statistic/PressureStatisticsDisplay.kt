package display.statistic

import subject.Subject
import util.WeatherMeasurement

class PressureStatisticsDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int = 0
) : StatisticsDisplay<WeatherMeasurement>() {

    init {
        observableSubject.registerObserver(this, priority)
    }

    override fun getFieldName() = "pressure"
    override fun getFieldValue(context: WeatherMeasurement) = context.pressure
}