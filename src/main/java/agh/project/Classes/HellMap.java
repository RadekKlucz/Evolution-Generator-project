package agh.project.Classes;

import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IWorldMap;

import java.util.Map;
import java.util.Random;

public class HellMap implements IWorldMap {
    private int width;
    private int height;
    private Map<Vector2d, Animal> animals;
    private Map<Vector2d, Plant> plants;
    private int startPlants;
    private int startAnimals;
    private int startEnergy;


    public HellMap(int width, int height) {
        if (width < 0 || height < 0) {
            try {
                throw new IllegalAccessException("Width and height cannot be negative");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        this.width = width;
        this.height = height;
    }

    public void addPlant() {
//        int maxValue = (int) Math.sqrt(this.startPlants * 10);
        Random rand = new Random();
        int xRandom;
        int yRandom;

        for (int i = 0; i < this.startPlants; i++) {
            while (true) {
                boolean repeat = false;
                xRandom = rand.nextInt(this.width);
                yRandom = rand.nextInt(this.height);

                Vector2d newRandomVector = new Vector2d(xRandom, yRandom);

                for (Vector2d position : plants.keySet())
                    if (position.equals(newRandomVector)) {
                        repeat = true;
                    }

                if (!repeat) {
                    Plant newPlant = new Plant(newRandomVector);
                    this.plants.put(newRandomVector, newPlant);
//                    /////////////////////////////////////////////////////////////////
//                    newGrass.addObserver(boundary);
//                    boundary.put(newRandomVector);
                    break;
                }
            }
        }
    }

    public void addAnimal() {
        Random random = new Random();
        for (int i = 0; i < this.startAnimals; i++) {
            Vector2d newRandomVector = new Vector2d(random.nextInt(this.width), random.nextInt(this.height));

            Animal newAnimal = new Animal(this, newRandomVector, this.startEnergy);
            // zrobic pozniej klase z wczytywanymi parametrami gdzie dostep bedzie za pomoca geterow

        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.x >= 0 && position.x <= this.width && position.y >= 0 && position.y <= this.height);
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public boolean isOccupiedByPlant(Vector2d position) {
        return this.plants.containsKey(position);
    }

    @Override
    public IMapElement objectAt(Vector2d position) {
        return null;
    }

    @Override
    public void specialMoves(Animal animal, Vector2d checkVector) {
        Random random = new Random();
        int newRandomX = random.nextInt(width);
        int newRandomY = random.nextInt(height);

        Vector2d newPosotion = new Vector2d(newRandomX, newRandomY);

        animal.position = newPosotion;
        animal.removeEnergy(5); //// tutaj wartość równa energi potrzebnej do rozmnażania (mamy private w animalu)


    }
}



