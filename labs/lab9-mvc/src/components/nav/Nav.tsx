import React, {Dispatch} from "react"
import 'bootstrap/dist/css/bootstrap.min.css'
import '../../assets/css/nav.css'
import {Editor} from "../../entities/Editor"
import {connect} from "react-redux"
import {AppBar, Toolbar} from "@material-ui/core"
import UndoIcon from "@material-ui/icons/Undo"
import RedoIcon from "@material-ui/icons/Redo"
import ChangeHistoryIcon from "@material-ui/icons/ChangeHistory"
import RadioButtonUncheckedIcon from "@material-ui/icons/RadioButtonUnchecked"
import CheckBoxOutlineBlankIcon from "@material-ui/icons/CheckBoxOutlineBlank"
import {Dropdown} from "react-bootstrap"
import {
    ADD_ELEMENT,
    CHANGE_ELEMENT_BORDER_COLOR,
    CHANGE_ELEMENT_BORDER_WIDTH,
    CHANGE_ELEMENT_FILL_COLOR,
    DELETE_ELEMENTS,
    REDO,
    UNDO
} from "../../store/actionTypes"
import DeleteRoundedIcon from "@material-ui/icons/DeleteRounded"
import ColorPicker from "react-pick-color"
import LineWeightIcon from "@material-ui/icons/LineWeight"
import {getSelectedElements} from "../../functions/getSelectedElements"
import {changePrimitiveStyleMenu} from "../../functions/changePrimitiveStyleMenu"
import {changeTextStyleMenu} from "../../functions/changeTextStyleMenu"
import {v4 as uuidv4} from "uuid"
import CheckIcon from "@material-ui/icons/Check"
import {DEFAULT_ELLIPSE, DEFAULT_RECTANGLE, DEFAULT_TRIANGLE} from "../../entities/Constants"


const mapStateToProps = (state: Editor) => {
    return {
        state: state,
    }
}


const mapDispatchToProps = (dispatch: Dispatch<any>) => {
    return {
        undo: () => dispatch({type: UNDO}),
        redo: () => dispatch({type: REDO}),

        addTriangle: () => dispatch({type: ADD_ELEMENT, payload: DEFAULT_TRIANGLE}),
        addEllipse: () => dispatch({type: ADD_ELEMENT, payload: DEFAULT_ELLIPSE}),
        addRectangle: () => dispatch({type: ADD_ELEMENT, payload: DEFAULT_RECTANGLE}),
        deleteElements: () => {
            dispatch({type: DELETE_ELEMENTS})
            changePrimitiveStyleMenu(false)
            changeTextStyleMenu(false)
        },

        changeElementBorderColor: (data: string) => dispatch({type: CHANGE_ELEMENT_BORDER_COLOR, payload: data}),
        changeElementFillColor: (data: string) => dispatch({type: CHANGE_ELEMENT_FILL_COLOR, payload: data}),
        changeElementBorderWidth: (data: number) => dispatch({type: CHANGE_ELEMENT_BORDER_WIDTH, payload: data}),
    }
}

const Nav = (props: any) => {
    let editor = props.state

    const elements = getSelectedElements(editor)
    let fillColor: string = ''
    let borderColor: string = ''
    let borderSizeView: number = 0
    if ((elements !== undefined) && (elements != null) && (elements.length >= 1)) {
        let element = elements[0]
        borderColor = `rgb(${element.borderColor.red},${element.borderColor.green},${element.borderColor.blue})`
        borderSizeView = element.borderWidth

        if (element.backgroundColor != null) {
            changeTextStyleMenu(false)
            changePrimitiveStyleMenu(true)
            fillColor = `rgb(${element.backgroundColor.red},${element.backgroundColor.green},${element.backgroundColor.blue})`
        }
    }

    const borderSizes = [1, 2, 3, 4, 8, 12, 16, 24]
    let borderSizeItems = borderSizes.map((borderSize: number) => {
        let opacity: number = 0
        if (borderSize === borderSizeView) {
            opacity = 1
        }

        return <Dropdown.Item key={uuidv4()} className="btn-sm button__onclick"
                              onClick={() => props.changeElementBorderWidth(borderSize)}>
            <CheckIcon fontSize='small' style={{marginRight: '.65rem', opacity: opacity}}/>
            {borderSize} px
        </Dropdown.Item>
    })

    return (
        <div id='nav_bar'>
            <AppBar position="static" className="nav">
                <Toolbar variant="dense">
                    <div id="slide-manipulation-buttons">
                        <button data-title="Undo" type="button"
                                className="btn btn-light btn-sm button__onclick dropbox__button"
                                onClick={() => props.undo()}>
                            <UndoIcon/>
                        </button>

                        <button data-title="Redo" type="button"
                                className="btn btn-light btn-sm button__onclick dropbox__button"
                                onClick={() => props.redo()}>
                            <RedoIcon/>
                        </button>
                    </div>

                    <div className="vertical_separator">&nbsp;</div>

                    <button data-title="Add&nbsp;triangle" type="button"
                            className="btn btn-light btn-sm button__onclick dropbox__button"
                            onClick={() => props.addTriangle()}>
                        <ChangeHistoryIcon/>
                    </button>

                    <button data-title="Add&nbsp;ellipse" type="button"
                            className="btn btn-light btn-sm button__onclick dropbox__button"
                            onClick={() => props.addEllipse()}>
                        <RadioButtonUncheckedIcon/>
                    </button>

                    <button data-title="Add&nbsp;rectangle" type="button"
                            className="btn btn-light btn-sm button__onclick dropbox__button"
                            onClick={() => props.addRectangle()}>
                        <CheckBoxOutlineBlankIcon/>
                    </button>


                    <div className="vertical_separator">&nbsp;</div>

                    {/*delete element*/}
                    <button data-title="Delete&nbsp;elements" id="edit_style_text_delete" type="button"
                            className="btn btn-sm button__onclick dropbox__button hidden" onClick={() =>
                        props.deleteElements()
                    }>
                        <DeleteRoundedIcon/>
                    </button>

                    {/* separator */}
                    <div id="edit_style_text_sep_1" className="vertical_separator hidden">&nbsp;</div>

                    {/*Fill backgroundColor*/}
                    <div data-title="Fill&nbsp;color">
                        <Dropdown id="edit_style_element_fill_color" className="hidden">
                            <Dropdown.Toggle className="btn-light btn-sm btn button__onclick dropbox__button"
                                             variant="success" id="dropdown-slide">
                                <div id="fill_element" className="edit_style_text__font">
                                    <svg className="MuiSvgIcon-root" focusable="false" viewBox="0 0 24 24"
                                         aria-hidden="true">
                                        <path
                                            d="M16.56 8.94L7.62 0 6.21 1.41l2.38 2.38-5.15 5.15c-.59.59-.59 1.54 0 2.12l5.5 5.5c.29.29.68.44 1.06.44s.77-.15 1.06-.44l5.5-5.5c.59-.58.59-1.53 0-2.12zM5.21 10L10 5.21 14.79 10H5.21zM19 11.5s-2 2.17-2 3.5c0 1.1.9 2 2 2s2-.9 2-2c0-1.33-2-3.5-2-3.5z"/>
                                        <path fill={fillColor} d="M0 20h24v4H0z"/>
                                    </svg>
                                </div>
                            </Dropdown.Toggle>

                            <Dropdown.Menu>
                                <ColorPicker color={fillColor} onChange={(color) =>
                                    props.changeElementFillColor(color.hex)
                                } hideAlpha={true} hideInputs={false} theme={{
                                    "background": "#fff",
                                    "inputBackground": "#f4f4f4",
                                    "color": "#262626",
                                    "borderColor": "#ffffff",
                                    "borderRadius": "5px",
                                    "boxShadow": "none",
                                    "width": "280px"
                                }}/>
                            </Dropdown.Menu>
                        </Dropdown>
                    </div>

                    {/*Border color*/}
                    <div data-title="Border&nbsp;color">
                        <Dropdown id="edit_style_element_border_color" className="hidden">
                            <Dropdown.Toggle className="btn-light btn-sm btn button__onclick dropbox__button"
                                             variant="success" id="dropdown-slide">
                                <div id="border_element" className="edit_style_text__font">
                                    <svg className="MuiSvgIcon-root" focusable="false" viewBox="0 0 24 24"
                                         aria-hidden="true">
                                        <path
                                            d="M17.75 7L14 3.25l-10 10V17h3.75l10-10zm2.96-2.96c.39-.39.39-1.02 0-1.41L18.37.29a.9959.9959 0 0 0-1.41 0L15 2.25 18.75 6l1.96-1.96z"/>
                                        <path fill={borderColor} d="M0 20h24v4H0z"/>
                                    </svg>
                                </div>
                            </Dropdown.Toggle>

                            <Dropdown.Menu>
                                <ColorPicker color={borderColor} onChange={(color) =>
                                    props.changeElementBorderColor(color.hex)
                                } hideAlpha={true} hideInputs={false} theme={{
                                    "background": "#fff",
                                    "inputBackground": "#f4f4f4",
                                    "color": "#262626",
                                    "borderColor": "#ffffff",
                                    "borderRadius": "5px",
                                    "boxShadow": "none",
                                    "width": "280px"
                                }}/>
                            </Dropdown.Menu>
                        </Dropdown>
                    </div>

                    {/*Border size*/}
                    <div data-title="Border&nbsp;size">
                        <Dropdown id="edit_style_border_size" className="hidden edit_style_text_size">
                            <Dropdown.Toggle className="btn-light btn-sm btn button__onclick dropbox__button"
                                             variant="success" id="dropdown-slide">
                                <div className="edit_style_text__font">
                                    <LineWeightIcon/>
                                </div>
                            </Dropdown.Toggle>

                            <Dropdown.Menu style={{backgroundColor: '#fff'}}>
                                {borderSizeItems}
                            </Dropdown.Menu>
                        </Dropdown>
                    </div>


                </Toolbar>
            </AppBar>
            <hr className="second_nav__hr"/>
        </div>
    )
};

export default connect(mapStateToProps, mapDispatchToProps)(Nav)
