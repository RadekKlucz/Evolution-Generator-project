package agh.project.Interfaces;

import agh.project.Classes.Animal;
import agh.project.Classes.Vector2d;

public interface IPositionChangeObserver {
    void animalPositionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);

    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
