package com.app.shape.resizable;

import com.app.ResizableBorder;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

public class Resizable extends JComponent {

    public Resizable(Component comp) {
        this(comp, new ResizableBorder(8));
    }

    public Resizable(Component comp, ResizableBorder border) {
        setLayout(new BorderLayout());
        add(comp);
        MouseInputListener resizeListener = new ResizeListener(this);
        addMouseListener(resizeListener);
        addMouseMotionListener(resizeListener);
        setBorder(border);
    }

    public void resize() {
        if (getParent() != null) {
            getParent().revalidate();
        }
    }
}
