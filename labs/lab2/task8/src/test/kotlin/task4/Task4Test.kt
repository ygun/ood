package task4

import display.ForecastDisplay
import display.statistic.HumidityStatisticsDisplay
import display.statistic.PressureStatisticsDisplay
import display.statistic.TemperatureStatisticsDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import util.WeatherMeasurement
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.assertEquals

class Task4Test {
    @Test
    fun `Elements without order number display in adding order`() {
        val outputStream = ByteArrayOutputStream()
        val stream = PrintStream(outputStream, true)

        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        var forecastDisplay = ForecastDisplay(weatherDataIn, outSubject = weatherDataOut, output = stream)
        var temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut, output = stream)
        var pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut, output = stream)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        weatherDataOut.setContext(WeatherMeasurement(82.0, 70.0, 29.2))

        val expected = File("D:/GitHub/ood/labs/lab2/task8/src/test/kotlin/task4/withoutOrder.txt").readText()
        assertEquals(expected, outputStream.toString())
    }

    @Test
    fun `Elements with not equals inside and outside order numbers display in different order`() {
        val outputStream = ByteArrayOutputStream()
        val stream = PrintStream(outputStream, true)

        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        var forecastDisplay = ForecastDisplay(weatherDataIn, 11, weatherDataOut, 20, stream)
        var temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherDataIn, 20, weatherDataOut, 10, stream)
        var pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, 9, weatherDataOut, 65, stream)
        var humidityStatisticsDisplay = HumidityStatisticsDisplay(weatherDataIn, 65, weatherDataOut, 8, stream)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        weatherDataOut.setContext(WeatherMeasurement(81.0, 71.0, 30.2))

        val expected = File("D:/GitHub/ood/labs/lab2/task8/src/test/kotlin/task4/differentOrder.txt").readText()
        assertEquals(expected, outputStream.toString())
    }

    @Test
    fun `Elements with equals inside and outside order numbers display in same order`() {
        val outputStream = ByteArrayOutputStream()
        val stream = PrintStream(outputStream, true)

        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        var forecastDisplay = ForecastDisplay(weatherDataIn, 20, weatherDataOut, 20, stream)
        var temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherDataIn, 10, weatherDataOut, 10, stream)
        var pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, 65, weatherDataOut, 65, stream)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        weatherDataOut.setContext(WeatherMeasurement(81.0, 71.0, 30.2))

        val expected = File("D:/GitHub/ood/labs/lab2/task8/src/test/kotlin/task4/equalsOrder.txt").readText()
        assertEquals(expected, outputStream.toString())
    }
}