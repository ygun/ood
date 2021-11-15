package machine

interface IGumballMachinePrivate {
    fun setHasQuarterState()
    fun setNoQuarterState()
    fun setSoldState()
    fun setSoldOutState()

    fun releaseBall()
    fun getBallCount(): Int

    fun getCountQuarters(): Int
    fun addQuarter()
    fun removeAllQuarters()

    fun fillMachine(ballsCount: Int)
}