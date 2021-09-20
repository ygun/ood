package display.statistic

import display.duo.SubjectLocation
import display.duo.WeatherDuoDisplay
import subject.Subject
import util.WeatherMeasurement
import kotlin.math.max
import kotlin.math.min

abstract class StatisticsDisplay(
    inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0
) : WeatherDuoDisplay(inSubject, inPriority, outSubject, outPriority) {

    private var inMeasurement = StatisticsMeasurement()
    private var outMeasurement = StatisticsMeasurement()

    init {
        inSubject.registerObserver(this, inPriority)
        outSubject.registerObserver(this, outPriority)
    }

    abstract fun getFieldName(): String
    abstract fun getFieldValue(context: WeatherMeasurement): Double

    override fun update(subjectLocation: SubjectLocation, measurement: WeatherMeasurement) {
        val newValue = getFieldValue(measurement)
        val currMeasurement = getMeasurement(subjectLocation)

        currMeasurement.sumOfAllValues += newValue
        currMeasurement.numReadings++
        currMeasurement.maxValue = max(newValue, currMeasurement.maxValue)
        currMeasurement.minValue = min(newValue, currMeasurement.minValue)

        display(subjectLocation)
    }

    override fun display(subjectLocation: SubjectLocation) {
        val currMeasurement = getMeasurement(subjectLocation)
        println(
            "${subjectLocation.name}: Avg/Max/Min ${getFieldName()} " +
                    "= ${currMeasurement.sumOfAllValues / currMeasurement.numReadings}" +
                    "/${currMeasurement.maxValue}/${currMeasurement.minValue}"
        )
    }

    private fun getMeasurement(subjectLocation: SubjectLocation): StatisticsMeasurement {
        return when (subjectLocation) {
            SubjectLocation.INSIDE -> inMeasurement
            SubjectLocation.OUTSIDE -> outMeasurement
        }
    }
}