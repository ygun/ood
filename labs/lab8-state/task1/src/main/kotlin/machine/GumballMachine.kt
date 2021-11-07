package gumball.machine

import state.*
import java.io.PrintStream

class GumballMachine(
    private var count: Int = 0,
    private val output: PrintStream = System.out
) : IGumballMachine {

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

    override fun insertQuarter() = state.insertQuarter()

    override fun ejectQuarter() = state.ejectQuarter()

    override fun turnCrank() = state.turnCrank()

    override fun dispense() = state.dispense()

    override fun releaseBall() {
        if (count != 0) {
            output.println("A gumball comes rolling out the slot...")
            --count
        }
    }

    override fun getBallCount(): Int = count

    override fun fillMachine(ballsCount: Int) {
        count = ballsCount
        if (count > 0) {
            state = noQuarterState
        }
    }

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

    override fun toString(): String {
        return "Mighty Gumball, Inc.\n" +
                "Inventory: $count gumballs\n" +
                "Machine is $state"
    }

    fun getState() = state
}