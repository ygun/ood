package com.app.lab9mvcfx.repository;

import com.app.lab9mvcfx.view.canvas.ShapeObserver;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.LinkedList;
import java.util.List;

public class ShapeRepositoryImpl implements ShapeRepository, ShapeObservable {

    private final List<Shape> shapes = new LinkedList<>();
    private Shape selectedShape;
    private final List<ShapeObserver> observers = new LinkedList<>();


    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);

        observers.forEach(e -> e.addShape(shape));
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        observers.forEach(e -> {
            if (e.getSelectedShape() == shape) {
                e.removeSelected(shape);
            } else {
                e.remove(shape);
            }
        });
    }

    @Override
    public void removeSelectedShape() {
        shapes.remove(selectedShape);
        selectedShape = null;
        observers.forEach(e -> e.removeSelected(selectedShape));
    }

    @Override
    public void registerObserver(ShapeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void updateSelectedShapeFill(Color fill) {
        observers.forEach(e -> e.updateSelectedFill(fill));
    }

    @Override
    public void updateSelectedShapeStrokeWidth(Integer newStroke) {
        observers.forEach(e -> e.updateSelectedShapeStrokeWidth(newStroke));
    }

    @Override
    public void updateSelectedShapeStrokeColor(Color newColor) {
        observers.forEach(e -> e.updateSelectedShapeStrokeColor(newColor));
    }
}
