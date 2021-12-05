package com.app.lab9mvcfx.domain.frame;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public final class DragAndDropUtil {

    private DragAndDropUtil() {
    }

    public static void enableDrag(Circle node, boolean canDragX, boolean canDragY, DragHandler dragHandler) {
        final var dragDelta = new Delta();
        node.setOnMousePressed(mouseEvent -> {
            dragDelta.x = node.getCenterX() - mouseEvent.getX();
            dragDelta.y = node.getCenterY() - mouseEvent.getY();
            node.getScene().setCursor(Cursor.MOVE);
        });

        node.setOnMouseReleased(mouseEvent -> node.getScene().setCursor(Cursor.HAND));

        node.setOnMouseDragged(mouseEvent -> {
            var oldX = node.getCenterX();
            var oldY = node.getCenterY();

            var newX = mouseEvent.getX() + dragDelta.x;
            if (canDragX && newX > 0 && newX < node.getScene().getWidth()) node.setCenterX(newX);

            var newY = mouseEvent.getY() + dragDelta.y;
            if (canDragY && newY > 0 && newY < node.getScene().getHeight()) node.setCenterY(newY);

            newX = node.getCenterX();
            newY = node.getCenterY();

            if (dragHandler != null && (newX != oldX || newY != oldY)) dragHandler.handle(oldX, oldY, newX, newY);
        });

        node.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) node.getScene().setCursor(Cursor.HAND);
        });

        node.setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) node.getScene().setCursor(Cursor.DEFAULT);
        });
    }

    public static void makeDraggable(Rectangle node, DragHandler dragHandler) {
        final Delta dragDelta = new Delta();

        node.setOnMouseEntered(me -> {
            if (!me.isPrimaryButtonDown()) node.getScene().setCursor(Cursor.HAND);
        });

        node.setOnMouseExited(me -> {
            if (!me.isPrimaryButtonDown()) node.getScene().setCursor(Cursor.DEFAULT);
        });

        node.setOnMousePressed(me -> {
            if (me.isPrimaryButtonDown()) node.getScene().setCursor(Cursor.DEFAULT);

            dragDelta.x = me.getX() - node.getX();
            dragDelta.y = me.getY() - node.getY();
            node.getScene().setCursor(Cursor.MOVE);
        });

        node.setOnMouseReleased(me -> {
            if (!me.isPrimaryButtonDown()) node.getScene().setCursor(Cursor.DEFAULT);
        });

        node.setOnMouseDragged(me -> {
            var oldX = node.getX();
            var oldY = node.getY();

            var tempX = me.getX() - dragDelta.x;
            var tempY = me.getY() - dragDelta.y;

            if (isAbleToDragByX(node, me, tempX)) node.setX(tempX);
            if (isAbleToDragByY(node, me, tempY)) node.setY(tempY);

            var newX = node.getX();
            var newY = node.getY();

            if (dragHandler != null && (newX != oldX || newY != oldY)) dragHandler.handle(oldX, oldY, newX, newY);
        });
    }

    private static boolean isAbleToDragByY(Rectangle node, MouseEvent me, double tempY) {
        return tempY > 0 && ((node.getY() + node.getHeight()) < 800 || (me.getY() < node.getY()));
    }

    private static boolean isAbleToDragByX(Rectangle node, MouseEvent me, double tempX) {
        return tempX > 0 && ((node.getX() + node.getWidth()) < 1520 || (me.getX() < node.getX()));
    }

    private static class Delta {
        double x;
        double y;
    }
}
