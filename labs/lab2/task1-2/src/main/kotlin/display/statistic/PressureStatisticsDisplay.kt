package display.statistic

import subject.Subject
import util.WeatherMeasurement

class PressureStatisticsDisplay(
    subject: Subject<WeatherMeasurement>
) : StatisticsDisplay<WeatherMeasurement>() {

    init {
        subject.registerObserver(this)
    }

    override fun getFieldName() = "pressure"
    override fun getFieldValue(context: WeatherMeasurement) = context.pressure
}