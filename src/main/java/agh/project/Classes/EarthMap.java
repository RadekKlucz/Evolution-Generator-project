package agh.project.Classes;

import agh.project.AbstractClasses.AbstractWorldMap;
import agh.project.EnumClasses.MapDirection;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;

public class EarthMap extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

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
        this.lowerLeftCorner = new Vector2d(0, 0);
        this.upperRightCorner = new Vector2d(width, height);
        this.generateDeadPosition();
    }

    public void specialMoves(Animal animal, Vector2d checkVector) {
        if (checkVector.y < 0) {
            animal.direction = MapDirection.NORTH;
            animal.position = new Vector2d(checkVector.x, checkVector.y + 2);
        }
        if (checkVector.y > height) {
            animal.direction = MapDirection.SOUTH;
            animal.position = new Vector2d(checkVector.x, checkVector.y - 2);
        }
        if (checkVector.x < 0) {
            int y = animal.position.y;
            animal.position = new Vector2d(width, y);
        }
        if (checkVector.x > width) {
            int y = animal.position.y;
            animal.position = new Vector2d(0, y);
        }
    }

}