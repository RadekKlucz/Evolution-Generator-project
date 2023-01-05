package agh.project.Classes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class DataReader {

    public int width;
    public int height;
    protected int startAnimals;
    protected int startPlants;
    protected int startEnergy;
    protected int moveEnergy;
    protected int energyFromEating;
    protected int dailyPlants;
    protected int energyToCopulate;
    protected int gensLength;

    public DataReader() {
        File selectedFile = new File("src/main/resources/configuration.json");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(selectedFile));

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(reader);
            long width = (long) json.get("width");
            long height = (long) json.get("height");
            long amountOfAnimals = (long) json.get("amountOfAnimals");
            long amountOfPlants = (long) json.get("amountOfPlants");
            long startEnergy = (long) json.get("startEnergy");
            long moveEnergy = (long) json.get("moveEnergy");
            long eatingEnergy = (long) json.get("eatingEnergy");
            long dailyPlantsNumber = (long) json.get("dailyPlantsNumber");
            long energyToCopulate = (long) json.get("energyToCopulate");
            long lengthGens = (long) json.get("lengthGens");

            this.width = (int) width;
            this.height = (int) height;
            this.startAnimals = (int) amountOfAnimals;
            this.startPlants = (int) amountOfPlants;
            this.startEnergy = (int) startEnergy;
            this.moveEnergy = (int) moveEnergy;
            this.energyFromEating = (int) eatingEnergy;
            this.dailyPlants = (int) dailyPlantsNumber;
            this.energyToCopulate = (int) energyToCopulate;
            this.gensLength = (int) lengthGens;


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}