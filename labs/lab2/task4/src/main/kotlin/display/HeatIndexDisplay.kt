package display

import display.duo.SubjectLocation
import display.duo.WeatherDuoDisplay
import observer.Observer
import subject.Subject
import util.WeatherMeasurement
import kotlin.math.pow

class HeatIndexDisplay(
    inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0
) : WeatherDuoDisplay(inSubject, inPriority, outSubject, outPriority) {

    private var heatIndexIn = 0.0
    private var heatIndexOut = 0.0

    init {
        inSubject.registerObserver(this, inPriority)
        outSubject.registerObserver(this, outPriority)
    }

    override fun update(subjectLocation: SubjectLocation, measurement: WeatherMeasurement) {
        when (subjectLocation) {
            SubjectLocation.INSIDE -> heatIndexIn = calculateHeatIndex(measurement.temperature, measurement.humidity)
            SubjectLocation.OUTSIDE -> heatIndexOut = calculateHeatIndex(measurement.temperature, measurement.humidity)
        }

        display(subjectLocation)
    }

    override fun display(subjectLocation: SubjectLocation) {
        val heatIndex = when(subjectLocation) {
            SubjectLocation.INSIDE -> heatIndexIn
            SubjectLocation.OUTSIDE -> heatIndexOut
        }

        println("${subjectLocation.name} Heat index: $heatIndex")
    }

    private fun calculateHeatIndex(temperature: Double, humidity: Double): Double {
        return (16.923 +
                1.85212e-1 * temperature +
                5.37941 * humidity -
                1.00254e-1 * temperature * humidity +
                9.41695e-3 * temperature.pow(2) +
                7.28898e-3 * humidity.pow(2) +
                3.45372e-4 * temperature.pow(2) * humidity -
                8.14971e-4 * temperature * humidity.pow(2) +
                1.02102e-5 * temperature.pow(2) * humidity.pow(2) -
                3.8646e-5 * temperature.pow(3) +
                2.91583e-5 * humidity.pow(3) +
                1.42721e-6 * temperature.pow(3) * humidity +
                1.97483e-7 * temperature * humidity.pow(3) -
                2.18429e-8 * temperature.pow(3) * humidity.pow(2) +
                8.43296e-10 * temperature.pow(2) * humidity.pow(3) -
                4.81975e-11 * temperature.pow(3) * humidity.pow(3))
    }
}