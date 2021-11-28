import {store} from "../store/store"

export function mouseMoveElement(evt: any, firstPosX: number, firstPosY: number) {
    let editor = store.getState()
    let stepX
    let stepY
    let selectedElements = []
    for (let i = 0; i < editor.selectionElementsId.length; i++) {
        selectedElements.push(document.getElementById(editor.selectionElementsId[i]))
    }

    stepX = evt.clientX - firstPosX
    stepY = evt.clientY - firstPosY

    let slide = document.getElementsByClassName('workspace')[0]
    for (let i = 0; i < selectedElements.length; i++) {
        let elem = selectedElements[i]
        if (elem) {
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
                X = Math.floor(stepX / (slide.clientWidth) * 100 * 100) / 100 + prevXAttribute + '%'
            }

            if (prevYAttribute !== undefined) {
                Y = Math.floor(stepY / (slide.clientHeight) * 100 * 100) / 100 + prevYAttribute + '%'
            }

            if (parent) {
                parent.setAttribute('x', X)
                parent.setAttribute('y', Y)
            }
        }
    }

    let selectionBorder = document.getElementById('selection-border') as HTMLElement
    let prevXAttribute: number | string | null = selectionBorder.getAttribute('data-tlp-x')
    let prevYAttribute: number | string | null = selectionBorder.getAttribute('data-tlp-y')
    let X = prevXAttribute
    let Y = prevYAttribute

    if (prevXAttribute && prevYAttribute) {
        prevXAttribute = parseFloat(prevXAttribute)
        prevYAttribute = parseFloat(prevYAttribute)
    }

    if (typeof(prevXAttribute) === "number" && typeof(prevYAttribute) === "number") {
        X = Math.floor(stepX / (slide.clientWidth) * 100 * 100) / 100 + prevXAttribute + '%'
        Y = Math.floor(stepY / (slide.clientHeight) * 100 * 100) / 100 + prevYAttribute + '%'
    }

    if (X && Y) {
        selectionBorder.setAttribute('x', X)
        selectionBorder.setAttribute('y', Y)
    }
}