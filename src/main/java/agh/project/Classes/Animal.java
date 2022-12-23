package agh.project.Classes;

import agh.project.AbstractClasses.AbstractMapElement;
import agh.project.EnumClasses.MapDirection;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;

import java.util.ArrayList;

public class Animal extends AbstractMapElement {
    private IWorldMap map;
    private MapDirection direction = MapDirection.getRandomPosition();

    private int grassEaten = 0;

    private int kids = 0;

    private int age = 0;
    private Vector2d position;
    private int energy;
//    private ArrayList<IPositionChangeObserver> observerList = new ArrayList<>();
    private Genes genes;
    public Animal(IWorldMap map) {
        this.map = map;
    }

    public int getActiveGenIndex(){ // tu trzeba się zastanowić
        return this.genes.getActiveGenIndex();
    }

    //move function
    public void move(){
        ArrayList genesList =  genes.getGenes();
        int gen = (int) genesList.get(genes.getActiveGenIndex());

        switch (gen){
            case 0 -> position.add(this.direction.toUnitVector());
            case 1 -> {
                direction.next();
                position.add(this.direction.toUnitVector());
            }
            case 2 -> {
                direction.next().next();
                position.add(this.direction.toUnitVector());
            }
            case 3 -> {
                direction.next().next().next();
                position.add(this.direction.toUnitVector());
            }
            case 4 -> {
                position.subtract(this.direction.toUnitVector());
            }
            case 5 -> {
                direction.previous().previous().previous();
                position.add(this.direction.toUnitVector());
            }
            case 6 -> {
                direction.previous().previous();
                position.add(this.direction.toUnitVector());
            }
            case 7 -> {
                direction.previous();
                position.add(this.direction.toUnitVector());
            }
        }
        // bez sprawdzania czy można się ruszyć

        this.ageIncrement();
    }

    public void incrementGrassEaten(){
        this.grassEaten += 1;
    }

    public void kidsIncrement(){
        this.kids += 1;
    }

    private void ageIncrement(){
        this.age += 1;
    }
    @Override
    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public Genes getGenes(){return genes;}

    //copulation
    public Animal population(Animal animal){
        //
        return null;
    }

    //energy false usuń
    public boolean checkEnergy(){
        return energy >= 0;
    }

    public void addEnergy(int value){
        energy += value;
    }



    // Czy na pewno bawić się z obrazkami ?
    @Override
    public String getPath() {
        return switch (this.direction) {
            case NORTH -> "src/main/resources/up.png";
            case NORTH_EAST -> "src/main/resources/up-right.png";
            case EAST -> "src/main/resources/right.png";
            case SOUTH_EAST -> "src/main/resources/down-right.png";
            case SOUTH -> "src/main/resources/down.png";
            case SOUTH_WEST -> "src/main/resources/down-left.png";
            case WEST -> "src/main/resources/left.png";
            case NORTH_WEST -> "src/main/resources/up-left.png";
        };
    }
}