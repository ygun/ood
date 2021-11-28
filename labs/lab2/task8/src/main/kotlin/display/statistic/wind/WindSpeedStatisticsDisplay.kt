package display.statistic.wind

import display.statistic.StatisticsDisplay
import subject.Subject
import util.WeatherMeasurement
import java.io.PrintStream

class WindSpeedStatisticsDisplay(
    inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0,
    output: PrintStream = System.out
) : StatisticsDisplay(inSubject, inPriority, outSubject, outPriority, output) {

    override fun getFieldName() = "wind speed"
    override fun getFieldValue(context: WeatherMeasurement) = context.windSpeed
}