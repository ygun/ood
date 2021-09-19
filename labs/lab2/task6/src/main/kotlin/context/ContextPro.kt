package context

import observer.pro.WindDirection

data class ContextPro(
    var contextBasic: ContextBasic,
    var windSpeed: Float,
    var windDirection: WindDirection
) : Context {
    override var temperature: Float = contextBasic.temperature
    override var humidity: Float = contextBasic.humidity
    override var pressure: Float = contextBasic.pressure

    constructor(temperature: Float,
                humidity: Float,
                pressure: Float,
                windSpeed: Float,
                windDirection: WindDirection
    ) : this(ContextBasic(temperature, humidity, pressure), windSpeed, windDirection)
}