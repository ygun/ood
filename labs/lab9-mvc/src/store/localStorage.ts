import {Editor} from "../entities/Editor"
import {LOCAL_STORAGE_EDITOR_KEY, WHITE} from "../entities/Constants"
import {v4 as uuidv4} from "uuid"


export const initialState: Editor = {
    id: uuidv4(),
    elements: [],
    background: WHITE,
    selectionElementsId: []
}


export const saveStateToLocalStorage = (state: Editor) => {
    try {
        const serialisedState = JSON.stringify(state)

        window.localStorage.setItem(LOCAL_STORAGE_EDITOR_KEY, serialisedState)
    } catch (err) {
        console.log(err)
    }
}

export const loadState = () => {
    try {
        const serialisedState = window.localStorage.getItem(LOCAL_STORAGE_EDITOR_KEY)

        if (!serialisedState) return undefined

        return JSON.parse(serialisedState)
    } catch (err) {
        return undefined
    }
}
