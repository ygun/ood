import {Editor} from '../entities/Editor'

export function chooseElement(editor: Editor, listElementsId: Array<string>): Editor {
    return {
        ...editor,
        selectionElementsId: listElementsId
    }
}