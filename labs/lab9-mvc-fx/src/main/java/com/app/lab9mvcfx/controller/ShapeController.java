package com.app.lab9mvcfx.controller;

import com.app.lab9mvcfx.repository.ShapeRepository;
import com.app.lab9mvcfx.repository.ShapeRepositoryImpl;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class ShapeController {

    private final ShapeRepository repository;
    private MenuController menuController;

    public ShapeController(ShapeRepository repository) {
        this.repository = repository;
    }

    public ShapeController(ShapeRepositoryImpl repository, MenuController menuController) {
        this.repository = repository;
        this.menuController = menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void addShape(Shape shape) {
        repository.addShape(shape);
    }

    public void removeSelectedShape() {
        menuController.showStyleButtons(false);
        repository.removeSelectedShape();
    }

    public void changeFillColor(Color newColor) {
        repository.updateSelectedShapeFill(newColor);
    }

    public void changeStrokeWidth(Integer newStroke) {
        repository.updateSelectedShapeStrokeWidth(newStroke);
    }

    public void changeStrokeColor(Color newColor) {
        repository.updateSelectedShapeStrokeColor(newColor);
    }

    public void changeStyleMenuView(boolean isShow) {
        menuController.showStyleButtons(isShow);
    }

    public void deleteShape() {
        repository.removeSelectedShape();
        changeStyleMenuView(false);
    }
}
