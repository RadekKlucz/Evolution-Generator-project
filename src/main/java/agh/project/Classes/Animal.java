package agh.project.Classes;

import agh.project.AbstractClasses.AbstractMapElement;
import agh.project.AbstractClasses.AbstractWorldMap;
import agh.project.EnumClasses.MapDirection;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;

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
//        int gen = (int) genesList.get(genes.getActiveGenIndex());
//        System.out.println("INDEX");
//        System.out.println(genes.getActiveGenIndex());

        //temporary position
        Vector2d tempPosition = this.position;

        //old position
        Vector2d oldPosition = this.position;

        switch (gen) {
            case 0 -> position.add(this.direction.toUnitVector());
            case 1 -> {
                direction.next();
//                position.add(this.direction.toUnitVector());
                tempPosition = tempPosition.add(this.direction.toUnitVector());

            }
            case 2 -> {
                direction.next().next();
//                position.add(this.direction.toUnitVector());
                tempPosition = tempPosition.add(this.direction.toUnitVector());
            }
            case 3 -> {
                direction.next().next().next();
//                position.add(this.direction.toUnitVector());
                tempPosition = tempPosition.add(this.direction.toUnitVector());
            }
            case 4 -> {
//                position.subtract(this.direction.toUnitVector());
                tempPosition = tempPosition.subtract(this.direction.toUnitVector());
            }
            case 5 -> {
                direction.previous().previous().previous();
//                position.add(this.direction.toUnitVector());
                tempPosition = tempPosition.add(this.direction.toUnitVector());
            }
            case 6 -> {
                direction.previous().previous();
//                position.add(this.direction.toUnitVector());
                tempPosition = tempPosition.add(this.direction.toUnitVector());
            }
            case 7 -> {
                direction.previous();
//                position.add(this.direction.toUnitVector());
                tempPosition = tempPosition.add(this.direction.toUnitVector());
            }
        }
        // bez sprawdzania czy można się ruszyć(było)

        //dodaje nowy mechanizm
        //jeżeli nie może się ruczyć w dane miejsce(chce wyjść poza mapę)
        if (!this.map.canMoveTo(this.position)) {

            //wtedy użyj spejalnej metody poruszania (w zależności od wariantu mapy)
            this.map.specialMoves(this, tempPosition);

        } else { // wprzeciwnym przypadku zwierzę rusza się w obrębie mapy więc ruch jest poprawny
            this.position = tempPosition;
        }

//        System.out.println("MOVED!!!");
//        positionChanged(oldPosition);
        int activeGenIndex = this.getActiveGenIndex();
        this.genes.nextGen(activeGenIndex);
        this.ageIncrement();
        this.removeEnergy(moveEnergy); ////// wartość z pliku do poruszania się
        positionChanged(oldPosition);

//        System.out.println("OLD POSITION");
//        System.out.println(oldPosition);
//        System.out.println("NEW POSITION");
//        System.out.println(this.position);
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
            this.energy -= this.energyToCopulate; // można za pomocą funkcji remove energy
            secondParent.energy -= this.energyToCopulate; // można za pomocą funkcji remove energy

            Animal child = new Animal(this.map, this.position, childEnergy);
            child.genes.setChildGens(this, secondParent);
            child.genes.mutation();

            this.kidsIncrement();
            secondParent.kidsIncrement();
            System.out.println("DZIECKO SIE RODZI");
            System.out.println("DZIECKO SIe RODZI");
            return child;
        }
        return null;
    }

    //energy false usuń
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