package task6

import display.statistic.wind.WindDirectionStatisticsDisplay
import display.statistic.wind.WindSpeedStatisticsDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import util.WeatherMeasurement
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.assertEquals

class Task6Test {
    @Test
    fun `Test the wind displays with duo data without primary order`() {
        val outputStream = ByteArrayOutputStream()
        val stream = PrintStream(outputStream, true)

        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val directionStatisticsDisplay =
            WindDirectionStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut, output = stream)
        val speedStatisticsDisplay =
            WindSpeedStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut, output = stream)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))
        weatherDataOut.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))

        val expected = File("D:/GitHub/ood/labs/lab2/task8/src/test/kotlin/task6/withoutOrder.txt").readText()
        assertEquals(expected, outputStream.toString())
    }

    @Test
    fun `Test the wind displays with duo data with primary order`() {
        val outputStream = ByteArrayOutputStream()
        val stream = PrintStream(outputStream, true)

        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val directionStatisticsDisplay =
            WindDirectionStatisticsDisplay(weatherDataIn, 1, weatherDataOut, 1, output = stream)
        val speedStatisticsDisplay = WindSpeedStatisticsDisplay(weatherDataIn, 2, weatherDataOut, 2, output = stream)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))
        weatherDataOut.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))

        val expected = File("D:/GitHub/ood/labs/lab2/task8/src/test/kotlin/task6/withOrder.txt").readText()
        assertEquals(expected, outputStream.toString())
    }
}