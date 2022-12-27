package agh.project.Classes;

import agh.project.EnumClasses.MapDirection;
import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IWorldMap;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class EarthMap implements IWorldMap {

    private int width;
    private int height;
    private Map<Vector2d, Animal> animals;
    private Map<Vector2d, Plant> plants;
    private int startPlants;
    private int startAnimals;
    private int startEnergy;

    private int dailyPlants;

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
                continue;
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
}