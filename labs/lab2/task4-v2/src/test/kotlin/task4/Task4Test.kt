package task4

import observer.duo.DuoDisplay
import observer.simple.ForecastDisplay
import observer.simple.HeatIndexDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import util.Context

class Task4Test {
    @Test
    fun `Duo Display for same displays`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val duoDisplayForecast = DuoDisplay(ForecastDisplay(weatherDataIn), ForecastDisplay(weatherDataOut))

        weatherDataIn.setContext(Context(85f, 40f, 20f))
        duoDisplayForecast.displaySystemStatus()
        println()
        weatherDataOut.setContext(Context(82f, 70f, 30.5f))
        duoDisplayForecast.displaySystemStatus()
        println()
    }

    @Test
    fun `Duo Display for different displays`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val duoDisplayForecast = DuoDisplay(HeatIndexDisplay(weatherDataIn), ForecastDisplay(weatherDataOut))

        weatherDataIn.setContext(Context(85f, 40f, 20f))
        duoDisplayForecast.displaySystemStatus()
        println()
        weatherDataOut.setContext(Context(82f, 70f, 30.5f))
        duoDisplayForecast.displaySystemStatus()
        println()
    }

    @Test
    fun `Duo Display with order`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        val duoDisplayForecast = DuoDisplay(HeatIndexDisplay(weatherDataIn, 2), ForecastDisplay(weatherDataOut, 1))

        weatherDataIn.setContext(Context(85f, 40f, 20f))
        duoDisplayForecast.displaySystemStatus()
        println()
        weatherDataOut.setContext(Context(82f, 70f, 30.5f))
        duoDisplayForecast.displaySystemStatus()
        println()
    }
}