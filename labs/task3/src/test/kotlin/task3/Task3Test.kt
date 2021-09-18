package task3

import observer.ForecastDisplay
import observer.statistic.HumidityStaticsDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import util.Context

class Task3Test {
    @Test
    fun `Elements exist with null oder by default`() {
        val weatherData = WeatherData()

        var forecastDisplay = ForecastDisplay(weatherData)
        var humidityStaticsDisplay = HumidityStaticsDisplay(weatherData)

        weatherData.setContext(Context(80f, 65f, 30.4f))
        println()
        weatherData.setContext(Context(82f, 70f, 29.2f))
        println()
    }

    @Test
    fun `Elements with order number should be before than elements without`() {
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

    @Test
    fun `Elements with order number executing with correct order`() {
        val weatherData = WeatherData()

        val elemWithOrder1 = ElementWithOrderDisplay(weatherData, 1)
        val elemWithOrder2 = ElementWithOrderDisplay(weatherData, 2)
        val elemWithOrder34 = ElementWithOrderDisplay(weatherData, 34)
        val elemWithOrder100 = ElementWithOrderDisplay(weatherData, 100)
        val elemWithOrderNull = ElementWithOrderDisplay(weatherData, null)
        var forecastDisplay1 = ForecastDisplay(weatherData)
        var forecastDisplay2 = ForecastDisplay(weatherData)

        weatherData.setContext(Context(80f, 65f, 30.4f))
        println()
        weatherData.setContext(Context(82f, 70f, 29.2f))
        println()
    }
}