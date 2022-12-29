package agh.project.Classes;

import agh.project.EnumClasses.MapDirection;
import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;

import java.util.*;
import java.util.stream.Stream;

public class EarthMap implements IWorldMap, IPositionChangeObserver {

    private int width;
    private int height;
    private Map<Vector2d, List<Animal>> animals;
    private Map<Vector2d, Plant> plants;
    private int startPlants;
    private int startAnimals;
    private int startEnergy;

    private int dailyPlants;

    private Map<Vector2d, Integer> deadPosition;

    public EarthMap(int width, int height) {
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

    public void specialMoves(Animal animal, Vector2d checkVector) {
        if(checkVector.y < 0){
            animal.direction = MapDirection.NORTH;
        }
        if(checkVector.y > height){
            animal.direction = MapDirection.SOUTH;
        }
        if(checkVector.x < 0){
            int y = animal.position.y;
            animal.position = new Vector2d(width, y);
        }
        if(checkVector.x > width){
            int y = animal.position.y;
            animal.position = new Vector2d(0, y);
        }
        //sprawdź przypadki kiedy jest w rogach

    }
    public void addDeadPosition(Vector2d position) {
        int value = this.deadPosition.get(position);
        value ++;
        this.deadPosition.put(position, value);
    }


    public void generateDailyPlants(){
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
                //potrzebujemy listę z punktami gdzie zwierzęta umierają najczęściej
                Stream<Map.Entry<Vector2d, Integer>> sorted = deadPosition.entrySet().stream().sorted((Collections.reverseOrder(Map.Entry.comparingByValue())));
                sorted.toArray();


                }
            }
            the entries with the “sort()” method, we must first create a list with the set of entries returned by the “entrySet()” method:

            List<Entry<Integer, Integer>> nlist = new ArrayList<>(map.entrySet());


            Now, we will call the sort() method by passing the “comparingByValue()” method as an argument to allow comparison of the entry’s values:

            nlist.sort(Entry.comparingByValue());

            public class MapUtil {
                public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
                    List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
                    list.sort(Entry.comparingByValue());

                    Map<K, V> result = new LinkedHashMap<>();
                    for (Entry<K, V> entry : list) {
                        result.put(entry.getKey(), entry.getValue());
                    }

                    return result;
                }
            }

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
    public boolean isOccupiedByPlant(Vector2d position) { //można użyć tej funkcji do generowania roślin
        return this.plants.containsKey(position);
    }

    @Override
    public IMapElement objectAt(Vector2d position) {
        return null;
    }

    @Override
    public boolean isOccupiedByAnimal(Vector2d position) {
        return this.animals.containsKey(position);
    }

    @Override
    public boolean isOccupiedByAnimals(Vector2d position) {
        //być może lepiej będzie przechowywać zwierzęta w mapie gdzie kluczem
        //będzie vektor a wartowścią będzie jakaś lisata (w tedy możemy przechowywać
        //w jednym kluczu kilka zwierząt)
        return false;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        remove(oldPosition);
        add(newPosition);
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