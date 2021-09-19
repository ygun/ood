package observer

import subject.Subject
import subject.SubjectType
import util.Context

class ForecastDisplay(
    private val weatherDataIn: Subject,
    orderIn: Int? = null,
    private val weatherDataOut: Subject,
    orderOut: Int? = null
) : Observer, DisplayElement {

    private var currentPressureIn = 0f
    private var lastPressureIn = 0f

    private var currentPressureOut = 0f
    private var lastPressureOut = 0f

    init {
        weatherDataIn.registerObserver(this, orderIn)
        weatherDataOut.registerObserver(this, orderOut)
    }

    override fun updateIn(context: Context) {
        lastPressureIn = currentPressureIn
        currentPressureIn = context.pressure

        display()
    }

    override fun updateOut(context: Context) {
        lastPressureIn = currentPressureOut
        currentPressureOut = context.pressure

        display()
    }

    override fun display() {
        print("Forecast In: ${getWeatherMessage(SubjectType.INPUT)}; ")
        println("Forecast Out: ${getWeatherMessage(SubjectType.OUTPUT)}")
    }

    private fun getWeatherMessage(subjectType: SubjectType): String {
        return when (subjectType) {
            SubjectType.INPUT -> when {
                currentPressureIn > lastPressureIn -> "Improving weather on the way!"
                currentPressureIn < lastPressureIn -> "The weather will be cooler and rain is expected."
                else -> "The weather won't change."
            }
            SubjectType.OUTPUT -> when {
                currentPressureOut > lastPressureOut -> "Improving weather on the way!"
                currentPressureOut < lastPressureOut -> "The weather will be cooler and rain is expected."
                else -> "The weather won't change."
            }
        }
    }
}