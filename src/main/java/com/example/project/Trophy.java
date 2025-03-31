package com.example.project;

//only needs a constructor
public class Trophy extends Sprite{ // child of trophy
    public Trophy(int x, int y) {
        super(x, y);
    }

    @Override
    public String getCoords() {
        return "Trophy: " + super.getCoords();
    }

    @Override
    public String getRowCol(int size) {
        return "Trophy: " + super.getRowCol(size);
    }
}
