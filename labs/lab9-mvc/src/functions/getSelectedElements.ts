import {Editor} from "../entities/Editor"

export const getSelectedElements = (editor: Editor) => {
    if (editor.selectionElementsId === []) return null

    const selectedElementId = editor.selectionElementsId
    return editor.elements.filter((elem) => selectedElementId.includes(elem.id) ? elem : false)
}