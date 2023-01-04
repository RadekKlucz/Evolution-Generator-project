package agh.project.AbstractClasses;

import agh.project.Classes.DataReader;
import agh.project.Classes.Vector2d;
import agh.project.GUI.GuiElement;
import agh.project.Interfaces.IMapElement;

import java.awt.*;

public abstract class AbstractMapElement extends GuiElement implements IMapElement {
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
