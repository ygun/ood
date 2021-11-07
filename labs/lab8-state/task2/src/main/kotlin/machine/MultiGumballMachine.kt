package machine

import state.*
import java.io.PrintStream

class MultiGumballMachine(
    private var countBalls: Int = 0,
    private val output: PrintStream = System.out
) : IGumballMachine {

    private val soldState: IState = SoldState(this, output)
    private val soldOutState: IState = SoldOutState(this, output)
    private val noQuarterState: IState = NoQuarterState(this, output)
    private val hasQuarterState: IState = HasQuarterState(this, output)
    private var state: IState = soldOutState

    private var countQuarters: Int = 0

    init {
        if (countBalls > 0) {
            state = noQuarterState
        }
    }

    override fun insertQuarter() = state.insertQuarter()

    override fun ejectQuarter() = state.ejectQuarter()

    override fun turnCrank() = state.turnCrank()

    override fun dispense() = state.dispense()

    override fun releaseBall() {
        if (countBalls != 0 && countQuarters > 0) {
            output.println("A gumball comes rolling out the slot...")
            --countBalls
            --countQuarters
        }
    }

    override fun getBallCount(): Int = countBalls

    override fun fillMachine(ballsCount: Int) {
        countBalls = ballsCount
        if (countBalls > 0) {
            state = noQuarterState
        }
    }

    override fun getCountQuarters(): Int = countQuarters

    override fun addQuarter() {
        countQuarters++
    }

    override fun removeAllQuarters() {
        countQuarters = 0
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
                "Inventory: $countBalls gumballs\n" +
                "Count quarters: $countQuarters\n" +
                "Machine is $state"
    }

    fun getState() = state
}