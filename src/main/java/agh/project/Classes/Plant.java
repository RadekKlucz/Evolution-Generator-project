package agh.project.Classes;

import agh.project.AbstractClasses.AbstractMapElement;
import agh.project.Interfaces.IPositionChangeObserver;


import java.awt.Color;
import java.util.ArrayList;

public class Plant extends AbstractMapElement {
    private final Vector2d position;

    public Plant(Vector2d position) {
        this.position = position;
    }

    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition){
        for (IPositionChangeObserver observer: observers){
            observer.positionChanged(oldPosition,this.position);
        }
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