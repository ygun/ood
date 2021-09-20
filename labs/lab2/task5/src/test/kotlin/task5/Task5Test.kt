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
        val weatherData = WeatherData()

        val directionStatisticsDisplay = WindDirectionStatisticsDisplay(weatherData)
        val speedStatisticsDisplay = WindSpeedStatisticsDisplay(weatherData)

        weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        println()
        weatherData.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))
        println()
    }

    @Test
    fun `Test the wind displays with order observer`() {
        val weatherData = WeatherData()

        val elementWithOrderDisplay1 = ElementWithOrderDisplay(weatherData, 0)
        val directionStatisticsDisplay = WindDirectionStatisticsDisplay(weatherData, 1)
        val speedStatisticsDisplay = WindSpeedStatisticsDisplay(weatherData, 2)
        val elementWithOrderDisplay2 = ElementWithOrderDisplay(weatherData, 3)

        weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        println()
        weatherData.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))
        println()
    }
}