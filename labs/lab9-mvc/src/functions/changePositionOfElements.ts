import {Editor} from '../entities/Editor'

export function changePositionOfElements(editor: Editor, payload: any): Editor {
    return {
        ...editor,
        elements: editor.elements.filter((element) => {
            if (payload.get('elements').get(element.id)) {
                let newPos = payload.get('elements').get(element.id)
                return {
                    ...element,
                    center: {
                        x: element.center.x = newPos.get('newCenter').get('x'),
                        y: element.center.y = newPos.get('newCenter').get('y')
                    },
                    topLeftPoint: {
                        x: element.topLeftPoint.x = newPos.get('newTopLeftPoint').get('x'),
                        y: element.topLeftPoint.y = newPos.get('newTopLeftPoint').get('y')
                    },
                    bottomRightPoint: {
                        x: element.bottomRightPoint.x = newPos.get('newBottomRightPoint').get('x'),
                        y: element.bottomRightPoint.y = newPos.get('newBottomRightPoint').get('y')
                    }
                }
            }
            return element
        })
    }
}