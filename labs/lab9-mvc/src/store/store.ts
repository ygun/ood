import {loadState, saveStateToLocalStorage} from "./localStorage"
import {applyMiddleware, createStore, Store} from "redux"
import {Editor} from "../entities/Editor"
import {DispatchType, EditorAction} from "../type"
import reducer, {lastCommand} from "./reducer"
import thunk from "redux-thunk"
import {decIndex, getStateHistory, saveStateToHistory} from "./stateHistory"
import {CHOOSE_ELEMENTS, REDO, SET_EDITOR, UNDO} from "./actionTypes"


const oldState = loadState()
export const store: Store<Editor, EditorAction> & {
    dispatch: DispatchType
} = createStore(reducer, oldState, applyMiddleware(thunk))


let notValidActions: Array<string> = [UNDO, REDO, CHOOSE_ELEMENTS, SET_EDITOR]
store.subscribe(() => {
    let state = store.getState()
    let stateHistory = getStateHistory()

    saveStateToLocalStorage(state)

    if (!notValidActions.includes(lastCommand)) {
        saveStateToHistory(state)
    }

    if (stateHistory.history.length === 0) {
        saveStateToHistory(oldState)
    }
    if (stateHistory.history.length === stateHistory.index) {
        decIndex()
    }
})


