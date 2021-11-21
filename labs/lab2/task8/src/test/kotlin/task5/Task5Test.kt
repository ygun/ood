package task5

import display.statistic.wind.WindDirectionStatisticsDisplay
import display.statistic.wind.WindSpeedStatisticsDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import task3.ElementWithOrderDisplay
import util.WeatherMeasurement

class Task5Test {
    @Test
    fun `Test default work flow of the wind displays`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val directionStatisticsDisplay = WindDirectionStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut)
        val speedStatisticsDisplay = WindSpeedStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        println()
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))
        println()
    }

    @Test
    fun `Test the wind displays with order observer`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val elementWithOrderDisplay1 = ElementWithOrderDisplay(weatherDataIn, 0)
        val directionStatisticsDisplay = WindDirectionStatisticsDisplay(weatherDataIn, 1, weatherDataOut)
        val speedStatisticsDisplay = WindSpeedStatisticsDisplay(weatherDataIn, 2, weatherDataOut)
        val elementWithOrderDisplay2 = ElementWithOrderDisplay(weatherDataIn, 3)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        println()
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))
        println()
    }
}