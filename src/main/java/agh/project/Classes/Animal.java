package agh.project.Classes;

import agh.project.AbstractClasses.AbstractMapElement;
import agh.project.EnumClasses.MapDirection;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;

import java.util.ArrayList;

public class Animal extends AbstractMapElement {
    private IWorldMap map;
    private MapDirection direction = MapDirection.getRandomPosition();
    private Vector2d position;
    private int energy;
//    private ArrayList<IPositionChangeObserver> observerList = new ArrayList<>();
    private Genes genes;
    public Animal(IWorldMap map) {
        this.map = map;
    }



    @Override
    public Vector2d getPosition() {
        return null;
    }

    public int getEnergy() {
        return energy;
    }

    // Czy na pewno bawić się z obrazkami ?
    @Override
    public String getPath() {
        return switch (this.direction) {
            case NORTH -> "src/main/resources/up.png";
            case NORTH_EAST -> "src/main/resources/up-right.png";
            case EAST -> "src/main/resources/right.png";
            case SOUTH_EAST -> "src/main/resources/down-right.png";
            case SOUTH -> "src/main/resources/down.png";
            case SOUTH_WEST -> "src/main/resources/down-left.png";
            case WEST -> "src/main/resources/left.png";
            case NORTH_WEST -> "src/main/resources/up-left.png";
        };
    }
}