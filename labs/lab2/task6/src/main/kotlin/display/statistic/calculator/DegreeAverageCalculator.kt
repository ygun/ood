package display.statistic.calculator

import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class DegreeAverageCalculator : AverageCalculator {

    private var sumOfAllCos = 0.0
    private var sumOfAllSin = 0.0

    override fun addNewValue(newValue: Double) {
        val valueInRad = degreeToRad(newValue)
        sumOfAllSin += sin(valueInRad)
        sumOfAllCos += cos(valueInRad)
    }

    override fun calculateAverage(): Double {
        return when {
            (sumOfAllCos == sumOfAllSin) && (sumOfAllCos == 0.0) -> 0.0
            else -> radToDegree(atan2(sumOfAllSin, sumOfAllCos))
        }
    }
}

private fun degreeToRad(degrees: Double): Double {
    return degrees * PI / 180
}

private fun radToDegree(rad: Double): Double {
    return rad * 180 / PI
}