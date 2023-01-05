package agh.project.Engine;

import agh.project.Elements.Animal;
import agh.project.Elements.Plant;
import agh.project.Elements.Vector2d;
import agh.project.Map.AbstractWorldMap;
import agh.project.Map.IMapUpdateObserver;
import agh.project.Map.IWorldMap;

import java.util.*;

import static java.lang.System.out;

public class SimulationEngine extends AbstractWorldMap implements IEngine {
    private List<Animal> animals;
    private List<Plant> plants;
    private int moveDelay = 0;
    private final IWorldMap map;
    private List<IMapUpdateObserver> observers;
    private boolean paused = false;
    private int day = 0;

    public SimulationEngine(IWorldMap map) {
        this.map = map;
        this.observers = new ArrayList<>();
        addAnimalToMap();
        addPlantToMap();
    }

    private void addAnimalToMap() {
        this.map.addAnimal();
        animals = this.map.listOfAnimals();
    }

    private void addPlantToMap() {
        this.map.addPlant();
        plants = this.map.listOfPlants();
    }

    public void run() {
        //skręt i przemieszczenie każdego zwierzęcia,
        while (true) {
            if (!paused) {
                for (Animal animal : animals) {
                    animal.move();
                }
                //konsumpcja roślin na których pola weszły zwierzęta,
                List<Plant> plantsListCopy = List.copyOf(plants);
                for (Plant plant : plantsListCopy) {
                    Vector2d plantPosition = plant.getPosition();
                    if (this.map.isOccupiedByAnimal(plantPosition)) {
                        List<Animal> eatingAnimals = this.map.animalsAt(plantPosition);

                        if (eatingAnimals != null && eatingAnimals.size() >= 2) {
                            Animal eatingAnimal = this.map.priority(eatingAnimals);
                            eatingAnimal.addEnergy(energyFromEating);
                            plants.remove(plant);
                            this.map.removePlant(plantPosition);
                            plant.positionChanged(plantPosition);
                            eatingAnimal.incrementGrassEaten();
                        } else {
                            Animal eatingAnimal = eatingAnimals.get(0);
                            eatingAnimal.addEnergy(energyFromEating);
                            plants.remove(plant);
                            this.map.removePlant(plantPosition);
                            plant.positionChanged(plantPosition);
                            eatingAnimal.incrementGrassEaten();
                        }
                    }
                }

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

                //wzrastanie nowych roślin na wybranych polach mapy.
                List<Plant> generetedPlantList = this.map.generateDailyPlants();
                for (Plant plant : generetedPlantList) {
                    this.plants.add(plant);
                }


                //usunięcie martwych zwierząt z mapy
                List<Animal> animalListCopy = List.copyOf(animals);
                for (Animal animal : animalListCopy) {
                    if (animal.getEnergy() <= 0) {
                        animals.remove(animal);
                        this.map.removeAnimal(animal.position, animal);
                    }
                }
                if (animals.size() <= 0) {
                    break;
                }

                int freeCells = this.map.freePositionsNumber();
                day++;
                saveDataToCsvFile(day, animals.size(), plants.size(), freeCells);

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

    public void pause() {
        this.paused = true;
    }

    public void resume() {
        this.paused = false;
    }

}
