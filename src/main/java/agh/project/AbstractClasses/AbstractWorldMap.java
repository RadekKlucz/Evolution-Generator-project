package agh.project.AbstractClasses;

import agh.project.Classes.Animal;
import agh.project.Classes.Plant;
import agh.project.Classes.Vector2d;
import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IWorldMap;

import java.util.*;
import java.util.stream.Collectors;

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
    private Map<Vector2d, Integer> deadPosition;
    private int dailyPlants;


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

    @Override /////////tutaj trzeba się zastanowić może być wiele obiektów
    public IMapElement objectAt(Vector2d position) {
        List objects = (List) animals.get(position);

        if(objects != null){
            return (IMapElement) objects.get(0);
        }
//        else {
//            List objects = (List) plants.get(position);
//            if(objects != null){
//                return (IMapElement) object;
//            }else {
//                return null;
//            }
//        }
        return null;
    }

    @Override
    public void specialMoves(Animal animal, Vector2d checkVector) {

    }

    @Override
    public boolean isOccupiedByAnimal(Vector2d position) {
        return this.animals.containsKey(position);
    }

    @Override
    public boolean isOccupiedByAnimals(Vector2d position) {
        return animals.get(position).size() > 1;
    }

    @Override
    public List<Animal> animalsAt(Vector2d position) {
        if (this.isOccupiedByAnimal(position)){
            return this.animals.get(position);
        }
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
        int value = this.deadPosition.get(position);
        value ++;
        this.deadPosition.put(position, value);
    }

    @Override
    public void addPlant() {
        Random random = new Random();
        int start = 0;

        while (start < startPlants) {
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
            newAnimal.getGenes().setStartGenes(7);////////////////////////////////////////////////zamienić ilość genów na wczytywane z pliku

            animals.put(newRandomVector, new ArrayList<>());
            animals.get(newRandomVector).add(newAnimal);
            // zrobic pozniej klase z wczytywanymi parametrami gdzie dostep bedzie za pomoca geterow
        }
    }

    public void generateDailyPlants(){
        //sorting deadPostion map to use in prefer places
        Map<Vector2d, Integer> sortedMap = deadPosition.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        for(int i = 0; i < dailyPlants; i++){
            Random random = new Random();
            int xRandom;
            int yRandom;

            //random place
            if(random.nextDouble() < 0.2){
                while (true) {
                    boolean repeat = false;
                    xRandom = random.nextInt(this.width);
                    yRandom = random.nextInt(this.height);

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
            }else { //prefer places
                for(Vector2d position : sortedMap.keySet()){
                    if(!isOccupiedByPlant(position)){
                        plants.put(position, new Plant(position));
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void animalPositionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        if (animals.get(oldPosition).size() > 1){
            animals.get(oldPosition).remove(animal);
        }else{
            animals.remove(oldPosition);
        }

        if(!isOccupiedByAnimal(newPosition)){
            animals.put(newPosition, new ArrayList<>());
            animals.get(newPosition).add(animal);
        }else {
            animals.get(newPosition).add(animal);
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Plant plant = plants.remove(oldPosition);
        plants.put(newPosition, plant);
    }


}
