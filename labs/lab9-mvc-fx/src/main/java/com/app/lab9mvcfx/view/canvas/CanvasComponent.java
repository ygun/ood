package com.app.lab9mvcfx.view.canvas;

import com.app.lab9mvcfx.controller.ShapeController;
import com.app.lab9mvcfx.domain.frame.ResizingFrame;
import com.app.lab9mvcfx.repository.ShapeObservable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.List;

public class CanvasComponent extends Pane implements ShapeObserver {

    private final ShapeObservable repository;
    private final ShapeController shapeController;
    private Shape selectedShape;

    public CanvasComponent(ShapeObservable repository, ShapeController shapeController) {
        this.repository = repository;
        repository.registerObserver(this);

        this.shapeController = shapeController;
        makeShapesClickable();
    }

    @Override
    public void addShape(Shape shape) {
        this.getChildren().add(shape);
        makeSelectable(shape);
    }

    @Override
    public void remove(Shape shape) {
        this.getChildren().remove(shape);
    }

    @Override
    public void removeSelected(Shape shape) {
        this.getChildren().remove(shape);
        this.getChildren().remove(selectedShape);
        removeSelectCircuit();
    }

    @Override
    public void updateSelectedFill(Color fill) {
        this.selectedShape.setFill(fill);
    }

    @Override
    public void updateSelectedShapeStrokeWidth(Integer newStroke) {
        this.selectedShape.setStrokeWidth(newStroke);
    }

    @Override
    public void updateSelectedShapeStrokeColor(Color newColor) {
        this.selectedShape.setStroke(newColor);
    }

    @Override
    public void setAll(List<Shape> shapes) {
        super.getChildren().setAll(shapes);
    }

    @Override
    public Shape getSelectedShape() {
        return selectedShape;
    }

    private void removeSelectCircuit() {
        this.getChildren().removeIf(ResizingFrame.class::isInstance);
        selectedShape = null;
    }

    private void makeSelectable(Shape shape) {
        shape.setOnMouseClicked(event -> {
            if (selectedShape != shape) {
                this.getChildren().removeIf(ResizingFrame.class::isInstance);
                selectedShape = shape;

                shape.toFront();
                var resizingControl = new ResizingFrame(shape);
                this.getChildren().add(resizingControl);

                shapeController.changeStyleMenuView(true);
            }

            event.consume();
        });
    }


    private void makeShapesClickable() {
        this.setOnMouseClicked(event -> {
            final var parentNode = ((Node) event.getTarget()).getParent();
            if (selectedShape != null && !(parentNode instanceof ResizingFrame)) {
                removeSelectCircuit();
                shapeController.changeStyleMenuView(false);
            }
        });
    }
}
