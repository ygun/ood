package machine

import org.junit.jupiter.api.Test
import state.SoldState
import state.States.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TestNaiveGumballMachine {

    @Test
    fun `insertQuarter SoldOut doesn't change state`() {
        val machine = NaiveGumballMachine(10)
        machine.setSoldOutState()
        val prevBallsCount = machine.getBallCount()

        machine.insertQuarter()
        assertEquals(SOLD_OUT, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `insertQuarter NoQuarter change state to HasQuarter`() {
        val machine = NaiveGumballMachine(10)
        machine.setNoQuarterState()
        val prevBallsCount = machine.getBallCount()

        machine.insertQuarter()
        assertEquals(HAS_QUARTER, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `insertQuarter HasQuarter add quarter to Machine`() {
        val machine = NaiveGumballMachine(10)
        machine.setHasQuarterState()

        machine.insertQuarter()
        assertEquals(HAS_QUARTER, machine.getState())
        assertEquals(1, machine.getCountQuarters())

        machine.insertQuarter()
        machine.insertQuarter()
        machine.insertQuarter()
        machine.insertQuarter()
        assertEquals(HAS_QUARTER, machine.getState())
        assertEquals(5, machine.getCountQuarters())

        machine.insertQuarter()
        assertEquals(HAS_QUARTER, machine.getState())
        assertEquals(5, machine.getCountQuarters())
    }

    @Test
    fun `insertQuarter Sold doesn't change state`() {
        val machine = NaiveGumballMachine(10)
        machine.setSoldState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.insertQuarter()
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `ejectQuarter SoldOut doesn't change state`() {
        val machine = NaiveGumballMachine(10)
        machine.setSoldOutState()
        machine.addQuarter()
        machine.addQuarter()
        machine.addQuarter()
        assertEquals(3, machine.getCountQuarters())

        val prevBallsCount = machine.getBallCount()

        machine.ejectQuarter()
        assertEquals(prevBallsCount, machine.getBallCount())
        assertEquals(0, machine.getCountQuarters())
    }

    @Test
    fun `ejectQuarter NoQuarter doesn't change state`() {
        val machine = NaiveGumballMachine(10)
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.ejectQuarter()
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `ejectQuarter HasQuarter change state to NoQuarter and return all quarters`() {
        val machine = NaiveGumballMachine(10)
        machine.setHasQuarterState()
        machine.addQuarter()
        machine.addQuarter()
        machine.addQuarter()
        assertEquals(3, machine.getCountQuarters())

        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.ejectQuarter()
        assertEquals(NO_QUARTER, machine.getState())
        assertNotEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
        assertEquals(0, machine.getCountQuarters())
    }

    @Test
    fun `ejectQuarter Sold doesn't change state and return all quarters`() {
        val machine = NaiveGumballMachine(10)
        machine.setSoldState()
        machine.addQuarter()
        machine.addQuarter()
        machine.addQuarter()
        assertEquals(3, machine.getCountQuarters())

        val prevBallsCount = machine.getBallCount()
        val prevState = machine.getState()

        val soldState = SoldState(machine)

        soldState.ejectQuarter()
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
        assertEquals(0, machine.getCountQuarters())
    }

    @Test
    fun `turnCrank SoldOut doesn't change state`() {
        val machine = NaiveGumballMachine(10)
        machine.setSoldOutState()
        val prevBallsCount = machine.getBallCount()
        val prevState = machine.getState()

        machine.turnCrank()
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }


    @Test
    fun `turnCrank NoQuarter doesn't change state`() {
        val machine = NaiveGumballMachine(10)
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.turnCrank()
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }


    @Test
    fun `turnCrank HasQuarter able to change state to SoldState`() {
        val machine = NaiveGumballMachine(1)
        machine.setHasQuarterState()
        machine.addQuarter()
        val prevState = machine.getState()

        machine.turnCrank()
        assertNotEquals(prevState, machine.getState())
        assertEquals(0, machine.getBallCount())
    }

    @Test
    fun `turnCrank HasQuarter able to change state to NoQuarter`() {
        val machine = NaiveGumballMachine(10)
        machine.setHasQuarterState()
        machine.addQuarter()
        val prevState = machine.getState()

        machine.turnCrank()
        assertNotEquals(prevState, machine.getState())
        assertEquals(9, machine.getBallCount())
    }

    @Test
    fun `turnCrank Sold doesn't change state`() {
        val machine = MultiGumballMachine(10)
        machine.setSoldState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.turnCrank()
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense SoldOut doesn't change state`() {
        val machine = NaiveGumballMachine(10)
        machine.setSoldOutState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.dispense()
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense NoQuarter doesn't change state`() {
        val machine = NaiveGumballMachine(10)
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.dispense()
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense HasQuarter doesn't change state`() {
        val machine = NaiveGumballMachine(10)
        machine.setHasQuarterState()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.dispense()
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }


    @Test
    fun `dispense Sold able to change state to SoldOutState`() {
        val machine = NaiveGumballMachine(1)
        machine.setSoldState()
        machine.addQuarter()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.dispense()
        assertNotEquals(prevState, machine.getState())
        assertNotEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense Sold able to change state to NoQuarter`() {
        val machine = NaiveGumballMachine(10)
        machine.setSoldState()
        machine.addQuarter()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.dispense()
        assertNotEquals(prevState, machine.getState())
        assertNotEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense Sold able to change state to HasQuarter`() {
        val machine = NaiveGumballMachine(10)
        machine.setSoldState()
        machine.addQuarter()
        machine.addQuarter()
        machine.addQuarter()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.dispense()
        assertNotEquals(prevState, machine.getState())
        assertNotEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `dispense Sold able to change state to NoQuarterState`() {
        val machine = MultiGumballMachine(10)
        machine.setSoldState()
        machine.addQuarter()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.dispense()
        assertNotEquals(prevState, machine.getState())
        assertNotEquals(prevBallsCount, machine.getBallCount())
    }
}
