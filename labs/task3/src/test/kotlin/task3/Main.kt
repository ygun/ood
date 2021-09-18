package task3

import observer.ForecastDisplay
import subject.WeatherData
import task2.DisplayElementTest
import util.Context

fun main() {
    val weatherData = WeatherData()

    val elemWithOrder1 = ElementWithOrderDisplay(weatherData, 1)
    val elemWithOrder6 = ElementWithOrderDisplay(weatherData, 6)
    val elemWithOrderNull = ElementWithOrderDisplay(weatherData, null)
    var forecastDisplay = ForecastDisplay(weatherData)

    weatherData.setContext(Context(80f, 65f, 30.4f))
    println()
    weatherData.setContext(Context(82f, 70f, 29.2f))
    println()
}