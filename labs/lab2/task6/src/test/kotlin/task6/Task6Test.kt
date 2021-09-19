package task6

import context.ContextBasic
import context.ContextPro
import observer.ForecastDisplay
import observer.duo.DuoDisplay
import observer.pro.WindDirection
import observer.pro.WindDisplay
import org.junit.jupiter.api.Test
import subject.WeatherData

class Task6Test {
    @Test
    fun `Correct work of WindDisplay Duo`() {
        val weatherDataIn = WeatherData()
        val weatherDataOut = WeatherData()

        var contextBasic = ContextBasic(80f, 65f, 30f)
        var duoWindDisplay = DuoDisplay(
            ForecastDisplay(weatherDataIn),
            WindDisplay(weatherDataOut, curDirection = WindDirection.NORTH)
        )

        weatherDataIn.setContext(ContextBasic(80f, 65f, 30f))
        println()
        weatherDataIn.setContext(ContextPro(90f, 68f, 32.4f, 1.2f, WindDirection.WEST))
        println()
        weatherDataOut.setContext(ContextPro(80f, 65f, 30.4f, 4.2f, WindDirection.EAST))
        println()
    }
}