import {Editor} from '../entities/Editor'
import {Element} from '../entities/Elements'
import {deepCopy} from "deep-copy-ts"
import {v4 as uuidv4} from 'uuid'


export function addElement(editor: Editor, element: Element): Editor {
    let copyElement = deepCopy(element)

    let elements = editor.elements.slice()
    copyElement.id = uuidv4()
    elements.push(copyElement)

    return {
        ...editor,
        elements: elements
    }
}