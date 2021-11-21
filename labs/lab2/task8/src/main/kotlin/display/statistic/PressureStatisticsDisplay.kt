package display.statistic

import subject.Subject
import util.WeatherMeasurement

class PressureStatisticsDisplay(
    inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0
) : StatisticsDisplay(inSubject, inPriority, outSubject, outPriority) {

    override fun getFieldName() = "pressure"

    override fun getFieldValue(context: WeatherMeasurement) = context.pressure
}