package gumball.machine

import state.IState

interface IGumballMachine : IState {
    fun releaseBall()
    fun getBallCount(): Int
    fun fillMachine(ballsCount: Int)

    fun setHasQuarterState()
    fun setNoQuarterState()
    fun setSoldState()
    fun setSoldOutState()
}