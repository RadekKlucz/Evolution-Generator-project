package agh.project.Classes;

import agh.project.AbstractClasses.AbstractWorldMap;
import agh.project.EnumClasses.MapDirection;
import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EarthMap extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    private int width;
    private int height;
    private Map<Vector2d, List<Animal>> animals;
    private Map<Vector2d, Plant> plants;
    private int startPlants;
    private int startAnimals;
    private int startEnergy;

    private int dailyPlants;
    private Map<Vector2d, Integer> deadPosition;

    public EarthMap(int width, int height) {
        if (width < 0 || height < 0) {
            try {
                throw new IllegalAccessException("Width and height cannot be negative");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        this.width = width;
        this.height = height;
        this.generateDeadPosition();
    }


    @Override
    public Vector2d[] getCorners() {
        return new Vector2d[0];
    }

    public void specialMoves(Animal animal, Vector2d checkVector) {
        if(checkVector.y < 0){
            animal.direction = MapDirection.NORTH;
        }
        if(checkVector.y > height){
            animal.direction = MapDirection.SOUTH;
        }
        if(checkVector.x < 0){
            int y = animal.position.y;
            animal.position = new Vector2d(width, y);
        }
        if(checkVector.x > width){
            int y = animal.position.y;
            animal.position = new Vector2d(0, y);
        }
        //sprawdź przypadki kiedy jest w rogach

    }




}