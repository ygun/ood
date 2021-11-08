package state

import machine.MultiGumballMachineImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestSoldOutState {
    @Test
    fun `insertQuarter doesn't change state`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setSoldOutState()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldOutState = SoldOutState(machineImpl)

        soldOutState.insertQuarter()
        assertTrue(machineImpl.getState() is SoldOutState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `ejectQuarter doesn't change state`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setSoldOutState()
        machineImpl.addQuarter()
        machineImpl.addQuarter()
        machineImpl.addQuarter()
        assertEquals(3, machineImpl.getCountQuarters())

        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldOutState = SoldOutState(machineImpl)

        soldOutState.ejectQuarter()
        assertTrue(machineImpl.getState() is SoldOutState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
        assertEquals(0, machineImpl.getCountQuarters())
    }

    @Test
    fun `turnCrank doesn't change state`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setSoldOutState()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldOutState = SoldOutState(machineImpl)

        soldOutState.turnCrank()
        assertTrue(machineImpl.getState() is SoldOutState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `dispense doesn't change state`() {
        val machineImpl = MultiGumballMachineImpl(10)
        machineImpl.setSoldOutState()
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val soldOutState = SoldOutState(machineImpl)

        soldOutState.dispense()
        assertTrue(machineImpl.getState() is SoldOutState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }
}