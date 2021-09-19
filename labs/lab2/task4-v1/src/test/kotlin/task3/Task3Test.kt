package task3

import observer.ForecastDisplay
import observer.statistic.HumidityStaticsDisplay
import org.junit.jupiter.api.Test
import subject.SubjectType
import subject.WeatherData
import util.Context

class Task3Test {
    @Test
    fun `Elements exist with null oder by default`() {
        val weatherDataIn = WeatherData(SubjectType.INPUT)
        val weatherDataOut = WeatherData(SubjectType.OUTPUT)

        var forecastDisplay = ForecastDisplay(weatherDataIn, weatherDataOut = weatherDataOut)
        var humidityStaticsDisplay = HumidityStaticsDisplay(weatherDataIn, weatherDataOut = weatherDataOut)

        weatherDataIn.setContext(Context(80f, 65f, 30.4f))
        println()
        weatherDataOut.setContext(Context(82f, 70f, 29.2f))
        println()
    }

    @Test
    fun `Elements with order number should be before than elements without`() {
        val weatherDataIn = WeatherData(SubjectType.INPUT)
        val weatherDataOut = WeatherData(SubjectType.OUTPUT)

        val elemWithOrder1 = ElementWithOrderDisplay(weatherDataIn, 1, weatherDataOut, 1)
        val elemWithOrder6 = ElementWithOrderDisplay(weatherDataIn, 6, weatherDataOut, 6)
        val elemWithOrderNull = ElementWithOrderDisplay(weatherDataIn, null, weatherDataOut, null)
        var forecastDisplay = ForecastDisplay(weatherDataIn, weatherDataOut = weatherDataOut)

        weatherDataIn.setContext(Context(80f, 65f, 30.4f))
        println()
        weatherDataIn.setContext(Context(82f, 70f, 29.2f))
        println()
    }

    @Test
    fun `Elements with order number executing with correct order`() {
        val weatherDataIn = WeatherData(SubjectType.INPUT)
        val weatherDataOut = WeatherData(SubjectType.OUTPUT)

        val elemWithOrder1 = ElementWithOrderDisplay(weatherDataIn, 1, weatherDataOut, 1)
        val elemWithOrder2 = ElementWithOrderDisplay(weatherDataIn, 2, weatherDataOut, 2)
        val elemWithOrder34 = ElementWithOrderDisplay(weatherDataIn, 34, weatherDataOut, 34)
        val elemWithOrder100 = ElementWithOrderDisplay(weatherDataIn, 100, weatherDataOut, 100)
        val elemWithOrderNull = ElementWithOrderDisplay(weatherDataIn, null, weatherDataOut, null)
        var forecastDisplay1 = ForecastDisplay(weatherDataIn, weatherDataOut = weatherDataOut)
        var forecastDisplay2 = ForecastDisplay(weatherDataIn, weatherDataOut = weatherDataOut)

        weatherDataIn.setContext(Context(80f, 65f, 30.4f))
        println()
        weatherDataIn.setContext(Context(82f, 70f, 29.2f))
        println()
    }
}