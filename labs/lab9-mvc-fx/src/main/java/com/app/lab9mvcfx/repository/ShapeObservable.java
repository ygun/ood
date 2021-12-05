package com.app.lab9mvcfx.repository;

import com.app.lab9mvcfx.view.canvas.ShapeObserver;

public interface ShapeObservable {
    void registerObserver(ShapeObserver observer);
}
