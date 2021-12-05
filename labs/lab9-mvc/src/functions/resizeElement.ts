import {store} from "../store/store"

export const resizeElement = (event: any, pointIndex: number) => {
    const point = event.target
    if (point.getAttribute('data-value') !== 'point') return pointIndex

    const allPoints = event.target.parentNode.childNodes
    for (let i = 0; i < allPoints.length; i++) {
        if (allPoints[i] === event.target) {
            pointIndex = i
        }
    }

    return pointIndex
}

export const moveElementPoint = (event: any, firstPosX: number, firstPosY: number, pointIndex: number) => {
    let editor = store.getState()
    let payload = new Map()
    const workspace = document.getElementsByClassName('workspace')[0]
    const stepX = event.clientX - firstPosX
    const stepY = event.clientY - firstPosY
    let X = Math.floor(stepX / (workspace.clientWidth) * 100 * 100) / 100
    let Y = Math.floor(stepY / (workspace.clientHeight) * 100 * 100) / 100
    const selectionBorder = document.getElementById('selection-border') as HTMLElement

    let oldTlpX: number | string | null = selectionBorder.getAttribute('data-tlp-x')
    let oldTlpY: number | string | null = selectionBorder.getAttribute('data-tlp-y')
    let oldBrpX: number | string | null = selectionBorder.getAttribute('data-brp-x')
    let oldBrpY: number | string | null = selectionBorder.getAttribute('data-brp-y')
    let oldWidth: number = 0
    let oldHeight: number = 0

    let tlpX: number = 0
    let tlpY: number = 0
    let brpX: number = 0
    let brpY: number = 0

    if (oldTlpX && oldTlpY && oldBrpX && oldBrpY) {
        oldTlpX = parseFloat(oldTlpX)
        oldTlpY = parseFloat(oldTlpY)
        oldBrpX = parseFloat(oldBrpX)
        oldBrpY = parseFloat(oldBrpY)
        tlpX = oldTlpX
        tlpY = oldTlpY
        brpX = oldBrpX
        brpY = oldBrpY
        oldWidth = oldBrpX - oldTlpX
        oldHeight = oldBrpY - oldTlpY
    }

    if (pointIndex === 0) {
        tlpX += X
        tlpY += Y
    } else if (pointIndex === 1) {
        tlpY += Y
    } else if (pointIndex === 2) {
        tlpY += Y
        brpX += X
    } else if (pointIndex === 3) {
        tlpX += X
    } else if (pointIndex === 4) {
        brpX += X
    } else if (pointIndex === 5) {
        tlpX += X
        brpY += Y
    } else if (pointIndex === 6) {
        brpY += Y
    } else if (pointIndex === 7) {
        brpX += X
        brpY += Y
    }

    const newWidth = brpX - tlpX
    const newHeight = brpY - tlpY

    const differenceX = newWidth / oldWidth
    const differenceY = newHeight / oldHeight

    const elements = new Map()

    let topLeftPointX: number = 0
    let topLeftPointY: number = 0
    let bottomRightPointX: number = 0
    let bottomRightPointY: number = 0

    tlpX = Math.round(tlpX * 100) / 100
    tlpY = Math.round(tlpY * 100) / 100
    brpX = Math.round(brpX * 100) / 100
    brpY = Math.round(brpY * 100) / 100

    let prevWidth = 0
    let prevHeight = 0
    if (typeof (oldTlpX) === "number" && typeof (oldTlpY) === "number" && typeof (oldBrpX) === "number" && typeof (oldBrpY) === "number") {
        prevWidth = Math.round((oldBrpX - oldTlpX) * 100) / 100
        prevHeight = Math.round((oldBrpY - oldTlpY) * 100) / 100
    }

    const width = Math.round((brpX - tlpX) * 100) / 100
    const height = Math.round((brpY - tlpY) * 100) / 100
    let viewBoxWidth = Math.round(width * 10 * 100) / 100

    const viewBoxHeight = prevWidth > prevHeight
        ? Math.round(height * 10 * 100) / 100
        : Math.round(height * 10 / 16 * 9 * 100) / 100

    let startVieBoxX = 0
    let startVieBoxY = 0
    switch (pointIndex) {
        case 0:
            startVieBoxX = viewBoxWidth - (width / prevWidth) * viewBoxWidth
            startVieBoxY = viewBoxHeight - (height / prevHeight) * viewBoxHeight
            break
        case 1:
            startVieBoxY = viewBoxHeight - (height / prevHeight) * viewBoxHeight
            break
        case 2:
            startVieBoxY = viewBoxHeight - (height / prevHeight) * viewBoxHeight
            break
        case 3:
            startVieBoxX = (prevWidth - prevWidth * (width / prevWidth)) * 10
            viewBoxWidth = Math.round(prevWidth * 10 * 100) / 100
            break
        case 5:
            viewBoxWidth = Math.round(prevWidth * 10 * 100) / 100
            startVieBoxX = (prevWidth - prevWidth * (width / prevWidth)) * 10
            break
    }

    const d = `M ${startVieBoxX}, ${startVieBoxY} H ${viewBoxWidth} V ${viewBoxHeight} H ${startVieBoxX} V ${startVieBoxY}`
    const elementBorder = selectionBorder.children[0]
    if (elementBorder) elementBorder.setAttribute('d', d)

    editor.elements.forEach(e => {
        if (!editor.selectionElementsId.includes(e.id)) return

        if (typeof (oldTlpX) === "number" && typeof (oldTlpY) === "number" && typeof (oldBrpX) === "number" && typeof (oldBrpY) === "number") {
            topLeftPointX = tlpX + differenceX * (e.topLeftPoint.x - oldTlpX)
            topLeftPointY = tlpY + differenceY * (e.topLeftPoint.y - oldTlpY)
            bottomRightPointX = brpX - differenceX * (oldBrpX - e.bottomRightPoint.x)
            bottomRightPointY = brpY - differenceY * (oldBrpY - e.bottomRightPoint.y)
        }

        const newTopLeftPoint = new Map([
            ['x', topLeftPointX],
            ['y', topLeftPointY],
        ])

        const newBottomRightPoint = new Map([
            ['x', bottomRightPointX],
            ['y', bottomRightPointY],
        ])

        const newCenter = new Map([
            ['x', e.center.x],
            ['y', e.center.y],
        ])

        const newPos = new Map([
            ['newTopLeftPoint', newTopLeftPoint],
            ['newBottomRightPoint', newBottomRightPoint],
            ['newCenter', newCenter]
        ])

        elements.set(e.id, newPos)
    })

    if (width < 5 || height < 5) payload.set('small', true)

    payload.set('elements', elements)

    return payload
}