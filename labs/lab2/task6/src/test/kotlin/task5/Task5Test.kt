package task5

import observer.pro.WindDirection
import observer.pro.WindDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData
import context.ContextPro

class Task5Test {
    @Test
    fun `Correct work of WindDisplay`() {
        val weatherData = WeatherData()

        var windDisplay = WindDisplay(weatherData, curDirection = WindDirection.NORTH)

        weatherData.setContext(ContextPro(80f, 65f, 30.4f, 2f, WindDirection.WEST))
        println()
        weatherData.setContext(ContextPro(80f, 65f, 30.4f, 4.2f, WindDirection.EAST))
        println()
    }
}