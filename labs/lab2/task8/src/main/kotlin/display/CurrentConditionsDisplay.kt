package display

import display.duo.SubjectLocation
import display.duo.WeatherDuoDisplay
import subject.Subject
import util.WeatherMeasurement
import java.io.PrintStream

class CurrentConditionsDisplay(
    inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0,
    private var output: PrintStream = System.out
) : WeatherDuoDisplay(inSubject, inPriority, outSubject, outPriority) {

    private var curInMeasurement: WeatherMeasurement = WeatherMeasurement()
    private var curOutMeasurement: WeatherMeasurement = WeatherMeasurement()

    override fun update(subjectLocation: SubjectLocation, measurement: WeatherMeasurement) {
        when (subjectLocation) {
            SubjectLocation.INSIDE -> curInMeasurement = measurement
            SubjectLocation.OUTSIDE -> curOutMeasurement = measurement
        }

        display(subjectLocation)
    }

    override fun display(subjectLocation: SubjectLocation) {
        val currentMeasurement = when(subjectLocation) {
            SubjectLocation.INSIDE -> curInMeasurement
            SubjectLocation.OUTSIDE -> curOutMeasurement
        }
        output.println(
            "Current ${subjectLocation.name.lowercase()} conditions: ${currentMeasurement.temperature}F degrees " +
                    "and ${currentMeasurement.humidity}% humidity"
        )
    }
}