package observer

import subject.Subject
import util.Context

class CurrentConditionsDisplay(
    private val weatherDataIn: Subject,
    orderIn: Int? = null,
    private val weatherDataOut: Subject,
    orderOut: Int? = null
) : Observer, DisplayElement {

    private var temperatureIn = 0f
    private var humidityIn = 0f

    private var temperatureOut = 0f
    private var humidityOut = 0f

    init {
        weatherDataIn.registerObserver(this, orderIn)
        weatherDataOut.registerObserver(this, orderOut)
    }

    override fun updateIn(context: Context) {
        temperatureIn = context.temperature
        humidityIn = context.humidity

        display()
    }

    override fun updateOut(context: Context) {
        temperatureOut = context.temperature
        humidityOut = context.humidity

        display()
    }

    override fun display() {
        print("Current conditions Input: ${temperatureIn}F degrees and ${humidityIn}% humidity; ")
        println("Current conditions Output: ${temperatureOut}F degrees and ${humidityOut}% humidity")
    }
}