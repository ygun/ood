package com.app.machine

interface IGumballMachinePrivate {
    fun setHasQuarterState()
    fun setNoQuarterState()
    fun setSoldState()
    fun setSoldOutState()

    fun releaseBall()
    fun getBallCount(): Int
}