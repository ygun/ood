package util

import observer.pro.WindDirection

data class ContextPro(
    override var temperature: Float,
    override var humidity: Float,
    override var pressure: Float,
    var windSpeed: Float,
    var windDirection: WindDirection,
) : Context {
}