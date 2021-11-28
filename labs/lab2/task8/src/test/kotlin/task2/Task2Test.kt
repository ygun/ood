package task2

import org.junit.jupiter.api.Test
import subject.WeatherData
import util.WeatherMeasurement
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.assertEquals

class Task2Test {
    @Test
    fun `Test display element with thy to delete himself in update `() {
        val outputStream = ByteArrayOutputStream()
        val stream = PrintStream(outputStream, true)

        val weatherData = WeatherData()
        val displayElementTest1 = DisplayElementTest(weatherData, stream)
        val displayElementTest2 = DisplayElementTest(weatherData, stream)

        weatherData.setContext(WeatherMeasurement(80.0, 65.0, 30.0))
        weatherData.setContext(WeatherMeasurement(82.0, 70.0, 29.0))

        val expected = File("D:/GitHub/ood/labs/lab2/task8/src/test/kotlin/task2/mergedOrder.txt").readText()
        assertEquals(expected, outputStream.toString())
    }
}