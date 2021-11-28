import {Point} from './Point'
import {Color} from './Color'

export type {
    Element,
}

enum ElementType {
    triangle,
    rectangle,
    ellipse,
}

export {
    ElementType
}


type Element = {
    id: string;
    center: Point;
    topLeftPoint: Point;
    bottomRightPoint: Point;
    borderColor: Color;
    borderWidth: number;
    backgroundColor: Color | null;
    type: ElementType
}