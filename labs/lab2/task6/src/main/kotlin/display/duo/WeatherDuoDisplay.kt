package display.duo

import display.DisplayElement
import observer.Observer
import subject.Subject
import util.WeatherMeasurement

abstract class WeatherDuoDisplay(
    private val inSubject: Subject<WeatherMeasurement>,
    inPriority: Int = 0,
    private val outSubject: Subject<WeatherMeasurement>,
    outPriority: Int = 0
): DisplayElement, Observer<WeatherMeasurement> {

    init {
        inSubject.registerObserver(this, inPriority)
        outSubject.registerObserver(this, outPriority)
    }

    abstract fun update(subjectLocation: SubjectLocation, measurement: WeatherMeasurement)

    abstract fun display(subjectLocation: SubjectLocation)

    final override fun update(subject: Subject<WeatherMeasurement>, context: WeatherMeasurement) {
        when (subject) {
            inSubject -> update(SubjectLocation.INSIDE, context)
            outSubject -> update(SubjectLocation.OUTSIDE, context)
            else -> throw IllegalArgumentException("Unknown observable subject")
        }
    }

    final override fun display() {
        display(SubjectLocation.INSIDE)
        display(SubjectLocation.OUTSIDE)
    }
}