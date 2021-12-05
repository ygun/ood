import {store} from "../store/store"
import {getSelectedHTMLElements} from "./getSelectedHTMLElements"

export const mouseMoveElement = (evt: any, firstPosX: number, firstPosY: number) => {
    const editor = store.getState()

    const stepX = evt.clientX - firstPosX

    const stepY = evt.clientY - firstPosY
    const workspace = document.getElementsByClassName('workspace')[0]

    const selectedElements = getSelectedHTMLElements(editor)
    for (let i = 0; i < selectedElements.length; i++) {
        const elem = selectedElements[i]
        if (!elem) continue

        let parent = elem.parentNode as HTMLElement
        if (elem.tagName === 'P') {
            parent = parent.parentNode as HTMLElement
        }

        let prevXAttribute = 0
        let prevYAttribute = 0
        editor.elements.forEach(e => {
            if (elem && e.id === elem.id) {
                prevXAttribute = e.topLeftPoint.x
                prevYAttribute = e.topLeftPoint.y
            }
        })

        let X = prevXAttribute + '%'
        let Y = prevYAttribute + '%'
        if (prevXAttribute !== undefined) {
            X = Math.floor(stepX / (workspace.clientWidth) * 100 * 100) / 100 + prevXAttribute + '%'
        }
        if (prevYAttribute !== undefined) {
            Y = Math.floor(stepY / (workspace.clientHeight) * 100 * 100) / 100 + prevYAttribute + '%'
        }
        if (parent) {
            parent.setAttribute('x', X)
            parent.setAttribute('y', Y)
        }
    }

    const selectionBorder = document.getElementById('selection-border') as HTMLElement

    let prevXAttribute: number | string | null = selectionBorder.getAttribute('data-tlp-x')
    let prevYAttribute: number | string | null = selectionBorder.getAttribute('data-tlp-y')
    if (prevXAttribute && prevYAttribute) {
        prevXAttribute = parseFloat(prevXAttribute)
        prevYAttribute = parseFloat(prevYAttribute)
    }

    let X = selectionBorder.getAttribute('data-tlp-x')
    let Y = selectionBorder.getAttribute('data-tlp-y')
    if (typeof(prevXAttribute) === "number" && typeof(prevYAttribute) === "number") {
        X = Math.floor(stepX / (workspace.clientWidth) * 100 * 100) / 100 + prevXAttribute + '%'
        Y = Math.floor(stepY / (workspace.clientHeight) * 100 * 100) / 100 + prevYAttribute + '%'
    }

    if (X && Y) {
        selectionBorder.setAttribute('x', X)
        selectionBorder.setAttribute('y', Y)
    }
}