package agh.project.AbstractClasses;

import agh.project.Classes.Animal;
import agh.project.Classes.DataReader;
import agh.project.Classes.Plant;
import agh.project.Classes.Vector2d;
import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IWorldMap;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap extends AbstractMapElement implements IWorldMap {
    protected Map<Vector2d, List<Animal>> animals = new HashMap<>();
    protected Map<Vector2d, Plant> plants = new HashMap<>();
    protected Vector2d lowerLeftCorner = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected Vector2d upperRightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
    private Map<Vector2d, Integer> deadPosition = new HashMap<>();

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
//        System.out.println("POSITION OBJECT AT:");
//        System.out.println(position);
        if (this.isOccupiedByAnimal(position)) {
            List<Animal> animalsList = animals.get(position);
//            System.out.println("ANIMALS LIST ABJECT AT");
//            System.out.println(animalsList);
            return animalsList.get(0);
        } else if (this.isOccupiedByPlant(position)) {
            return plants.get(position);
        } else {
            return null;
        }


//        if(animalsList != null){
//
//        }else {
//            return null;
//        }

//        List objects = (List) animals.get(position);
//
//        if(objects != null){
//            return (IMapElement) objects.get(0);
//        }

//        else {
//            List objects = (List) plants.get(position);
//            if(objects != null){
//                return (IMapElement) object;
//            }else {
//                return null;
//            }
//        }
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
        if (this.isOccupiedByAnimal(position)) {
            return this.animals.get(position);
        }
        return null;
    }

    @Override
    public List<Animal> copulation() {
        List<Animal> newAnimals = new ArrayList<>();


        for (List<Animal> animalList : animals.values()) {
            boolean CanCopulate = true;

            if (animalList.size() > 1) {
                List<Animal> temporary = new ArrayList<>();
                for (Animal animal : animalList) {
                    if (animal.getEnergy() > 10) {//wartość wczytana z pliku (energia konieczna do rozmnażania)
                        temporary.add(animal);
                    }
                }
                Animal animal1 = null;
                Animal animal2 = null;
                if (temporary.size() > 2) {
                    animal1 = this.priority(temporary);
                    temporary.remove(animal1);
                    animal2 = this.priority(temporary);
                } else if (temporary.size() == 2) {
                    animal1 = temporary.get(0);
                    animal2 = temporary.get(1);
                } else {
                    CanCopulate = false;
                }
//                Animal animal1 = this.priority(temporary);
//                temporary.remove(animal1);
//                Animal animal2 = this.priority(temporary);
                if (CanCopulate) {
                    Animal newAnimal = animal1.copulation(animal2);
                    newAnimals.add(newAnimal);
                }
//                Animal newAnimal =  animal1.copulation(animal2);
//                newAnimals.add(newAnimal);
            }
        }
        System.out.println("LISTA NARODZONYCH ANIMALI");
        System.out.println(newAnimals);
        return newAnimals;
    }

    @Override
    public Animal priority(List<Animal> animalsList) {
        Collections.sort(animalsList);
        System.out.println("SORTED ANIMALS LIST");
        System.out.println(animalsList);
        if (animalsList.get(0).getEnergy() == animalsList.get(1).getEnergy()) {
            Collections.sort(animalsList, new Comparator<Animal>() {
                @Override
                public int compare(Animal animal1, Animal animal2) {
                    return animal1.getAge() - animal2.getAge();
                }
            });
            if (animalsList.get(0).getAge() == animalsList.get(1).getAge()) {
                Collections.sort(animalsList, new Comparator<Animal>() {
                    @Override
                    public int compare(Animal animal1, Animal animal2) {
                        return animal1.getKids() - animal2.getKids();
                    }
                });
                if (animalsList.get(0).getKids() == animalsList.get(1).getKids()) {
                    List<Animal> temporary = new ArrayList<>();
                    for (Animal animal : animalsList) {
                        if (animal.getKids() == animalsList.get(0).getKids()) {
                            temporary.add(animal);
                        } else {
                            break;
                        }
                    }
                    Random random = new Random();
                    return temporary.get(random.nextInt(temporary.size()));
                } else {
                    return animalsList.get(0);
                }
            } else {
                return animalsList.get(0);
            }
        } else {
            return animalsList.get(0);
        }
    }


    @Override
    public void addDeadPosition(Vector2d position) {
//        if (this.deadPosition.get(position) != null){
//            int value = this.deadPosition.get(position);
//            value ++;
//            this.deadPosition.put(position, value);
//        }else {
//            this.deadPosition.put(position, 1);
//        }

        int value = this.deadPosition.get(position);
        value++;
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
                Plant newPlant = new Plant(newRandomPositionForPlant, this);

                plants.put(newRandomPositionForPlant, newPlant);
            }
        }
    }

    @Override
    public void addAnimal() {
//        System.out.println("ADD ANIMAL FUNCTION");
        Random random = new Random();
        for (int i = 0; i < this.startAnimals; i++) {
//            System.out.println("WIDTH and HEIGHT: ");
//            System.out.println(this.width);
//            System.out.println(this.height);
            Vector2d newRandomVector = new Vector2d(random.nextInt(this.width), random.nextInt(this.height));

            Animal newAnimal = new Animal(this, newRandomVector, startEnergy);
            newAnimal.getGenes().setStartGenes(gensLength);
            System.out.println("GENY:");
            System.out.println(newAnimal.getGenes().toString());

            if (isOccupiedByAnimal(newRandomVector)) {
                List<Animal> animalsList = animals.get(newRandomVector);
                animalsList.add(newAnimal);
            } else {
                animals.put(newRandomVector, new ArrayList<>());
                animals.get(newRandomVector).add(newAnimal);
            }
//            animals.put(newRandomVector, new ArrayList<>());
//            animals.get(newRandomVector).add(newAnimal);
            // zrobic pozniej klase z wczytywanymi parametrami gdzie dostep bedzie za pomoca geterow
        }
    }

    @Override
    public void AddNewAnimalToMap(List<Animal> animalsList) {
        for (Animal animal : animalsList) {
            if (isOccupiedByAnimal(animal.position)) {
                List<Animal> animalsCopyList = this.animals.get(animal.position);
                animalsCopyList.add(animal);
            } else {
                List<Animal> newList = new ArrayList<>();
                newList.add(animal);
                animals.put(animal.position, newList);
            }
        }
    }

    public void generateDeadPosition() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                deadPosition.put(new Vector2d(i, j), 0);
            }
        }
    }

    public List<Plant> generateDailyPlants() {
        List<Plant> generetedPlantsList = new ArrayList<>();
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
        for (int i = 0; i < dailyPlants; i++) {
            Random random = new Random();
            int xRandom;
            int yRandom;

            //random place
            if (random.nextDouble() < 0.2) {
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
                        Plant newPlant = new Plant(newRandomVector, this);
                        this.plants.put(newRandomVector, newPlant);
                        generetedPlantsList.add(newPlant);
//                    /////////////////////////////////////////////////////////////////
//                    newGrass.addObserver(boundary);
//                    boundary.put(newRandomVector);
                        break;
                    }
                }
            } else { //prefer places
                for (Vector2d position : sortedMap.keySet()) {
                    if (!isOccupiedByPlant(position)) {
                        Plant newPlant = new Plant(position, this);
                        plants.put(position, newPlant);
                        generetedPlantsList.add(newPlant);
                        break;
                    }
                }
            }
        }
        return generetedPlantsList;
    }

    @Override
    public void animalPositionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        if (animals.get(oldPosition).size() > 1) {
            animals.get(oldPosition).remove(animal);
        } else {
            animals.remove(oldPosition);
        }

        if (!isOccupiedByAnimal(newPosition)) {
            animals.put(newPosition, new ArrayList<>());
            animals.get(newPosition).add(animal);
        } else {
            animals.get(newPosition).add(animal);
        }
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Plant plant = plants.remove(oldPosition);
        plants.put(newPosition, plant);
    }


    public List<Animal> listOfAnimals() {
        int animalCount = animals.values().stream().mapToInt(List::size).sum();
        System.out.println("AKTUALNY TEST LICZBA ANIMALI");
        System.out.println(animalCount);
        List<Animal> allAnimals = animals.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println("All ANIMALS");
        System.out.println(allAnimals.size());
        return allAnimals;
    }

    public List<Plant> listOfPlants() {
        List<Plant> allPlants = new ArrayList<>(plants.values());
//        Collection<Plant> values = plants.values();
//        List<Plant> allPlants = new ArrayList<>(values);
        System.out.println("ALL PLANTS");
        System.out.println(allPlants);

        return allPlants;
    }

    public void removePlant(Vector2d position) {
        this.plants.remove(position);
    }

    public void removeAnimal(Vector2d position, Animal animal) {
        this.animals.get(position).remove(animal);
        if (animals.get(position).size() == 0) {
            animals.remove(position);
        }
    }

    @Override
    public Vector2d[] getCorners() {
        return new Vector2d[]{this.lowerLeftCorner, this.upperRightCorner};
    }

    public int getStartAnimals() {
        return startAnimals;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public void setStartAnimals(int startAnimals) {
        this.startAnimals = startAnimals;
    }

    public void setStartPlants(int startPlants) {
        this.startPlants = startPlants;
    }

    public void setStartEnergy(int startEnergy) {
        this.startEnergy = startEnergy;
    }

    public void setMoveEnergy(int moveEnergy) {
        this.moveEnergy = moveEnergy;
    }

    public void setEnergyFromEating(int energyFromEating) {
        this.energyFromEating = energyFromEating;
    }

    public void setEnergyToCopulate(int energyToCopulate) {
        this.energyToCopulate = energyToCopulate;
    }

    public void setDailyPlants(int dailyPlants) {
        this.dailyPlants = dailyPlants;
    }

    public void setGensLength(int gensLength) {
        this.gensLength = gensLength;
    }
}


