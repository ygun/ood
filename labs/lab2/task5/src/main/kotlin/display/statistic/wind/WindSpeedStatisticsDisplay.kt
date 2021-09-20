package display.statistic.wind

import display.statistic.StatisticsDisplay
import subject.Subject
import util.WeatherMeasurement

class WindSpeedStatisticsDisplay(
    observableSubject: Subject<WeatherMeasurement>,
    priority: Int = 0
) : StatisticsDisplay<WeatherMeasurement>() {

    init {
        observableSubject.registerObserver(this, priority)
    }

    override fun getFieldName() = "wind speed"
    override fun getFieldValue(context: WeatherMeasurement) = context.windSpeed
}