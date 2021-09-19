import observer.simple.CurrentConditionsDisplay
import observer.simple.ForecastDisplay
import observer.simple.HeatIndexDisplay
import observer.simple.statistic.HumidityStaticsDisplay
import observer.simple.statistic.PressureStatisticsDisplay
import observer.simple.statistic.TemperatureStatisticsDisplay
import subject.WeatherData
import util.Context

fun main() {
    val weatherData = WeatherData()

    val currentConditionsDisplay = CurrentConditionsDisplay(weatherData)
    val temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherData)
    val humidityStaticsDisplay = HumidityStaticsDisplay(weatherData)
    val pressureStatisticsDisplay = PressureStatisticsDisplay(weatherData)
    val forecastDisplay = ForecastDisplay(weatherData)
    val heatIndexDisplay = HeatIndexDisplay(weatherData)

    weatherData.setContext(Context(80f, 65f, 30.4f))
    println()
    weatherData.setContext(Context(82f, 70f, 29.2f))
    println()
    weatherData.setContext(Context(78f, 90f, 29.2f))
}