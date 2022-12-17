package agh.project.Classes;

import agh.project.AbstractClasses.AbstractMapElement;

public class Plant extends AbstractMapElement {
    private final Vector2d position;

    public Plant(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return position;
        // czy energie trawy tez zdefiniować?
    }

    @Override
    public String toString() {
        return "P" + position.toString();
    }

    @Override
    public String getPath() {
        return "src/main/resources/grass.png";
    }
}