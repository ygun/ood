import observer.CurrentConditionsDisplay
import observer.ForecastDisplay
import observer.HeatIndexDisplay
import observer.statistic.HumidityStaticsDisplay
import observer.statistic.PressureStatisticsDisplay
import observer.statistic.TemperatureStatisticsDisplay
import subject.SubjectType
import subject.WeatherData
import util.Context

fun main() {
    val weatherDataIn = WeatherData(SubjectType.INPUT)
    val weatherDataOut = WeatherData(SubjectType.OUTPUT)

    val currentConditionsDisplay = CurrentConditionsDisplay(weatherDataIn, weatherDataOut = weatherDataOut)
    val temperatureStatisticsDisplay = TemperatureStatisticsDisplay(weatherDataIn, weatherDataOut = weatherDataOut)
    val humidityStaticsDisplay = HumidityStaticsDisplay(weatherDataIn, weatherDataOut = weatherDataOut)
    val pressureStatisticsDisplay = PressureStatisticsDisplay(weatherDataIn, weatherDataOut = weatherDataOut)
    val forecastDisplay = ForecastDisplay(weatherDataIn, weatherDataOut = weatherDataOut)
    val heatIndexDisplay = HeatIndexDisplay(weatherDataIn, weatherDataOut = weatherDataOut)

    weatherDataIn.setContext(Context(80f, 65f, 30.4f))
    println()
    weatherDataIn.setContext(Context(82f, 70f, 29.2f))
    println()
    weatherDataOut.setContext(Context(78f, 90f, 29.2f))
}