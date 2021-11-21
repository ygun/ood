package display.statistic.wind

import display.statistic.StatisticsDisplay
import display.statistic.calculator.DegreeAverageCalculator
import subject.Subject
import util.WeatherMeasurement

class WindDirectionStatisticsDisplay(
    inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0
) : StatisticsDisplay(inSubject, inPriority, outSubject, outPriority) {

    override fun getFieldName() = "wind direction"
    override fun getFieldValue(context: WeatherMeasurement) = context.windDirection
}