import {Editor} from '../entities/Editor'

export const changeElementBorderWidth = (editor: Editor, width: number): Editor => ({
    ...editor,
    elements: editor.elements.map(elem => {
        if (editor.selectionElementsId.includes(elem.id)) {
            elem.borderWidth = width
            return elem
        }
        return elem
    })
} as Editor)