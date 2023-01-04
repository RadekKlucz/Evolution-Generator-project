package agh.project.Classes;

import javafx.scene.control.TextField;
import agh.project.GUI.GuiElement;

public class DataReader {

    public int mapWidth;
    public int mapHeight;
    public int startAnimalNumber;
    public int startPlantsNumber;
    public int startAnimalEnergy;
    public int moveEnergy;
    public int energyFromEating;
    public int numberOfNewDailyPlants;
    public int neededEnergyToCopulate;
    public int parentEnergyToNewChild;
    public int gensLength;

    public void setMapWidth(TextField textField){
        try {
            this.mapWidth = Integer.parseInt(textField.getText());
            System.out.println("wczytana szerokosc" + mapWidth);
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public void setMapHeight(TextField textField){
        try {
            this.mapHeight = Integer.parseInt(textField.getText());
            System.out.println("wczytana wysokosc" + mapHeight);
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public void setStartAnimalNumber(TextField textField){
        try {
            this.startAnimalNumber = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public void setMoveEnergy(TextField textField){
        try {
            this.moveEnergy = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public void setStartPlantsNumber(TextField textField){
        try {
            this.startPlantsNumber = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public void setEnergyFromEating(TextField textField){
        try {
            this.energyFromEating = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public void setStartAnimalEnergy(TextField textField){
        try {
            this.startAnimalEnergy = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public void setNumberOfNewDailyPlants(TextField textField){
        try {
            this.numberOfNewDailyPlants = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public void setNeededEnergyToCopulate(TextField textField){
        try {
            this.neededEnergyToCopulate = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public void setParentEnergyToNewChild(TextField textField){
        try {
            this.parentEnergyToNewChild = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public void setGensLenght(TextField textField){
        try {
            this.gensLength = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e){
            System.out.println(e+ "Dane startowe nie poprawnie wpisane");
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
