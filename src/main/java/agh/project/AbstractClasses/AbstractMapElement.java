package agh.project.AbstractClasses;

import agh.project.Classes.Vector2d;
import agh.project.Interfaces.IMapElement;

public abstract class AbstractMapElement implements IMapElement {
    protected Vector2d position;
    protected int energy;

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String getPath() {
        return null;
    }

    public int getEnergy() {
        return energy;
    }
}
