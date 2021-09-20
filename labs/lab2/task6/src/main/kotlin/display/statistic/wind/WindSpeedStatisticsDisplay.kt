package display.statistic.wind

import display.statistic.StatisticsDisplay
import subject.Subject
import util.WeatherMeasurement

class WindSpeedStatisticsDisplay(
    inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0
) : StatisticsDisplay(inSubject, inPriority, outSubject, outPriority) {

    override fun getFieldName() = "wind speed"
    override fun getFieldValue(context: WeatherMeasurement) = context.windSpeed
}