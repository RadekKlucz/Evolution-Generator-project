package agh.project.Classes;

import agh.project.AbstractClasses.AbstractWorldMap;
import agh.project.EnumClasses.MoveDirection;
import agh.project.Interfaces.IEngine;
import agh.project.Interfaces.IMapUpdateObserver;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;

import java.util.*;

import static java.lang.System.out;

public class SimulationEngine extends AbstractWorldMap implements IEngine {
    private List<Animal> animals;
    private List<Plant> plants;
    private int moveDelay = 0;
    private final IWorldMap map;
    private List<IMapUpdateObserver> observers;
    private boolean paused = false;

    public SimulationEngine(IWorldMap map) {
        this.map = map;
        this.observers = new ArrayList<>();
        addAnimalToMap();
        addPlantToMap();
    }

    private void addAnimalToMap() {
        out.println("TEST1");
        this.map.addAnimal();
        out.println("TEST2");
        animals = this.map.listOfAnimals();

        out.println("TEST3");
    }

    private void addPlantToMap() {
        out.println("TEST4");
        this.map.addPlant();
        out.println("TEST5");
        plants = this.map.listOfPlants();
        out.println("TEST6");
    }

    public void run() {
        //skręt i przemieszczenie każdego zwierzęcia,
        while (true) {
            if (!paused) {
                out.println("LICZBA ANIMALI NA POCZĄTEK DNIA::");
                out.println(animals.size());
                for (Animal animal : animals) {
                    animal.move();
                    //                for (IMapUpdateObserver observer : this.observers) {
                    //                    observer.positionChanged();
                    //                }
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
                            eatingAnimal.addEnergy(energyFromEating); //wczytywane z pliku na początku jedzenie rośliny//
                            plants.remove(plant);
                            this.map.removePlant(plantPosition);
                            plant.positionChanged(plantPosition);
                            eatingAnimal.incrementGrassEaten();
                        } else {
                            Animal eatingAnimal = eatingAnimals.get(0);
                            eatingAnimal.addEnergy(energyFromEating); //wczytywane z pliku na początku jedzenie rośliny//
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
                if (newAnimals.size() > 0) {
                    for (Animal animal : newAnimals) {
                        animals.add(animal);
                    }
                    this.map.AddNewAnimalToMap(newAnimals);
                    for (IMapUpdateObserver observer : this.observers) {
                        observer.positionChanged();
                    }
                }

                out.println("LICZBA ANIMALI W ŚRODKU DNIA");
                out.println(animals.size());

                //wzrastanie nowych roślin na wybranych polach mapy.
                List<Plant> generetedPlantList = this.map.generateDailyPlants();
                for (Plant plant : generetedPlantList) {
                    this.plants.add(plant);
                }


                //usunięcie martwych zwierząt z mapy,         ///////////////nie jestem pewny tego (czy to napewno iteruje po wszystkich zwierzętach?) trzeba jeszcze przemyśleć tą funkcję czy ona żeczywiście aktualizuje zwierzęta na mapie czy tylko lokalnie (listę animals)
                List<Animal> animalListCopy = List.copyOf(animals);
                for (Animal animal : animalListCopy) {
                    if (animal.getEnergy() <= 0) {
                        animals.remove(animal);
                        this.map.removeAnimal(animal.position, animal);
                        //                    this.map.addDeadPosition(animal.position);
                    }
                }
                if (animals.size() <= 0) {
                    break;
                }
                out.println("LICZBA ZWIERZAT PO USUNIECIU ZWIERZAT");
                out.println(animals.size());

                /////////////////////////////


            }

            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException error) {
                out.println("Something goes wrong: " + error);
            }
            for (IMapUpdateObserver observer : this.observers) {
                observer.positionChanged();
            }
            int number = this.map.freePositionsNumber();
            out.println("LICZBA ANIMALI NA KONIEC DNIA");
            out.println(animals.size());
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

    public void pause() {
        this.paused = true;
    }

    public void resume() {
        this.paused = false;
    }

}
