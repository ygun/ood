package com.app.lab9mvcfx.repository;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public interface ShapeRepository {
    void addShape(Shape shape);
    void removeShape(Shape shape);
    void removeSelectedShape();

    void updateSelectedShapeFill(Color fill);

    void updateSelectedShapeStrokeWidth(Integer newStroke);

    void updateSelectedShapeStrokeColor(Color newColor);
}
