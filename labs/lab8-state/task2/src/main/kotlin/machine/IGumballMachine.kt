package machine

import state.IState

interface IGumballMachine : IState {
    fun releaseBall()
    fun getBallCount(): Int
    fun fillMachine(ballsCount: Int)

    fun getCountQuarters(): Int
    fun addQuarter()
    fun removeAllQuarters()

    fun setHasQuarterState()
    fun setNoQuarterState()
    fun setSoldState()
    fun setSoldOutState()

    companion object {
        const val MAX_QUARTERS_COUNT = 5
    }
}