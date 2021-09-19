import observer.CurrentConditionsDisplay
import observer.ForecastDisplay
import observer.HeatIndexDisplay
import observer.statistic.HumidityStaticsDisplay
import observer.statistic.PressureStatisticsDisplay
import observer.statistic.TemperatureStatisticsDisplay
import subject.WeatherData
import util.ContextBasic

fun main() {
    val weatherData = WeatherData()

    val currentConditionsDisplay = CurrentConditionsDisplay(weatherData)
    val temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherData)
    val humidityStaticsDisplay = HumidityStaticsDisplay(weatherData)
    val pressureStatisticsDisplay = PressureStatisticsDisplay(weatherData)
    val forecastDisplay = ForecastDisplay(weatherData)
    val heatIndexDisplay = HeatIndexDisplay(weatherData)

    weatherData.setContext(ContextBasic(80f, 65f, 30.4f))
    println()
    weatherData.setContext(ContextBasic(82f, 70f, 29.2f))
    println()
    weatherData.setContext(ContextBasic(78f, 90f, 29.2f))
}