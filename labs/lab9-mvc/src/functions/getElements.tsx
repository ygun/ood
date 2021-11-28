import {ElementType} from "../entities/Elements"
import React, {Dispatch} from "react"
import {selectElements} from "./selectElement"
import {svg} from "react-pick-color/build/components/ColorList/ColorList.style"
import {Editor} from "../entities/Editor"


export const getElements = (editor: Editor, dispatch: Dispatch<any>) => editor.elements.map(e => {
    const width = Math.round((e.bottomRightPoint.x - e.topLeftPoint.x) * 100) / 100
    const height = Math.round((e.bottomRightPoint.y - e.topLeftPoint.y) * 100) / 100

    const viewBoxWidth = Math.round((e.bottomRightPoint.x - e.topLeftPoint.x) * 10 * 100) / 100
    const viewBoxHeight = width > height
        ? Math.round(height * 10 * 100) / 100
        : Math.round(height * 10 / 16 * 9 * 100) / 100
    const viewBox = `0 0, ${viewBoxWidth}, ${viewBoxHeight}`

    const borderColor = `rgb(${e.borderColor.red},${e.borderColor.green},${e.borderColor.blue})`
    const backgroundColor = e.backgroundColor
        ? 'rgb(' + e.backgroundColor.red + ', ' + e.backgroundColor.green + ', ' + e.backgroundColor.blue + ')'
        : 'rgb(255, 255, 255)'

    const elemId = e.id
    const elemParentId = `svg_${elemId}`
    const key = e.id
    let elementPoints = `M 0, 0 H ${viewBoxWidth} V ${viewBoxHeight} H 0 V 0`

    let strokeLinecap: "round" | "square" | undefined = "square"
    let strokeLinejoin: "round" | "miter" | undefined = "miter"

    if (e.type !== ElementType.rectangle) {
        strokeLinecap = "round"
        strokeLinejoin = "round"
    }

    if (e.type === ElementType.ellipse) {
        elementPoints = `M 1,${viewBoxHeight / 2} A ${viewBoxWidth / 2 - 1},${viewBoxHeight / 2 - 1} 0 1, 1 1,${viewBoxHeight / 2 + 0.0001}`
    } else if (e.type === ElementType.triangle) {
        elementPoints = `M ${viewBoxWidth / 2}, 0 L ${viewBoxWidth} ${viewBoxHeight - 1} L 0 ${viewBoxHeight - 1} L ${viewBoxWidth / 2} 0`
    }

    return <svg id={elemParentId} x={e.topLeftPoint.x + '%'} y={e.topLeftPoint.y + '%'} viewBox={viewBox}
                width={width + '%'}
                height={height + '%'} preserveAspectRatio="none" key={key}>
        <path id={elemId} data-is-element={true} d={elementPoints} stroke={borderColor}
              strokeWidth={e.borderWidth} strokeLinejoin={strokeLinejoin}
              strokeLinecap={strokeLinecap} fill={backgroundColor}
              onClick={(evt) => selectElements(evt, e.id, dispatch)}/>
    </svg>
})

