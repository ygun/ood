package display.statistic

import subject.Subject
import util.WeatherMeasurement

class TemperatureStatisticsDisplay(
    inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0
) : StatisticsDisplay(inSubject, inPriority, outSubject, outPriority) {

    override fun getFieldName() = "temperature"

    override fun getFieldValue(context: WeatherMeasurement) = context.temperature
}