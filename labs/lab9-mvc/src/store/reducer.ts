import {Editor} from "../entities/Editor"
import {EditorAction} from "../type"
import * as actionTypes from "./actionTypes"
import {setEditor} from "../functions/setEditor"
import {chooseElement} from "../functions/chooseElement"
import {addElement} from "../functions/addElement"
import {changePositionOfElements} from "../functions/changePositionOfElements"
import {deleteElement} from "../functions/deleteElement"
import {changeElementFillColor} from "../functions/changeElementFillColor"
import {changeElementBorderColor} from "../functions/changeElementBorderColor"
import {changeElementBorderWidth} from "../functions/changeElementBorderWidth"
import {initialState} from "./localStorage"
import {redo, undo} from "./stateHistory"

export let lastCommand: string

const reducer = (
    state: Editor = initialState,
    action: EditorAction
): Editor => {
    lastCommand = action.type
    switch (action.type) {
        /* editor */
        case actionTypes.SET_EDITOR:
            return setEditor(action.payload)
        case actionTypes.NEW_EDITOR:
            return setEditor(initialState)

        /* undo and redo*/
        case actionTypes.UNDO:
            return undo(state)
        case actionTypes.REDO:
            return redo(state)



        /* elements */
        /* main */
        case actionTypes.CHOOSE_ELEMENTS:
            return chooseElement(state, action.payload)
        case actionTypes.ADD_ELEMENT:
            return addElement(state, action.payload)
        case actionTypes.DELETE_ELEMENTS:
            return deleteElement(state)
        /* move */
        case actionTypes.CHANGE_POSITION_OF_ELEMENTS:
            return changePositionOfElements(state, action.payload)
        case actionTypes.END_MOVE_ELEMENTS:
            return changePositionOfElements(state, action.payload)

        /* styles */
        case actionTypes.CHANGE_ELEMENT_FILL_COLOR:
            return changeElementFillColor(state, action.payload)
        case actionTypes.CHANGE_ELEMENT_BORDER_COLOR:
            return changeElementBorderColor(state, action.payload)
        case actionTypes.CHANGE_ELEMENT_BORDER_WIDTH:
            return changeElementBorderWidth(state, action.payload)

    }

    return state
}

export default reducer