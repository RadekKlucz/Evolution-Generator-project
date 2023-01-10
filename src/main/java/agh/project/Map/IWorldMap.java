package agh.project.Map;

import agh.project.Elements.Animal;
import agh.project.Elements.Plant;
import agh.project.Elements.Vector2d;
import agh.project.Elements.IMapElement;
import agh.project.Engine.IPositionChangeObserver;

import java.util.List;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author Radosław Kluczewski, Szczepan Polak, apohllo
 */
public interface IWorldMap extends IPositionChangeObserver {
    /**
     * Indicate if any object can move to the given position.
     *
     * @param position The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    boolean canMoveTo(Vector2d position);

    /**
     * Place a animal on the map.
     *
     * @param animal The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    boolean place(Animal animal);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupiedByPlant(Vector2d position);

    /**
     * Return an object at a given position.
     *
     * @param position The position of the object.
     * @return Object or null if the position is not occupied.
     */
    IMapElement objectAt(Vector2d position);

    void specialMoves(Animal animal, Vector2d checkVector);

    boolean isOccupiedByAnimal(Vector2d position);

    boolean isOccupiedByAnimals(Vector2d position);

    List<Animal> animalsAt(Vector2d position);

    List<Animal> copulation();

    Animal priority(List<Animal> animalsList);

    void addDeadPosition(Vector2d position);

    void addPlant();

    void addAnimal();

    List<Plant> generateDailyPlants();

    Vector2d[] getCorners();

    List<Animal> listOfAnimals();

    List<Plant> listOfPlants();

    void removePlant(Vector2d position);

    void AddNewAnimalToMap(List<Animal> animalsList);

    void removeAnimal(Vector2d position, Animal animal);

    int freePositionsNumber();

}