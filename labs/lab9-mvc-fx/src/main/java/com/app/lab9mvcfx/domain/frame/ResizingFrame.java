package com.app.lab9mvcfx.domain.frame;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ResizingFrame extends Group {
    private final Node targetNode;
    private final Rectangle frame = new Rectangle();
    private static final Integer MIN_WIDTH = 10;
    private static final Integer MIN_HEIGHT = 10;

    private final Anchor topLeft = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
        var newWidth = frame.getWidth() - (newX - oldX);
        if (newWidth > MIN_WIDTH) {
            frame.setX(newX);
            frame.setWidth(newWidth);
        }

        var newHeight = frame.getHeight() - (newY - oldY);
        if (newHeight > MIN_HEIGHT) {
            frame.setY(newY);
            frame.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });

    private final Anchor topCenter = new Anchor(Color.GOLD, false, true, (oldX, oldY, newX, newY) -> {
        var newHeight = frame.getHeight() - (newY - oldY);
        if (newHeight > MIN_HEIGHT) {
            frame.setY(newY);
            frame.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });

    private final Anchor topRight = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
        var newWidth = frame.getWidth() + (newX - oldX);
        if (newWidth > MIN_WIDTH) {
            frame.setWidth(newWidth);
        }

        var newHeight = frame.getHeight() - (newY - oldY);
        if (newHeight > MIN_HEIGHT) {
            frame.setY(newY);
            frame.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });

    private final Anchor rightCenter = new Anchor(Color.GOLD, true, false, (oldX, oldY, newX, newY) -> {
        var newWidth = frame.getWidth() + (newX - oldX);
        if (newWidth > MIN_WIDTH) {
            frame.setWidth(newWidth);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });

    private final Anchor bottomRight = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
        var newWidth = frame.getWidth() + (newX - oldX);
        if (newWidth > MIN_WIDTH) {
            frame.setWidth(newWidth);
        }

        var newHeight = frame.getHeight() + (newY - oldY);
        if (newHeight > MIN_HEIGHT) {
            frame.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });

    private final Anchor bottomCenter = new Anchor(Color.GOLD, false, true, (oldX, oldY, newX, newY) -> {
        var newHeight = frame.getHeight() + (newY - oldY);
        if (newHeight > MIN_HEIGHT) {
            frame.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });

    private final Anchor bottomLeft = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
        var newWidth = frame.getWidth() - (newX - oldX);
        if (newWidth > MIN_WIDTH) {
            frame.setX(newX);
            frame.setWidth(newWidth);
        }

        var newHeight = frame.getHeight() + (newY - oldY);
        if (newHeight > MIN_HEIGHT) {
            frame.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });

    private final Anchor leftCenter = new Anchor(Color.GOLD, true, false, (oldX, oldY, newX, newY) -> {
        var newWidth = frame.getWidth() - (newX - oldX);
        if (newWidth > MIN_WIDTH) {
            frame.setX(newX);
            frame.setWidth(newWidth);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });

    public ResizingFrame(Node targetNode) {
        this.targetNode = targetNode;

        attachFrameRectangle(targetNode);
        attachAnchors();

        frame.toBack();
    }

    private void attachFrameRectangle(Node node) {
        Bounds bounds = node.getBoundsInParent();

        frame.setStyle(
                "-fx-stroke: forestgreen; " +
                        "-fx-stroke-width: 2px; " +
                        "-fx-stroke-dash-array: 12 2 4 2; " +
                        "-fx-stroke-dash-offset: 6; " +
                        "-fx-stroke-line-cap: butt; " +
                        "-fx-fill: rgba(255, 228, 118, 0);"
        );

        frame.setX(bounds.getMinX());
        frame.setY(bounds.getMinY());
        frame.setWidth(bounds.getWidth());
        frame.setHeight(bounds.getHeight());

        DragAndDropUtil.makeDraggable(frame, (oldX, oldY, newX, newY) -> {
            updateAnchorPositions();
            relocateTargetNode(newX, newY);
        });

        getChildren().add(frame);
    }

    private void relocateTargetNode(double newX, double newY) {
        if (targetNode instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) targetNode;
            ellipse.setCenterX(newX + ellipse.getRadiusX());
            ellipse.setCenterY(newY + ellipse.getRadiusY());
        } else if (targetNode instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) targetNode;
            rectangle.setX(newX);
            rectangle.setY(newY);
        } else if (targetNode instanceof Shape) {
            Shape shape = (Shape) targetNode;

            shape.setLayoutX(newX - 668);
            shape.setLayoutY(newY - 332);
        }
    }

    private void resizeTargetNode() {
        if (targetNode instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) targetNode;
            ellipse.setRadiusX(frame.getWidth() / 2);
            ellipse.setRadiusY(frame.getHeight() / 2);

            relocateTargetNode(frame.getX(), frame.getY());
        } else if (targetNode instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) targetNode;
            rectangle.setWidth(frame.getWidth());
            rectangle.setHeight(frame.getHeight());

            relocateTargetNode(frame.getX(), frame.getY());
        } else if (targetNode instanceof Shape) {
            Shape shape = (Shape) targetNode;

            shape.setScaleX(frame.getWidth());

            relocateTargetNode(frame.getX(), frame.getY());
        }
    }

    private void attachAnchors() {
        updateAnchorPositions();

        getChildren().addAll(topLeft, topCenter, topRight, rightCenter, bottomRight, bottomCenter, bottomLeft, leftCenter);
    }

    private void updateAnchorPositions() {
        topLeft.setCenterX(frame.getX());
        topLeft.setCenterY(frame.getY());

        topCenter.setCenterX(frame.getX() + frame.getWidth() / 2);
        topCenter.setCenterY(frame.getY());

        topRight.setCenterX(frame.getX() + frame.getWidth());
        topRight.setCenterY(frame.getY());

        rightCenter.setCenterX(frame.getX() + frame.getWidth());
        rightCenter.setCenterY(frame.getY() + frame.getHeight() / 2);

        bottomRight.setCenterX(frame.getX() + frame.getWidth());
        bottomRight.setCenterY(frame.getY() + frame.getHeight());

        bottomCenter.setCenterX(frame.getX() + frame.getWidth() / 2);
        bottomCenter.setCenterY(frame.getY() + frame.getHeight());

        bottomLeft.setCenterX(frame.getX());
        bottomLeft.setCenterY(frame.getY() + frame.getHeight());

        leftCenter.setCenterX(frame.getX());
        leftCenter.setCenterY(frame.getY() + frame.getHeight() / 2);
    }
}
