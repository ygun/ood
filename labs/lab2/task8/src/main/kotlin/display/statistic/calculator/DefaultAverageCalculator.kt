package display.statistic.calculator

class DefaultAverageCalculator : AverageCalculator {
    private var sumOfAllValues = 0.0
    private var numReadings = 0

    override fun addNewValue(newValue: Double) {
        numReadings++
        sumOfAllValues += newValue
    }

    override fun calculateAverage(): Double = sumOfAllValues / numReadings
}