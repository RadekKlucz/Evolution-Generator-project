package agh.project.AbstractClasses;

import agh.project.Classes.Vector2d;
import agh.project.Interfaces.IMapElement;

import java.awt.*;

public abstract class AbstractMapElement implements IMapElement {
    protected Vector2d position;
    protected int energy;

    @Override
    public Vector2d getPosition() {
        return position;
    }

//    @Override
//    public Color getColor() {
//        return null;
//    }

    public int getEnergy() {
        return energy;
    }
}
