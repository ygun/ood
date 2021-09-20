package display

import display.duo.SubjectLocation
import display.duo.WeatherDuoDisplay
import subject.Subject
import util.WeatherMeasurement

class ForecastDisplay(
    inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0
) : WeatherDuoDisplay(inSubject, inPriority, outSubject, outPriority) {

    private var inCurrentPressure = 0.0
    private var inLastPressure = 0.0

    private var outCurrentPressure = 0.0
    private var outLastPressure = 0.0

    override fun update(subjectLocation: SubjectLocation, measurement: WeatherMeasurement) {
        when (subjectLocation) {
            SubjectLocation.INSIDE -> {
                inLastPressure = inCurrentPressure
                inCurrentPressure = measurement.pressure
            }
            SubjectLocation.OUTSIDE -> {
                outLastPressure = outCurrentPressure
                outCurrentPressure = measurement.pressure
            }
        }

        display(subjectLocation)
    }

    override fun display(subjectLocation: SubjectLocation) {
        val heatIndexMsg = when(subjectLocation) {
            SubjectLocation.INSIDE -> getWeatherMessage(SubjectLocation.INSIDE)
            SubjectLocation.OUTSIDE -> getWeatherMessage(SubjectLocation.OUTSIDE)
        }

        println("${subjectLocation.name}: Heat index: $heatIndexMsg")
    }

    private fun getWeatherMessage(subjectLocation: SubjectLocation): String {
        return when (subjectLocation) {
            SubjectLocation.INSIDE -> when {
                inCurrentPressure > inLastPressure -> "Inside improving weather on the way!"
                inCurrentPressure < inLastPressure -> "The weather inside will be cooler, turn on heating."
                else -> "The weather won't change."
            }
            SubjectLocation.OUTSIDE -> when {
                outCurrentPressure > outLastPressure -> "Outside improving weather on the way!"
                outCurrentPressure < outLastPressure -> "The weather outside will be cooler, may be will be rain."
                else -> "The weather won't change."
            }
        }
    }
}