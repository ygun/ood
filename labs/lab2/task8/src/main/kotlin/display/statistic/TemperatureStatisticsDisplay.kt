package display.statistic

import subject.Subject
import util.WeatherMeasurement
import java.io.PrintStream

class TemperatureStatisticsDisplay(
    inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0,
    output: PrintStream = System.out
) : StatisticsDisplay(inSubject, inPriority, outSubject, outPriority, output) {

    override fun getFieldName() = "temperature"

    override fun getFieldValue(context: WeatherMeasurement) = context.temperature
}