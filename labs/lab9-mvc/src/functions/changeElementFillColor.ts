import {Editor} from '../entities/Editor'
import {hexToRgb} from "./hexToRgb"

export const changeElementFillColor = (editor: Editor, color: string): Editor => ({
    ...editor,
    elements: editor.elements.map(elem => {
        if (editor.selectionElementsId.includes(elem.id)) elem.backgroundColor = hexToRgb(color)
        return elem
    })
} as Editor)