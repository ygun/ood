package task4

import observer.ForecastDisplay
import observer.HeatIndexDisplay
import observer.statistic.HumidityStaticsDisplay
import observer.statistic.PressureStatisticsDisplay
import org.junit.jupiter.api.Test
import subject.SubjectType
import subject.WeatherData
import util.Context

class Task4Test {
    @Test
    fun `Duo Observers`() {
        val weatherDataIn = WeatherData(SubjectType.INPUT)
        val weatherDataOut = WeatherData(SubjectType.OUTPUT)

        var forecastDisplay = ForecastDisplay(weatherDataIn, weatherDataOut = weatherDataOut)
        var humidityStaticsDisplay = HumidityStaticsDisplay(weatherDataIn, weatherDataOut = weatherDataOut)
        var pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, weatherDataOut = weatherDataOut)
        var heatIndexDisplay = HeatIndexDisplay(weatherDataIn, weatherDataOut = weatherDataOut)

        weatherDataIn.setContext(Context(80f, 65f, 30.4f))
        println()
        weatherDataIn.setContext(Context(82f, 70f, 29.2f))
        println()
    }

    @Test
    fun `Duo Observers with order num`() {
        val weatherDataIn = WeatherData(SubjectType.INPUT)
        val weatherDataOut = WeatherData(SubjectType.OUTPUT)

        var forecastDisplay = ForecastDisplay(weatherDataIn, 1, weatherDataOut, 1)
        var humidityStaticsDisplay = HumidityStaticsDisplay(weatherDataIn, 4, weatherDataOut, 4)
        var pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, null, weatherDataOut, null)
        var heatIndexDisplay = HeatIndexDisplay(weatherDataIn, 100, weatherDataOut, 100)

        weatherDataIn.setContext(Context(80f, 65f, 30.4f))
        println()
        weatherDataIn.setContext(Context(82f, 70f, 29.2f))
        println()
    }
}