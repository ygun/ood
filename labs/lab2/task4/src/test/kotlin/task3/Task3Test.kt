package task3

import display.ForecastDisplay
import display.statistic.HumidityStaticsDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import util.WeatherMeasurement

class Task3Test {
    @Test
    fun `Elements exist with null oder by default`() {
        val weatherData = WeatherData()

        var forecastDisplay = ForecastDisplay(weatherData, 1)
        var humidityStaticsDisplay = HumidityStaticsDisplay(weatherData)

        weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        println()
        weatherData.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        println()
    }

    @Test
    fun `Elements with order number should be before than elements without`() {
        val weatherData = WeatherData()

        val elemWithOrder1 = ElementWithOrderDisplay(weatherData, 1)
        val elemWithOrder6 = ElementWithOrderDisplay(weatherData, 6)
        val elemWithOrderNull = ElementWithOrderDisplay(weatherData, 12)
        var forecastDisplay = ForecastDisplay(weatherData)

        weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        println()
        weatherData.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        println()
    }

    @Test
    fun `Elements with order number executing with correct order`() {
        val weatherData = WeatherData()

        val elemWithOrder1 = ElementWithOrderDisplay(weatherData, 1)
        val elemWithOrder2 = ElementWithOrderDisplay(weatherData, 2)
        val elemWithOrder34 = ElementWithOrderDisplay(weatherData, 34)
        val elemWithOrder100 = ElementWithOrderDisplay(weatherData, 100)
        var forecastDisplay1 = ForecastDisplay(weatherData)
        var forecastDisplay2 = ForecastDisplay(weatherData)

        weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        println()
        weatherData.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        println()
    }
}