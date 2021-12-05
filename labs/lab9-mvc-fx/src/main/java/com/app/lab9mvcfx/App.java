package com.app.lab9mvcfx;

import com.app.lab9mvcfx.controller.MenuController;
import com.app.lab9mvcfx.controller.ShapeController;
import com.app.lab9mvcfx.repository.ShapeRepositoryImpl;
import com.app.lab9mvcfx.view.canvas.CanvasComponent;
import com.app.lab9mvcfx.view.menu.MenuComponent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        var window = new BorderPane();

        var shapeRepository = new ShapeRepositoryImpl();
        var shapeController = new ShapeController(shapeRepository);
        var menuController = new MenuController(shapeController);
        shapeController.setMenuController(menuController);

        var menu = new MenuComponent(shapeController);
        menuController.setMenuComponent(menu);

        var canvas = new CanvasComponent(shapeRepository, shapeController);

        window.setTop(menu);
        window.setCenter(canvas);

        var windowScene = new Scene(window, 1000, 500, Color.ALICEBLUE);
        addKeyListenerToScene(windowScene, shapeController);

        stage.setScene(windowScene);
        stage.setMaximized(true);
        stage.show();
    }

    private void addKeyListenerToScene(Scene root, ShapeController controller) {
        root.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE) controller.removeSelectedShape();
        });
    }
}
