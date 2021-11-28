import {Editor} from "../entities/Editor"

export function getSelectedElements(editor: Editor) {
    if (editor.selectionElementsId !== []) {
        const selectedElementId = editor.selectionElementsId
        return editor.elements.filter((elem) => {
            if (selectedElementId.includes(elem.id)) {
                return elem
            }
            return false
        })
    } else {
        return null
    }
}