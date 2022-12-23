package agh.project.Classes;

import agh.project.AbstractClasses.AbstractMapElement;

import java.awt.Color;

public class Plant extends AbstractMapElement {
    private final Vector2d position;

    public Plant(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Position" + position.toString();
    }

    @Override
    public Color getColor() {
        return new Color(47, 241, 6);
    }
}