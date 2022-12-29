package agh.project.Classes;

import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IWorldMap;

import java.util.*;

public class HellMap implements IWorldMap {
    private int width;
    private int height;
    private Map<Vector2d, List<Animal>> animals;
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



