package com.app.shape.resizable;

import com.app.ResizableBorder;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeListener extends MouseInputAdapter {

    private final Resizable component;
    private int cursor;
    private Point startPos = null;
    private static final int MIN_SIZE = 50;

    public ResizeListener(Resizable component) {
        this.component = component;
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if (component.hasFocus()) {
            var resizableBorder = (ResizableBorder) component.getBorder();
            component.setCursor(Cursor.getPredefinedCursor(resizableBorder.getCursor(me)));
        }
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        component.setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void mousePressed(MouseEvent me) {

        var resizableBorder = (ResizableBorder) component.getBorder();
        cursor = resizableBorder.getCursor(me);
        startPos = me.getPoint();

        component.requestFocus();
        component.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        if (startPos == null) {
            return;
        }

        var x = component.getX();
        var y = component.getY();
        var w = component.getWidth();
        var h = component.getHeight();

        var dx = me.getX() - startPos.x;
        var dy = me.getY() - startPos.y;

        switch (cursor) {
            case Cursor.N_RESIZE_CURSOR -> northResize(x, y, w, h, dy);
            case Cursor.S_RESIZE_CURSOR -> southResize(me, x, y, w, h, dy);
            case Cursor.W_RESIZE_CURSOR -> westResize(x, y, w, h, dx);
            case Cursor.E_RESIZE_CURSOR -> eastResize(me, x, y, w, h, dx);
            case Cursor.NW_RESIZE_CURSOR -> northWestResize(x, y, w, h, dx, dy);
            case Cursor.NE_RESIZE_CURSOR -> northEastResize(me, x, y, w, h, dx, dy);
            case Cursor.SW_RESIZE_CURSOR -> southWestResize(me, x, y, w, h, dx, dy);
            case Cursor.SE_RESIZE_CURSOR -> southEastResize(me, x, y, w, h, dx, dy);
            case Cursor.MOVE_CURSOR -> moveCursor(dx, dy);
            default -> throw new IllegalStateException("Unexpected value: " + cursor);
        }
        component.setCursor(Cursor.getPredefinedCursor(cursor));
    }

    private void moveCursor(int dx, int dy) {
        System.out.println("dx = " + dx + " ; dy = " + dy);
        var bounds = component.getBounds();
        bounds.translate(dx, dy);
        System.out.println("bounds = " + bounds);
        component.setBounds(bounds);
        component.resize();
    }

    private void southEastResize(MouseEvent me, int x, int y, int w, int h, int dx, int dy) {
        if (w + dx >= MIN_SIZE && h + dy >= MIN_SIZE) {
            component.setBounds(x, y, w + dx, h + dy);
            startPos = me.getPoint();
            component.resize();
        }
    }

    private void southWestResize(MouseEvent me, int x, int y, int w, int h, int dx, int dy) {
        if (w - dx >= MIN_SIZE && h + dy >= MIN_SIZE) {
            component.setBounds(x + dx, y, w - dx, h + dy);
            startPos = new Point(startPos.x, me.getY());
            component.resize();
        }
    }

    private void northEastResize(MouseEvent me, int x, int y, int w, int h, int dx, int dy) {
        if (w + dx >= MIN_SIZE && h - dy >= MIN_SIZE) {
            component.setBounds(x, y + dy, w + dx, h - dy);
            startPos = new Point(me.getX(), startPos.y);
            component.resize();
        }
    }

    private void northWestResize(int x, int y, int w, int h, int dx, int dy) {
        if (w - dx >= MIN_SIZE && h - dy >= MIN_SIZE) {
            component.setBounds(x + dx, y + dy, w - dx, h - dy);
            component.resize();
        }
    }

    private void eastResize(MouseEvent me, int x, int y, int w, int h, int dx) {
        if (w + dx >= MIN_SIZE) {
            component.setBounds(x, y, w + dx, h);
            startPos = me.getPoint();
            component.resize();
        }
    }

    private void westResize(int x, int y, int w, int h, int dx) {
        if (w - dx >= MIN_SIZE) {
            component.setBounds(x + dx, y, w - dx, h);
            component.resize();
        }
    }

    private void southResize(MouseEvent me, int x, int y, int w, int h, int dy) {
        if (h + dy >= MIN_SIZE) {
            component.setBounds(x, y, w, h + dy);
            startPos = me.getPoint();
            component.resize();
        }
    }

    private void northResize(int x, int y, int w, int h, int dy) {
        if (h - dy >= MIN_SIZE) {
            component.setBounds(x, y + dy, w, h - dy);
            component.resize();
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        startPos = null;
    }
}
