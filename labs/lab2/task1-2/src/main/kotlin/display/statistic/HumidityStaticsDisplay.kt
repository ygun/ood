package display.statistic

import subject.Subject
import util.WeatherMeasurement

class HumidityStaticsDisplay(
    subject: Subject<WeatherMeasurement>
) : StatisticsDisplay<WeatherMeasurement>() {

    init {
        subject.registerObserver(this)
    }

    override fun getFieldName() = "humidity"
    override fun getFieldValue(context: WeatherMeasurement) = context.humidity
}