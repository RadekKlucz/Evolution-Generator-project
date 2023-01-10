package agh.project.Elements;

import agh.project.Map.AbstractWorldMap;
import agh.project.Engine.IPositionChangeObserver;
import agh.project.Map.IWorldMap;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMap implements Comparable<Animal> {
    private IWorldMap map;
    public MapDirection direction = MapDirection.getRandomPosition();

    public Vector2d position;
    private int energy;
    private Genes genes;
    private int grassEaten = 0;
    private int kids = 0;
    private int age = 0;

    private ArrayList<IPositionChangeObserver> observers = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d position, int energy) {
        this.map = map;
        this.position = position;
        this.energy = energy;
        this.genes = new Genes();
        addObserver(this.map);
    }

    public int getActiveGenIndex() { // tu trzeba się zastanowić
        return this.genes.getActiveGenIndex();
    }

    //move function
    public void move() {
        List<Integer> genesList = genes.getGenes();
        int genIndex = this.genes.getActiveGenIndex();
        int gen = genesList.get(genIndex);

        //temporary position
        Vector2d tempPosition = this.position;

        //old position
        Vector2d oldPosition = this.position;

        switch (gen) {
            case 0 -> position.add(this.direction.toUnitVector());
            case 1 -> {
                direction.next();
                tempPosition = tempPosition.add(this.direction.toUnitVector());

            }
            case 2 -> {
                direction.next().next();
                tempPosition = tempPosition.add(this.direction.toUnitVector());
            }
            case 3 -> {
                direction.next().next().next();
                tempPosition = tempPosition.add(this.direction.toUnitVector());
            }
            case 4 -> {
                tempPosition = tempPosition.subtract(this.direction.toUnitVector());
            }
            case 5 -> {
                direction.previous().previous().previous();
                tempPosition = tempPosition.add(this.direction.toUnitVector());
            }
            case 6 -> {
                direction.previous().previous();
                tempPosition = tempPosition.add(this.direction.toUnitVector());
            }
            case 7 -> {
                direction.previous();
                tempPosition = tempPosition.add(this.direction.toUnitVector());
            }
        }

        //jeżeli nie może się ruczyć w dane miejsce(chce wyjść poza mapę)
        if (!this.map.canMoveTo(this.position)) {

            //wtedy użyj spejalnej metody poruszania (w zależności od wariantu mapy)
            this.map.specialMoves(this, tempPosition);

        } else { // wprzeciwnym przypadku zwierzę rusza się w obrębie mapy więc ruch jest poprawny
            this.position = tempPosition;
        }

        int activeGenIndex = this.getActiveGenIndex();
        this.genes.nextGen(activeGenIndex);
        this.ageIncrement();
        this.removeEnergy(moveEnergy);
        positionChanged(oldPosition);
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Animal other) {
        return this.energy - other.energy;
    }

    public void incrementGrassEaten() {
        this.grassEaten += 1;
    }

    public void kidsIncrement() {
        this.kids += 1;
    }

    private void ageIncrement() {
        this.age += 1;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public Genes getGenes() {
        return genes;
    }

    //copulation
    public Animal copulation(Animal secondParent) {
        int childEnergy;
        if ((this.energyToCopulate < this.energy) && (this.energyToCopulate < secondParent.energy)) {
            childEnergy = 2 * this.energyToCopulate;
            this.energy -= this.energyToCopulate;
            secondParent.energy -= this.energyToCopulate;

            Animal child = new Animal(this.map, this.position, childEnergy);
            child.genes.setChildGens(this, secondParent);
            child.genes.mutation();

            this.kidsIncrement();
            secondParent.kidsIncrement();
            return child;
        }
        return null;
    }

    public boolean checkEnergy() {
        return energy >= 0;
    }

    public void addEnergy(int value) {
        energy += value;
    }

    public void removeEnergy(int value) {
        energy -= value;
        if (energy < 0) {
            energy = 0;
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.animalPositionChanged(oldPosition, this.position, this);
        }
    }

    public int getKids() {
        return kids;
    }

}