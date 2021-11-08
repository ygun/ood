package com.app.machine

import com.app.state.*
import java.io.PrintStream

class GumballMachineImpl(
    private var count: Int = 0,
    private val output: PrintStream = System.out
) : IGumballMachinePrivate {

    private val soldState: IState = SoldState(this, output)
    private val soldOutState: IState = SoldOutState(this, output)
    private val noQuarterState: IState = NoQuarterState(this, output)
    private val hasQuarterState: IState = HasQuarterState(this, output)
    private var state: IState = soldOutState

    init {
        if (count > 0) {
            state = noQuarterState
        }
    }

    fun insertQuarter() = state.insertQuarter()

    fun ejectQuarter() = state.ejectQuarter()

    fun turnCrank() = state.turnCrank()

    fun dispense() = state.dispense()

    override fun setHasQuarterState() {
        state = hasQuarterState
    }

    override fun setNoQuarterState() {
        state = noQuarterState
    }

    override fun setSoldState() {
        state = soldState
    }

    override fun setSoldOutState() {
        state = soldOutState
    }

    override fun releaseBall() {
        if (count != 0) {
            output.println("A gumball comes rolling out the slot...")
            --count
        }
    }

    override fun getBallCount() = count

    fun getState() = state
}