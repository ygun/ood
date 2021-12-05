import {store} from "../store/store"
import {Point} from "../entities/Point"
import {getPointOfSelectBorder} from "./getPointOfSelectBorder"
import {v4 as uuidv4} from "uuid"
import {svg} from "react-pick-color/build/components/ColorList/ColorList.style"
import React from "react"

export const getBorderForResize = () => {
    let editor = store.getState()
    if (editor.selectionElementsId.length <= 0) return

    let bottomRightPoint: Point = {
        x: -10000,
        y: -10000
    }
    let topLeftPoint: Point = {
        x: 10000,
        y: 10000
    }
    editor.elements.forEach(e => {
        if (editor.selectionElementsId.includes(e.id)) {
            if (e.bottomRightPoint.x > bottomRightPoint.x) {
                bottomRightPoint.x = e.bottomRightPoint.x
            }

            if (e.bottomRightPoint.y > bottomRightPoint.y) {
                bottomRightPoint.y = e.bottomRightPoint.y
            }

            if (e.topLeftPoint.x < topLeftPoint.x) {
                topLeftPoint.x = e.topLeftPoint.x
            }

            if (e.topLeftPoint.y < topLeftPoint.y) {
                topLeftPoint.y = e.topLeftPoint.y
            }
        }
    })

    return getBorder(topLeftPoint, bottomRightPoint)
}

export function getBorder(topLeftPoint: Point, bottomRightPoint: Point) {
    const width = Math.round((bottomRightPoint.x - topLeftPoint.x) * 100) / 100
    const height = Math.round((bottomRightPoint.y - topLeftPoint.y) * 100) / 100
    const viewBoxWidth = Math.round((bottomRightPoint.x - topLeftPoint.x) * 10 * 100) / 100
    const viewBoxHeight = width > height
        ? Math.round(height * 10 * 100) / 100
        : Math.round(height * 10 / 16 * 9 * 100) / 100

    const viewBox = `0 0, ${viewBoxWidth}, ${viewBoxHeight}`
    const d = `M 0, 0 H ${viewBoxWidth} V ${viewBoxHeight} H 0 V 0`

    const selectedPoints = getPointOfSelectBorder(width, height, viewBoxWidth, viewBoxHeight)
    const points = [
        <path data-value="point" d={selectedPoints[0]} stroke="rgb(255, 255, 255)" strokeWidth="2"
              strokeLinejoin="miter" key={uuidv4()}
              strokeLinecap="square" fill="rgb(26, 115, 232)" style={{cursor: 'nwse-resize'}}/>,
        <path data-value="point" d={selectedPoints[1]} stroke="rgb(255, 255, 255)" strokeWidth="2"
              strokeLinejoin="miter" key={uuidv4()}
              strokeLinecap="square" fill="rgb(26, 115, 232)" style={{cursor: 'ns-resize'}}/>,
        <path data-value="point" d={selectedPoints[2]} stroke="rgb(255, 255, 255)" strokeWidth="2"
              strokeLinejoin="miter" key={uuidv4()}
              strokeLinecap="square" fill="rgb(26, 115, 232)" style={{cursor: 'nesw-resize'}}/>,
        <path data-value="point" d={selectedPoints[3]} stroke="rgb(255, 255, 255)" strokeWidth="2"
              strokeLinejoin="miter" key={uuidv4()}
              strokeLinecap="square" fill="rgb(26, 115, 232)" style={{cursor: 'ew-resize'}}/>,
        <path data-value="point" d={selectedPoints[4]} stroke="rgb(255, 255, 255)" strokeWidth="2"
              strokeLinejoin="miter" key={uuidv4()}
              strokeLinecap="square" fill="rgb(26, 115, 232)" style={{cursor: 'ew-resize'}}/>,
        <path data-value="point" d={selectedPoints[5]} stroke="rgb(255, 255, 255)" strokeWidth="2"
              strokeLinejoin="miter" key={uuidv4()}
              strokeLinecap="square" fill="rgb(26, 115, 232)" style={{cursor: 'nesw-resize'}}/>,
        <path data-value="point" d={selectedPoints[6]} stroke="rgb(255, 255, 255)" strokeWidth="2"
              strokeLinejoin="miter" key={uuidv4()}
              strokeLinecap="square" fill="rgb(26, 115, 232)" style={{cursor: 'ns-resize'}}/>,
        <path data-value="point" d={selectedPoints[7]} stroke="rgb(255, 255, 255)" strokeWidth="2"
              strokeLinejoin="miter" key={uuidv4()}
              strokeLinecap="square" fill="rgb(26, 115, 232)" style={{cursor: 'nwse-resize'}}/>
    ]

    return <svg id='selection-border' x={topLeftPoint.x + '%'} y={topLeftPoint.y + '%'}
                viewBox={viewBox} width={width + '%'} height={height + '%'} preserveAspectRatio="none" key={uuidv4()}
                data-old-d={d}
                data-tlp-x={topLeftPoint.x} data-tlp-y={topLeftPoint.y} data-brp-x={bottomRightPoint.x}
                data-brp-y={bottomRightPoint.y}>
        <path d={d} stroke="rgb(26, 115, 232)" strokeWidth="2" strokeLinejoin="miter"
              strokeLinecap="square" fill="none" className="elem-path elem-path_active"/>
        <svg id='selection-border-points' className="points_container points_container_active">
            {points.map((point) => point)}
        </svg>
    </svg>
}