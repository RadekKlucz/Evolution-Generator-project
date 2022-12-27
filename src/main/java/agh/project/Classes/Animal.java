package agh.project.Classes;

import agh.project.AbstractClasses.AbstractMapElement;
import agh.project.EnumClasses.MapDirection;
import agh.project.Interfaces.IPositionChangeObserver;
import agh.project.Interfaces.IWorldMap;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;

public class Animal extends AbstractMapElement {
    private IWorldMap map;
    public MapDirection direction = MapDirection.getRandomPosition();
    public Vector2d position;
    private int startEnergy;
    private int energy;
    private int energyToCopulate;
    private Genes genes;
    private int grassEaten = 0;
    private int kids = 0;
    private int age = 0;

    //    private ArrayList<IPositionChangeObserver> observerList = new ArrayList<>();

    public Animal(IWorldMap map, Vector2d position, int energy) {
        this.map = map;
        this.position = position;
        this.energy = energy;
        this.genes = new Genes();
    }

    public int getActiveGenIndex(){ // tu trzeba się zastanowić
        return this.genes.getActiveGenIndex();
    }

    //move function
    public void move(){
        ArrayList genesList =  genes.getGenes();
        int gen = (int) genesList.get(genes.getActiveGenIndex());

        //temporary position
        Vector2d tempPosition = this.position;

        switch (gen){
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
        if(!this.map.canMoveTo(this.position)){

            //wtedy użyj spejalnej metody poruszania (w zależności od wariantu mapy)
            this.map.specialMoves(this, tempPosition);

        }else { // wprzeciwnym przypadku zwierzę rusza się w obrębie mapy więc ruch jest poprawny
            this.position = tempPosition;
        }


        this.ageIncrement();
        //trzeba gdzieś uruchomić funkcję aktywującą nowy gen na następny dzień
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
    public Animal copulation(Animal secondParent){
        int childEnergy;
        if ((this.energyToCopulate < this.energy) && (this.energyToCopulate < secondParent.energy)) {
            childEnergy = 2 * this.energyToCopulate;
            this.energy -= this.energyToCopulate; // można za pomocą funkcji remove energy
            secondParent.energy -= this.energyToCopulate; // można za pomocą funkcji remove energy

            Animal child = new Animal(this.map, this.position, childEnergy);
            child.genes.setChildGens(this, secondParent);
            return child;
        }
        return null;
    }

    //energy false usuń
    public boolean checkEnergy(){
        return energy >= 0;
    }

    public void addEnergy(int value){
        energy += value;
    }
    public void removeEnergy(int value){
        energy -= value;
        if (energy < 0){
            energy = 0;
        }
    }

    @Override
    public Color getColor() {
        if(energy == 0) {
            return new Color(0x8C040A); // very dark red
        } else if (energy <= startEnergy * 0.3) {
            return new Color(0xFF0008); // red
        } else if ((energy <= startEnergy * 0.5) && (energy > startEnergy * 0.3)) {
            return new Color(0xFD6801); // orange
        } else if ((energy <= startEnergy *0.7) && (energy > startEnergy * 0.5)) {
            return new Color(0xE59E6C); // light orange
        } else if ((energy <= startEnergy) && (energy > startEnergy * 0.7)) {
            return new Color(0xC1C94F); // gold
        } else if (energy > startEnergy) {
            return new Color(0x3F1010); // brown
        }
        return new Color(0x000000); // dark
    }
}