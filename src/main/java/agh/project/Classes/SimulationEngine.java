package agh.project.Classes;

import agh.project.EnumClasses.MoveDirection;
import agh.project.Interfaces.IEngine;
import agh.project.Interfaces.IMapUpdateObserver;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;

import java.util.*;

import static java.lang.System.out;

public class SimulationEngine implements IEngine {
    private List<Animal> animals;
    private List<Plant> plants;

    private int moveDelay = 0;
    private final IWorldMap map;
    private List<IMapUpdateObserver> observers;


    public SimulationEngine(IWorldMap map){
        this.map = map;
        this.observers = new ArrayList<>();
        addAnimalToMap();
        addPlantToMap();
    }


    private void addAnimalToMap(){
        out.println("TEST1");
        this.map.addAnimal();
        out.println("TEST2");
        animals = this.map.listOfAnimals();
        out.println("TEST3");
    }
    private void addPlantToMap(){
        out.println("TEST4");
        this.map.addPlant();
        out.println("TEST5");
        plants = this.map.listOfPlants();
        out.println("TEST6");
    }
    public void run(){
        //skręt i przemieszczenie każdego zwierzęcia,
        while (true) {
            for (Animal animal : animals) {
                animal.move();
                for (IMapUpdateObserver observer : this.observers) {
                    observer.positionChanged();
                }
//                try {
//                    Thread.sleep(moveDelay);
//                } catch (InterruptedException error) {
//                    out.println("Something goes wrong: " + error);
//                }
            }


            out.println("LICZBA PLANTÓW PRZED FUKCJĄ JEDZENIA");
            out.println(plants.size());
            //konsumpcja roślin na których pola weszły zwierzęta,
            List<Plant> plantsListCopy = List.copyOf(plants);
            for (Plant plant : plantsListCopy) {
                Vector2d plantPosition = plant.getPosition();
                if (this.map.isOccupiedByAnimal(plantPosition)) {
                    List<Animal> eatingAnimals = this.map.animalsAt(plantPosition);

                    if (eatingAnimals != null && eatingAnimals.size() >= 2) {
                        Animal eatingAnimal = this.map.priority(eatingAnimals);
                        eatingAnimal.addEnergy(500); //wczytywane z pliku na początku//
                        plants.remove(plant);
                        this.map.removePlant(plantPosition);
                        plant.positionChanged(plantPosition);
                        eatingAnimal.incrementGrassEaten();
                    }else {
                        Animal eatingAnimal = eatingAnimals.get(0);
                        eatingAnimal.addEnergy(500); //wczytywane z pliku na początku//
                        plants.remove(plant);
                        this.map.removePlant(plantPosition);
                        plant.positionChanged(plantPosition);
                        eatingAnimal.incrementGrassEaten();
                    }
//                    try {
//                        Thread.sleep(moveDelay);
//                    } catch (InterruptedException error) {
//                        out.println("Something goes wrong: " + error);
//                    }
                }
            }
            out.println("LICZBA PLANTÓW NA KONIEC FUKCJI JEDZENIA");
            out.println(plants.size());
            //rozmnażanie się najedzonych zwierząt znajdujących się na tym samym polu,
            List<Animal> newAnimals = this.map.copulation();
            animals.addAll(newAnimals);

            //wzrastanie nowych roślin na wybranych polach mapy.
            List<Plant> generetedPlantList = this.map.generateDailyPlants();
            for(Plant plant : generetedPlantList){
                this.plants.add(plant);
            }


            //usunięcie martwych zwierząt z mapy,         ///////////////nie jestem pewny tego (czy to napewno iteruje po wszystkich zwierzętach?) trzeba jeszcze przemyśleć tą funkcję czy ona żeczywiście aktualizuje zwierzęta na mapie czy tylko lokalnie (listę animals)
            List<Animal> animalListCopy = List.copyOf(animals);
            for (Animal animal : animalListCopy) {
                if (animal.getEnergy() <= 0) {
                    animals.remove(animal);
//                    this.map.addDeadPosition(animal.position);
                }
            }

            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException error) {
                out.println("Something goes wrong: " + error);
            }
            for (IMapUpdateObserver observer : this.observers) {
                observer.positionChanged();
            }
        }
    }

    @Override
    public void addObserver(IMapUpdateObserver observer) {
            observers.add(observer);
    }

    @Override
    public void setMoveDelay(int moveDelay) {
        this.moveDelay = moveDelay;
    }

//    private Animal priority(List<Animal> animalsList){
//        Collections.sort(animalsList);
//        if (animalsList.get(0).getEnergy() == animalsList.get(1).getEnergy()){
//            Collections.sort(animalsList, new Comparator<Animal>() {
//                @Override
//                public int compare(Animal animal1, Animal animal2) {
//                    return animal1.getAge() - animal2.getAge();
//                }
//            });
//            if(animalsList.get(0).getAge() == animalsList.get(1).getAge()){
//                Collections.sort(animalsList, new Comparator<Animal>() {
//                    @Override
//                    public int compare(Animal animal1, Animal animal2) {
//                        return animal1.getKids() - animal2.getKids();
//                    }
//                });
//                if(animalsList.get(0).getKids() == animalsList.get(1).getKids()){
//                    List<Animal> temporary = new ArrayList<>();
//                    for (Animal animal: animalsList){
//                        if(animal.getKids() == animalsList.get(0).getKids()){
//                            temporary.add(animal);
//                        }else {
//                            break;
//                        }
//                    }
//                    Random random = new Random();
//                    return temporary.get(random.nextInt(temporary.size()));
//                }else {
//                    return animalsList.get(0);
//                }
//            }
//            else {
//                return animalsList.get(0);
//            }
//        }else {
//            return animalsList.get(0);
//        }
//    }

}
