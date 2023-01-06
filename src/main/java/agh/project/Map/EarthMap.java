package agh.project.Map;

import agh.project.Elements.Vector2d;
import agh.project.Elements.Animal;
import agh.project.Elements.MapDirection;
import agh.project.Engine.IPositionChangeObserver;

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
        this.upperRightCorner = new Vector2d(width - 1, height - 1);
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