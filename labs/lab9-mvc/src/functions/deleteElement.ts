import {Editor} from '../entities/Editor'

export function deleteElement(editor: Editor): Editor {
    return {
        ...editor,
        elements: editor.elements.filter(elem => {
            return !editor.selectionElementsId.includes(elem.id)
        }),
        selectionElementsId: []
    }
}