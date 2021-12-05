package com.app.lab9mvcfx.view.canvas;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.List;

public interface ShapeObserver {
    void addShape(Shape shape);

    void setAll(List<Shape> shapes);

    Shape getSelectedShape();

    void remove(Shape shape);

    void removeSelected(Shape shape);

    void updateSelectedFill(Color fill);

    void updateSelectedShapeStrokeWidth(Integer newStroke);

    void updateSelectedShapeStrokeColor(Color newColor);
}
