import {Editor} from '../entities/Editor'

export function changeElementBorderWidth(editor: Editor, width: number): Editor {
    return {
        ...editor,
        elements: editor.elements.map(elem => {
            if (editor.selectionElementsId.includes(elem.id)) {
                elem.borderWidth = width
                return elem
            }
            return elem
        })
    } as Editor
}   