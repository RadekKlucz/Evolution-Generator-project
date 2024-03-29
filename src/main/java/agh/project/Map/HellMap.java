package agh.project.Map;

import agh.project.Elements.Vector2d;
import agh.project.Elements.Animal;
import agh.project.Engine.IPositionChangeObserver;

import java.util.*;

public class HellMap extends AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

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
        this.upperRightCorner = new Vector2d(width - 1, height - 1);
        this.generateDeadPosition();
    }

    @Override
    public void specialMoves(Animal animal, Vector2d checkVector) {
        Random random = new Random();
        int newRandomX = random.nextInt(width);
        int newRandomY = random.nextInt(height);

        animal.position = new Vector2d(newRandomX, newRandomY);
        animal.removeEnergy(energyToCopulate);

    }

}