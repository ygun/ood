package task4

import display.ForecastDisplay
import display.statistic.HumidityStatisticsDisplay
import display.statistic.PressureStatisticsDisplay
import display.statistic.TemperatureStatisticsDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import util.WeatherMeasurement

class Task4Test {
    @Test
    fun `Elements without order number display in adding order`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        var forecastDisplay = ForecastDisplay(weatherDataIn, outSubject = weatherDataOut)
        var temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut)
        var pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, outSubject = weatherDataOut)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        println()
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        println()
    }

    @Test
    fun `Elements with inside order number display in adding order for outside`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        var forecastDisplay = ForecastDisplay(weatherDataIn, 20, weatherDataOut)
        var temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherDataIn, 10, weatherDataOut)
        var pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, 65, weatherDataOut)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        println()
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        println()
        weatherDataOut.setContext(WeatherMeasurement(81.0, 71.0, 30.2))
        println()
    }

    @Test
    fun `Elements with not equals inside and outside order numbers display in different order`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        var forecastDisplay = ForecastDisplay(weatherDataIn, 11, weatherDataOut, 20)
        var temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherDataIn, 20, weatherDataOut, 10)
        var pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, 9, weatherDataOut, 65)
        var humidityStatisticsDisplay = HumidityStatisticsDisplay(weatherDataIn, 65, weatherDataOut, 8)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        println()
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        println()
        weatherDataOut.setContext(WeatherMeasurement(81.0, 71.0, 30.2))
        println()
    }

    @Test
    fun `Elements with equals inside and outside order numbers display in order`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        var forecastDisplay = ForecastDisplay(weatherDataIn, 20, weatherDataOut, 20)
        var temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherDataIn, 10, weatherDataOut, 10)
        var pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, 65, weatherDataOut, 65)

        weatherDataIn.setContext(WeatherMeasurement(80.0, 65.0, 30.4))
        println()
        weatherDataIn.setContext(WeatherMeasurement(82.0, 70.0, 29.2))
        println()
        weatherDataOut.setContext(WeatherMeasurement(81.0, 71.0, 30.2))
        println()
    }
}