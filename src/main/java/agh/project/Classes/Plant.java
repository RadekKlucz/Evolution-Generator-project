package agh.project.Classes;

import agh.project.AbstractClasses.AbstractMapElement;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;


import java.util.ArrayList;

public class Plant extends AbstractMapElement {
    private final Vector2d position;
    private IWorldMap map;

    public Plant(Vector2d position, IWorldMap map) {
        this.map = map;
        this.position = position;
        addObserver(this.map);
    }

    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, this.position);
        }
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

}