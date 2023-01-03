package agh.project.Classes;

import agh.project.AbstractClasses.AbstractMapElement;
import agh.project.AbstractClasses.AbstractWorldMap;
import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;

import java.util.*;

public class HellMap extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

//    int width;
//    int height;

    public HellMap(int width, int height) {
        if (width <= 0 || height <= 0) {
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

    @Override
    public void specialMoves(Animal animal, Vector2d checkVector) {
        Random random = new Random();
        int newRandomX = random.nextInt(width);
        int newRandomY = random.nextInt(height);

        animal.position = new Vector2d(newRandomX, newRandomY);
        animal.removeEnergy(5); //// tutaj wartość równa energi potrzebnej do rozmnażania (mamy private w animalu)

    }

    @Override
    public Vector2d[] getCorners() {
        return new Vector2d[]{this.lowerLeftCorner, this.upperRightCorner};
    }

}