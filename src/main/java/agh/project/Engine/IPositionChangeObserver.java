package agh.project.Engine;

import agh.project.Elements.Animal;
import agh.project.Elements.Vector2d;

public interface IPositionChangeObserver {
    void animalPositionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);

    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
