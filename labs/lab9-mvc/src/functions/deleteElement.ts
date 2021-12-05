import {Editor} from '../entities/Editor'

export const deleteElement = (editor: Editor): Editor => ({
    ...editor,
    elements: editor.elements.filter(elem => !editor.selectionElementsId.includes(elem.id)),
    selectionElementsId: []
})