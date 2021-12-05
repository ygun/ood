import {Editor} from "../entities/Editor"

export const getSelectedHTMLElements = (editor: Editor) => editor.selectionElementsId.map((currElement, index) =>
    document.getElementById(editor.selectionElementsId[index])
)