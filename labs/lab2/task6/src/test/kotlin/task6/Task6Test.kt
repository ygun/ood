package task6

import display.statistic.wind.WindDirectionStatisticsDisplay
import display.statistic.wind.WindSpeedStatisticsDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import task3.ElementWithOrderDisplay
import util.WeatherMeasurement

clas1s Task6Test {
    @Test
    fun `Test the wind displays with duo data without primary order`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val directionStatisticsDisplay = WindDirectionStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut)
        val speedStatisticsDisplay = WindSpeedStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        println()
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))
        println()
        weatherDataOut.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))
        println()
    }

    @Test
    fun `Test the wind displays with duo data with primary order`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val directionStatisticsDisplay = WindDirectionStatisticsDisplay(weatherDataIn, 1, weatherDataOut, 1)
        val speedStatisticsDisplay = WindSpeedStatisticsDisplay(weatherDataIn, 2, weatherDataOut, 2)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        println()
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))
        println()
        weatherDataOut.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))
        println()
    }
}