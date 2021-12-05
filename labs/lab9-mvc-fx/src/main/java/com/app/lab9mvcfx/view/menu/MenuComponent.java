package com.app.lab9mvcfx.view.menu;

import com.app.lab9mvcfx.controller.MenuController;
import com.app.lab9mvcfx.controller.ShapeController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import java.util.LinkedList;
import java.util.List;

public class MenuComponent extends ToolBar {

    private final MenuController menuController;

    private final Button addRectangle = new Button("Add rectangle");
    private final Button addEllipse = new Button("Add ellipse");

    private final Button deleteShape = new Button("Delete shape");

    private final ColorPicker fillPicker = new ColorPicker();
    private final Label fillPickerLabel = new Label("Fill: ");

    private final ColorPicker strokeColorPicker = new ColorPicker();
    private final Label strokeColorPickerLabel = new Label("Stroke color: ");

    private final MenuButton strokeWidth = new MenuButton();


    public MenuComponent(ShapeController shapeController) {
        menuController = new MenuController(shapeController, this);

        addRectangle.setOnAction(event -> menuController.addRectangle());
        addEllipse.setOnAction(event -> menuController.addEllipse());

        this.getItems().addAll(addRectangle, addEllipse);

        var separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        this.getItems().add(separator);

        deleteShape.setOnAction(event -> menuController.deleteShape());

        strokeWidth.setText("Stroke width");
        strokeWidth.getItems().setAll(getStokeSelectItems());
        strokeWidth.setAlignment(Pos.CENTER);

        fillPicker.setOnAction(event -> menuController.changeFill(fillPicker.getValue()));
        strokeColorPicker.setOnAction(event -> menuController.changeStrokeColor(strokeColorPicker.getValue()));


        this.getItems().add(deleteShape);

        this.getItems().add(fillPickerLabel);
        this.getItems().add(fillPicker);

        this.getItems().add(strokeColorPickerLabel);
        this.getItems().add(strokeColorPicker);
        this.getItems().add(strokeWidth);

        showStyleButtons(false);
    }


    public void showStyleButtons(boolean isShow) {
        deleteShape.setVisible(isShow);

        fillPickerLabel.setVisible(isShow);
        fillPicker.setVisible(isShow);

        strokeColorPickerLabel.setVisible(isShow);
        strokeColorPicker.setVisible(isShow);
        strokeWidth.setVisible(isShow);
    }

    private List<MenuItem> getStokeSelectItems() {
        final var stroke1 = new MenuItem("1");
        stroke1.setOnAction(event -> menuController.changeStrokeWidth(1));

        final var stroke2 = new MenuItem("2");
        stroke2.setOnAction(event -> menuController.changeStrokeWidth(2));

        final var stroke3 = new MenuItem("3");
        stroke3.setOnAction(event -> menuController.changeStrokeWidth(3));

        final var stroke5 = new MenuItem("5");
        stroke5.setOnAction(event -> menuController.changeStrokeWidth(5));

        final var stroke8 = new MenuItem("8");
        stroke8.setOnAction(event -> menuController.changeStrokeWidth(8));

        final var stroke13 = new MenuItem("13");
        stroke13.setOnAction(event -> menuController.changeStrokeWidth(13));

        final var stroke21 = new MenuItem("21");
        stroke21.setOnAction(event -> menuController.changeStrokeWidth(21));

        return new LinkedList<>(List.of(stroke1, stroke2, stroke3, stroke5, stroke8, stroke13, stroke21));
    }
}
