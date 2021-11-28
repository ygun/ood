import {Editor} from '../entities/Editor'
import {hexToRgb} from "./hexToRgb"


export function changeElementBorderColor(editor: Editor, color: string): Editor {
    return {
        ...editor,
        elements: editor.elements.map(elem => {
            if (editor.selectionElementsId.includes(elem.id)) {
                elem.borderColor = hexToRgb(color)
                return elem
            }
            return elem
        })
    } as Editor
}   