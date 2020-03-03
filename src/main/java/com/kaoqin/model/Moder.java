package com.kaoqin.model;

public class Moder {
    private boolean mode = false;

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Moder{" +
                "mode=" + mode +
                '}';
    }
}
