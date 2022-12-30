package agh.project.Classes;

import agh.project.AbstractClasses.AbstractMapElement;
import agh.project.AbstractClasses.AbstractWorldMap;
import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IWorldMap;

import java.util.*;

public class HellMap extends AbstractWorldMap implements IWorldMap {

    public HellMap(int width, int height) {
        if (width < 0 || height < 0) {
            try {
                throw new IllegalAccessException("Width and height cannot be negative");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        this.lowerLeftCorner = new Vector2d(0, 0);
        this.upperRightCorner = new Vector2d(width, height);
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
    public boolean isOccupiedByAnimal(Vector2d position) {
        return false;
    }

    @Override
    public boolean isOccupiedByAnimals(Vector2d position) {
        return false;
    }

    @Override
    public List<Animal> animalsAt(Vector2d position) {
        if (this.isOccupiedByAnimal(position)){
            return this.animals.get(position);
        }
        return null;
    }

    public Animal priority(List<Animal> animalsList){
        Collections.sort(animalsList);
        if (animalsList.get(0).getEnergy() == animalsList.get(1).getEnergy()){
            Collections.sort(animalsList, new Comparator<Animal>() {
                @Override
                public int compare(Animal animal1, Animal animal2) {
                    return animal1.getAge() - animal2.getAge();
                }
            });
            if(animalsList.get(0).getAge() == animalsList.get(1).getAge()){
                Collections.sort(animalsList, new Comparator<Animal>() {
                    @Override
                    public int compare(Animal animal1, Animal animal2) {
                        return animal1.getKids() - animal2.getKids();
                    }
                });
                if(animalsList.get(0).getKids() == animalsList.get(1).getKids()){
                    List<Animal> temporary = new ArrayList<>();
                    for (Animal animal: animalsList){
                        if(animal.getKids() == animalsList.get(0).getKids()){
                            temporary.add(animal);
                        }else {
                            break;
                        }
                    }
                    Random random = new Random();
                    return temporary.get(random.nextInt(temporary.size()));
                }else {
                    return animalsList.get(0);
                }
            }
            else {
                return animalsList.get(0);
            }
        }else {
            return animalsList.get(0);
        }
    }

    @Override
    public void addDeadPosition(Vector2d position) {

    }

    @Override
    public Vector2d[] getCorners() {
        return new Vector2d[]{this.lowerLeftCorner, this.upperRightCorner};
    }

    private TreeSet<Vector2d> oX = new TreeSet<>(Comparator.comparing(Vector2d -> Vector2d.x));
    private TreeSet<Vector2d> oY = new TreeSet<>(Comparator.comparing(Vector2d -> Vector2d.y));
    public void add(Vector2d vector){
        this.oX.add(vector);
        this.oY.add(vector);
    }
    public void remove(Vector2d vector){
        this.oX.remove(vector);
        this.oY.remove(vector);
    }
}