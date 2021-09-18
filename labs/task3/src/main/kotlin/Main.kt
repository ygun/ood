import observer.CurrentConditionsDisplay
import observer.ForecastDisplay
import observer.HeatIndexDisplay
import observer.statistic.HumidityStaticsDisplay
import observer.statistic.PressureStatisticsDisplay
import observer.statistic.StatisticsDisplay
import observer.statistic.TemperatureStatisticsDisplay
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