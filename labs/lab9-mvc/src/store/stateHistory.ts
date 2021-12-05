import {Editor} from "../entities/Editor"
import {deepCopy} from "deep-copy-ts"
import {StateHistory} from "../entities/StateHistory"
import {INITIAL_STATE_HISTORY} from "../entities/Constants"

let stateHistory: StateHistory = INITIAL_STATE_HISTORY

export const saveStateToHistory = (state: Editor) => {
    try {
        if (stateHistory.index !== stateHistory.history.length - 1) {
            stateHistory.history.splice(stateHistory.index + 1)
        }

        stateHistory.history.push(deepCopy(state))
        incIndex()
    } catch (err) {
        console.log(err)
    }
}

export const getStateHistory = (): StateHistory => stateHistory

export const canRedo = (): boolean => stateHistory.index < stateHistory.history.length - 1

export const canUndo = (): boolean => stateHistory.index > 0 && stateHistory.history.length > 1

export const canUndoKeyboard = (evt: KeyboardEvent): boolean => evt.ctrlKey && evt.keyCode === 90

export const decIndex = () => {
    stateHistory.index--
}

export function incIndex() {
    stateHistory.index++
}

export function undo(state: Editor) {
    if (!canUndo()) return state

    if (stateHistory.index > 0) {
        decIndex()
    }

    return stateHistory.history[stateHistory.index]
}

export function redo(state: Editor) {
    if (!canRedo()) return state

    incIndex()

    return stateHistory.history[stateHistory.index]
}
