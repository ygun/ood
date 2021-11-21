package com.app;

import com.app.shape.Circle;
import com.app.shape.resizable.Resizable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class ResizableComponentEx extends JFrame {

    public ResizableComponentEx() {
        initUI();
    }

    private void initUI() {

        var pnl = new JPanel(null);
        add(pnl);

        var area = new JPanel();
        area.setBackground(Color.white);
        Resizable res = new Resizable(area);
        res.setBounds(50, 50, 200, 150);
        pnl.add(res);


        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                requestFocus();
                res.repaint();
            }
        });

        setSize(550, 400);
        setTitle("Resizable component");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new ResizableComponentEx();
            ex.setVisible(true);
        });
    }
}
