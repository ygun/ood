package task2

import observer.DisplayElement
import observer.Observer
import subject.WeatherData
import util.Context

class DisplayElementTest(private val weatherData: WeatherData) : Observer, DisplayElement {

    init {
        weatherData.registerObserver(this)
    }

    override fun update(context: Context) {
        weatherData.removeObserver(this)

        display()
    }

    override fun display() {
        println("Remove observer in update")
    }
}