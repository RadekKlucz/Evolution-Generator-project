package agh.project.AbstractClasses;

import agh.project.Classes.Animal;
import agh.project.Classes.Plant;
import agh.project.Classes.Vector2d;
import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IWorldMap;

import java.util.*;

public abstract class AbstractWorldMap  implements IWorldMap {
    protected int width;
    protected int height;
    protected Map<Vector2d, List<Animal>> animals;
    protected Map<Vector2d, Plant> plants;
    protected int startPlants;
    protected int startAnimals;
    protected int startEnergy;
    protected Vector2d lowerLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d upperRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);


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

    }

    @Override
    public boolean isOccupiedByAnimal(Vector2d position) {
        return false;
    }

    @Override
    public boolean isOccupiedByAnimals(Vector2d position) {
        return false;
    }

    @Override
    public List<Animal> animalsAt(Vector2d position) {
        return null;
    }

    @Override
    public List<Animal> copulation(){
        List<Animal> newAnimals = new ArrayList<>();
        for (List<Animal> animalList: animals.values()){
            if(animalList.size() > 1){
                List<Animal> temporary = new ArrayList<>();
                for(Animal animal : animalList){
                    if(animal.getEnergy() > 5){//wartość wczytana z pliku (energia konieczna do rozmnażania)
                        temporary.add(animal);
                    }
                }
                Animal animal1 = this.priority(temporary);
                temporary.remove(animal1);
                Animal animal2 = this.priority(temporary);

                Animal newAnimal =  animal1.copulation(animal2);
                newAnimals.add(newAnimal);
            }
        }
        return newAnimals;
    }

    @Override
    public Animal priority(List<Animal> animalsList) {
        return null;
    }

    @Override
    public void addDeadPosition(Vector2d position) {
    }

    @Override
    public void addPlant(int numberOfPlants) {
        Random random = new Random();
        int start = 0;

        while (start < numberOfPlants) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            Vector2d newRandomPositionForPlant = new Vector2d(x, y);

            if (!isOccupiedByPlant(newRandomPositionForPlant)) {
                start++;
                Plant newPlant = new Plant(newRandomPositionForPlant);
                plants.put(newRandomPositionForPlant, newPlant);
            }
        }
    }

    @Override
    public void addAnimal() {
        Random random = new Random();
        for (int i = 0; i < this.startAnimals; i++) {
            Vector2d newRandomVector = new Vector2d(random.nextInt(this.width), random.nextInt(this.height));

            Animal newAnimal = new Animal(this, newRandomVector, this.startEnergy);
            // zrobic pozniej klase z wczytywanymi parametrami gdzie dostep bedzie za pomoca geterow
        }
    }
}
