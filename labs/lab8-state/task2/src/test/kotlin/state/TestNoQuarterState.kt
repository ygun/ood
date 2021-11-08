package state

import machine.MultiGumballMachineImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestNoQuarterState {
    @Test
    fun `insertQuarter change state to HasQuarter`() {
        val machineImpl = MultiGumballMachineImpl(10)
        val prevBallsCount = machineImpl.getBallCount()

        val noQuarterState = NoQuarterState(machineImpl)

        noQuarterState.insertQuarter()
        assertTrue(machineImpl.getState() is HasQuarterState)
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `ejectQuarter doesn't change state`() {
        val machineImpl = MultiGumballMachineImpl(10)
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val noQuarterState = NoQuarterState(machineImpl)

        noQuarterState.ejectQuarter()
        assertTrue(machineImpl.getState() is NoQuarterState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `turnCrank doesn't change state`() {
        val machineImpl = MultiGumballMachineImpl(10)
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val noQuarterState = NoQuarterState(machineImpl)

        noQuarterState.turnCrank()
        assertTrue(machineImpl.getState() is NoQuarterState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }

    @Test
    fun `dispense doesn't change state`() {
        val machineImpl = MultiGumballMachineImpl(10)
        val prevState = machineImpl.getState()
        val prevBallsCount = machineImpl.getBallCount()

        val noQuarterState = NoQuarterState(machineImpl)

        noQuarterState.dispense()
        assertTrue(machineImpl.getState() is NoQuarterState)
        assertEquals(prevState, machineImpl.getState())
        assertEquals(prevBallsCount, machineImpl.getBallCount())
    }
}