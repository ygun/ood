package com.app.lab9mvcfx.controller;

import com.app.lab9mvcfx.view.menu.MenuComponent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class MenuController extends Pane {

    private ShapeController shapeController;
    private MenuComponent menuComponent;

    public MenuController(ShapeController shapeController) {
        this.shapeController = shapeController;
    }

    public MenuController(ShapeController shapeController, MenuComponent menuComponent) {
        this.shapeController = shapeController;
        this.menuComponent = menuComponent;
    }

    public void addRectangle() {
        var rectangle = new Rectangle(550, 200, 400, 400);
        rectangle.setStroke(Color.PALEGREEN);
        rectangle.setStrokeWidth(1);
        rectangle.setFill(Color.PALEGREEN);

        shapeController.addShape(rectangle);
    }

    public void addEllipse() {
        var ellipse = new Ellipse(750, 400, 200, 200);
        ellipse.setStroke(Color.AQUAMARINE);
        ellipse.setStrokeWidth(1);
        ellipse.setFill(Color.AQUAMARINE);

        shapeController.addShape(ellipse);
    }

    public void showStyleButtons(boolean isShow) {
        menuComponent.showStyleButtons(isShow);
    }

    public void changeFill(Color newColor) {
        shapeController.changeFillColor(newColor);
    }

    public void changeStrokeColor(Color newColor) {
        shapeController.changeStrokeColor(newColor);
    }

    public void changeStrokeWidth(Integer newStroke) {
        shapeController.changeStrokeWidth(newStroke);
    }

    public void setMenuComponent(MenuComponent menuComponent) {
        this.menuComponent = menuComponent;
    }

    public void deleteShape() {
        shapeController.deleteShape();
    }
}
