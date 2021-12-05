import React, {Dispatch} from 'react'
import '../../assets/css/slideArea.css'
import {getElements} from "../../functions/getElements"
import {connect} from "react-redux"
import {Editor} from "../../entities/Editor"
import {useEventListener} from "../../customHooks/useEventListner"
import {CHOOSE_ELEMENTS} from "../../store/actionTypes"
import {getBorderForResize} from "../../functions/getBorderForResize"


const mapStateToProps = (state: Editor) => ({
    state: state
})

const mapDispatchToProps = (dispatch: Dispatch<any>) => ({
    getElements: (editor: Editor) => getElements(editor, dispatch),
    clearSelectionOnLoaded: () => dispatch({type: CHOOSE_ELEMENTS, payload: []})
})

const CanvasArea = (props: any) => {
    const editor = props.state

    const elements: Array<Element> = props.getElements(editor)

    const handleClearWindow = () => {
        props.clearSelectionOnLoaded()
    }
    useEventListener('unload', handleClearWindow)
    useEventListener('DOMContentLoaded', handleClearWindow)

    return (
        <div id="slide-area" className='slide-area'>
            <svg className={'workspace'} id={'slide_area_'}
                 style={{background: `0 0 / cover`}}>
                {elements}
                {getBorderForResize()}
            </svg>
        </div>
    )
}

export default connect(mapStateToProps, mapDispatchToProps)(CanvasArea)