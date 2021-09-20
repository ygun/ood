package task3

import display.ForecastDisplay
import display.statistic.HumidityStaticsDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import util.WeatherMeasurement

class Task3Test {
    @Test
    fun `Elements with order number should be before than elements without`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val elemWithOrder1 = ElementWithOrderDisplay(weatherDataIn, 1)
        val elemWithOrder6 = ElementWithOrderDisplay(weatherDataIn, 6)
        val elemWithOrderNull = ElementWithOrderDisplay(weatherDataIn, 12)
        var forecastDisplay = ForecastDisplay(weatherDataIn, 20, weatherDataOut)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        println()
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        println()
    }

    @Test
    fun `Elements with order number executing with correct order`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val elemWithOrder1 = ElementWithOrderDisplay(weatherDataIn, 1)
        val elemWithOrder2 = ElementWithOrderDisplay(weatherDataIn, 2)
        val elemWithOrder34 = ElementWithOrderDisplay(weatherDataIn, 34)
        val elemWithOrder100 = ElementWithOrderDisplay(weatherDataIn, 100)
        var forecastDisplay1 = ForecastDisplay(weatherDataIn, 0, weatherDataOut, 0)
        var forecastDisplay2 = ForecastDisplay(weatherDataIn, outSubject = weatherDataOut)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        println()
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        println()
    }
}