package task3

import display.ForecastDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import util.WeatherMeasurement
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.assertEquals

class Task3Test {
    @Test
    fun `Elements with order number should be before than elements without`() {
        val outputStream = ByteArrayOutputStream()
        val stream = PrintStream(outputStream, true)

        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val elemWithOrder1 = ElementWithOrderDisplay(weatherDataIn, 1, stream)
        val elemWithOrderNull = ElementWithOrderDisplay(weatherDataIn, 12, stream)
        val elemWithOrder6 = ElementWithOrderDisplay(weatherDataIn, 6, stream)
        var forecastDisplay = ForecastDisplay(weatherDataIn, 20, weatherDataOut, output = stream)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        weatherDataOut.setContext(WeatherMeasurement(82.0, 70.0, 29.2))

        val expected = File("D:/GitHub/ood/labs/lab2/task8/src/test/kotlin/task3/mergedOrder.txt").readText()
        assertEquals(expected, outputStream.toString())
    }

    @Test
    fun `Elements with order number executing with correct order`() {
        val outputStream = ByteArrayOutputStream()
        val stream = PrintStream(outputStream, true)

        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val elemWithOrder1 = ElementWithOrderDisplay(weatherDataIn, 1, stream)
        val elemWithOrder100 = ElementWithOrderDisplay(weatherDataIn, 100, stream)
        val elemWithOrder34 = ElementWithOrderDisplay(weatherDataIn, 34, stream)
        val elemWithOrder2 = ElementWithOrderDisplay(weatherDataIn, 2, stream)
        var forecastDisplay1 = ForecastDisplay(weatherDataIn, 0, weatherDataOut, 0, stream)
        var forecastDisplay2 = ForecastDisplay(weatherDataIn, outSubject = weatherDataOut, output = stream)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        weatherDataOut.setContext(WeatherMeasurement(82.0, 70.0, 29.2))

        val expected = File("D:/GitHub/ood/labs/lab2/task8/src/test/kotlin/task3/allWithOrder.txt").readText()
        assertEquals(expected, outputStream.toString())
    }
}