package util

class ContextBasic(
    override var temperature: Float,
    override var humidity: Float,
    override var pressure: Float
) : Context {
}