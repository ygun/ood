import React, {Dispatch} from 'react'
import '../../assets/css/slideArea.css'
import {getElements} from "../../functions/getElements"
import {connect} from "react-redux"
import {Editor} from "../../entities/Editor"
import {useEventListener} from "../../customHooks/useEventListner"
import {CHOOSE_ELEMENTS} from "../../store/actionTypes"
import {borderForResize} from "../../functions/getBorderForResize";


const mapStateToProps = (state: Editor) => {
    return {
        state: state
    }
}

const mapDispatchToProps = (dispatch: Dispatch<any>) => {
    return {
        getElements: (editor: Editor) => getElements(editor, dispatch),
        clearSelectionOnLoaded: () => dispatch({type: CHOOSE_ELEMENTS, payload: []})
    }
}

function SlideArea(props: any) {
    let editor = props.state

    let elements: Array<Element>
    elements = props.getElements(editor)

    let handleClearWindow = () => {
        props.clearSelectionOnLoaded()
    }
    useEventListener('unload', handleClearWindow)
    useEventListener('DOMContentLoaded', handleClearWindow)

    return (
        <div id="slide-area" className='slide-area'>
            <svg className={'workspace'} id={'slide_area_'}
                 style={{background: `0 0 / cover`}}>
                {elements}
                {borderForResize()}
            </svg>
        </div>
    )
}

export default connect(mapStateToProps, mapDispatchToProps)(SlideArea)