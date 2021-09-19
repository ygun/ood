package observer.pro

import observer.DisplayElement
import observer.Observer
import subject.Subject
import util.Context
import util.ContextPro
import java.lang.Math.toDegrees
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.round
import kotlin.math.tan

class WindDisplay(
    private val weatherData: Subject,
    order: Int? = null,
    private var curDirection: WindDirection
) : Observer, DisplayElement {

    private var maxSpeed = Float.MIN_VALUE
    private var minSpeed = Float.MAX_VALUE
    private var curSpeed = 0f
    private var avgDirectionAngle: Double = 0.0
    private var numReadings = 0

    init {
        weatherData.registerObserver(this, order)
    }

    override fun update(context: Context) {
        if (context !is ContextPro) return
        numReadings++

        avgDirectionAngle = calcAvgDirection(context.windDirection)
        curDirection = context.windDirection

        curSpeed = context.windSpeed

        if (curSpeed > maxSpeed) {
            maxSpeed = curSpeed
        }
        if (curSpeed < minSpeed) {
            minSpeed = curSpeed
        }

        display()
    }

    private fun calcAvgDirection(direction: WindDirection): Double {
        val difBetweenAngles = direction.getDegrees() - curDirection.getDegrees()
        val avgAngle = toDegrees(atan(tan(difBetweenAngles.toDouble())))
        return round(abs(avgAngle))
    }

    override fun display() {
        println("Avg angle/Max/Min wind speed: $avgDirectionAngle/$maxSpeed/$minSpeed; " +
                "Current direction: $curDirection; " +
                "Current speed: $curSpeed")
    }

}