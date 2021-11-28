package task5

import display.statistic.wind.WindDirectionStatisticsDisplay
import display.statistic.wind.WindSpeedStatisticsDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import task3.ElementWithOrderDisplay
import util.WeatherMeasurement
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.assertEquals

class Task5Test {
    @Test
    fun `Test default work flow of the wind displays`() {
        val outputStream = ByteArrayOutputStream()
        val stream = PrintStream(outputStream, true)

        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val directionStatisticsDisplay = WindDirectionStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut, output = stream)
        val speedStatisticsDisplay = WindSpeedStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut, output = stream)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        weatherDataOut.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))

        val expected = File("D:/GitHub/ood/labs/lab2/task8/src/test/kotlin/task5/withoutOrder.txt").readText()
        assertEquals(expected, outputStream.toString())
    }

    @Test
    fun `Test the wind displays with order observer`() {
        val outputStream = ByteArrayOutputStream()
        val stream = PrintStream(outputStream, true)

        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val elementWithOrderDisplay1 = ElementWithOrderDisplay(weatherDataIn, 0, stream)
        val directionStatisticsDisplay = WindDirectionStatisticsDisplay(weatherDataIn, 1, weatherDataOut, output = stream)
        val speedStatisticsDisplay = WindSpeedStatisticsDisplay(weatherDataIn, 2, weatherDataOut, output = stream)
        val elementWithOrderDisplay2 = ElementWithOrderDisplay(weatherDataIn, 3, stream)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.0, 2.0, 12.0))
        weatherDataOut.setContext(WeatherMeasurement(82.0, 70.0, 29.0, 4.0, 50.0))

        val expected = File("D:/GitHub/ood/labs/lab2/task8/src/test/kotlin/task5/withOrder.txt").readText()
        assertEquals(expected, outputStream.toString())
    }
}