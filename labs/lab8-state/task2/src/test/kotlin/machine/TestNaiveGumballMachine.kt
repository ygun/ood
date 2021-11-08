package machine

import org.junit.jupiter.api.Test
import state.States.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TestNaiveGumballMachine {

    @Test
    fun `insertQuarter SoldOut doesn't change state`() {
        val machine = NaiveGumballMachine()
        val prevBallsCount = machine.getBallCount()

        machine.insertQuarter()
        assertEquals(SOLD_OUT, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `insertQuarter NoQuarter change state to HasQuarter`() {
        val machine = NaiveGumballMachine(10)
        val prevBallsCount = machine.getBallCount()

        machine.insertQuarter()
        assertEquals(HAS_QUARTER, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }

    @Test
    fun `insertQuarter HasQuarter add quarter to Machine`() {
        val machine = NaiveGumballMachine(10)
        machine.insertQuarter()

        machine.insertQuarter()
        assertEquals(HAS_QUARTER, machine.getState())
        assertEquals(2, machine.getCountQuarters())

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
    fun `ejectQuarter SoldOut doesn't change state`() {
        val machine = NaiveGumballMachine()
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
        machine.insertQuarter()
        machine.addQuarter()
        machine.addQuarter()
        machine.addQuarter()
        assertEquals(4, machine.getCountQuarters())

        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.ejectQuarter()
        assertEquals(NO_QUARTER, machine.getState())
        assertNotEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
        assertEquals(0, machine.getCountQuarters())
    }

    @Test
    fun `turnCrank SoldOut doesn't change state`() {
        val machine = NaiveGumballMachine()
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
        machine.insertQuarter()
        machine.addQuarter()
        val prevState = machine.getState()

        machine.turnCrank()
        assertNotEquals(prevState, machine.getState())
        assertEquals(0, machine.getBallCount())
    }

    @Test
    fun `turnCrank HasQuarter able to change state to NoQuarter`() {
        val machine = NaiveGumballMachine(10)
        machine.insertQuarter()
        val prevState = machine.getState()

        machine.turnCrank()
        assertNotEquals(prevState, machine.getState())
        assertEquals(9, machine.getBallCount())
    }

    @Test
    fun `turnCrank HasQuarter able to not changing state`() {
        val machine = NaiveGumballMachine(10)
        machine.insertQuarter()
        machine.addQuarter()
        val prevState = machine.getState()

        machine.turnCrank()
        assertEquals(prevState, machine.getState())
        assertEquals(9, machine.getBallCount())
    }

    @Test
    fun `dispense SoldOut doesn't change state`() {
        val machine = NaiveGumballMachine()
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
        machine.insertQuarter()
        val prevState = machine.getState()
        val prevBallsCount = machine.getBallCount()

        machine.dispense()
        assertEquals(prevState, machine.getState())
        assertEquals(prevBallsCount, machine.getBallCount())
    }
}
