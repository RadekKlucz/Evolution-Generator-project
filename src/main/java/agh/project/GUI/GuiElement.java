package agh.project.GUI;

import agh.project.Classes.Animal;
import agh.project.Classes.HellMap;
import agh.project.Classes.DataReader;

import agh.project.Classes.SimulationEngine;
import agh.project.Classes.Vector2d;
import agh.project.Interfaces.IEngine;
import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IWorldMap;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import junit.framework.TestResult;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

import static java.lang.System.out;

public class GuiElement extends DataReader{
    private TextField widthText1;
    private TextField heightText1;
    private Image imageDarkRed = null;
    private Image imageRed = null;
    private Image imageOrange = null;
    private Image imageLightOrange = null;
    private Image imageGold = null;
    private Image imageBrown = null;
    private Image imageBlack = null;
    private Image imageGreen = null;

    public GuiElement() {
        try {
            this.imageDarkRed = new Image(new FileInputStream("src/main/resources/darkRed.png"));
            this.imageRed = new Image(new FileInputStream("src/main/resources/red.png"));
            this.imageOrange = new Image(new FileInputStream("src/main/resources/orange.png"));
            this.imageLightOrange = new Image(new FileInputStream("src/main/resources/lightOrange.png"));
            this.imageGold = new Image(new FileInputStream("src/main/resources/gold.png"));
            this.imageBrown = new Image(new FileInputStream("src/main/resources/brown.png"));
            this.imageBlack = new Image(new FileInputStream("src/main/resources/black.png"));
            this.imageGreen = new Image(new FileInputStream("src/main/resources/grass.png"));
        } catch (FileNotFoundException e) {
            out.println("File not found or file could not load" + e);
        }
    }


    public VBox showElement(IMapElement element) {
        ImageView imageView;
        if(element instanceof Animal) {
            var energy = ((Animal) element).getEnergy();
            var startEnergy = ((Animal) element).getStartEnergy();
            if (energy == 0) {
                imageView = new ImageView(imageDarkRed);
            } else if (energy <= startEnergy * 0.3) {
                imageView = new ImageView(imageRed);
            } else if ((energy <= startEnergy * 0.5) && (energy > startEnergy * 0.3)) {
                imageView = new ImageView(imageOrange);
            } else if ((energy <= startEnergy *0.7) && (energy > startEnergy * 0.5)) {
                imageView = new ImageView(imageLightOrange);
            } else if ((energy <= startEnergy) && (energy > startEnergy * 0.7)) {
                imageView = new ImageView(imageGold);
            } else if (energy > startEnergy) {
                imageView = new ImageView(imageBrown);
            } else {
                imageView = new ImageView(imageBlack);
            }
        } else {
            imageView = new ImageView(imageGreen);
        }
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        VBox verticalBox = new VBox(imageView);
        verticalBox.setAlignment(Pos.CENTER);
        return verticalBox;
    }

    public void createGrid(IWorldMap map, GridPane grid) {
        int cellSize = 21;

        Vector2d[] corners = map.getCorners();
        int left = corners[0].x;
        int right = corners[1].x;
        int down = corners[0].y;
        int up = corners[1].y;

        grid.setGridLinesVisible(false);
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();
        grid.setGridLinesVisible(true);

        Label yx = new Label("y \\ x");
        grid.add(yx, 0, 0, 1, 1);
        grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        grid.getRowConstraints().add(new RowConstraints(cellSize));
        GridPane.setHalignment(yx, HPos.CENTER);

        for (int i = 1; i <= right - left + 1; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
            Label label = new Label(String.format("%d", left + i - 1));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, i, 0, 1, 1);
        }

        for (int i = 1; i <= up - down + 1; i++) {
            grid.getRowConstraints().add(new RowConstraints(cellSize));
            Label label = new Label(String.format("%d", up - i + 1));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0, i, 1, 1);
        }

        for (int i = 1; i <= up - down + 1; i++) {
            for (int j = 1; j <= right - left + 1; j++) {
//                out.println("test");
                IMapElement object = map.objectAt(new Vector2d(left + j - 1, up - i + 1));
//                out.println(object);
                if(object != null) {
                    VBox element = showElement(object);
                    GridPane.setHalignment(element, HPos.CENTER);
                    grid.add(element, j, i, 1, 1);
                } else {
                    Label label = new Label("");
                    grid.add(label,  j, i, 1, 1);
                }
            }
        }
    }

    public VBox buttonsForMaps() {
        Label widthLabel = new Label("Width of map: ");
        TextField widthText = new TextField();
        widthText.setText("50");

        Label heightLabel = new Label("Height of map: ");
        TextField heightText = new TextField();
        heightText.setText("70");

        Label amountOfAnimalsLabel = new Label("Amount of start animals: ");
        TextField amountOfStartAnimalsText = new TextField();
        amountOfStartAnimalsText.setText("20");

        Label amountOfPlantsLebel = new Label("Amount of start plants: ");
        TextField amountOfStartPlantsText = new TextField();
        amountOfStartPlantsText.setText("30");

        Label startEnergyLabel = new Label("Start animal energy: ");
        TextField startAnimalEnergyText = new TextField();
        startAnimalEnergyText.setText("40");

        Label moveEnergyLabel = new Label("Move energy: ");
        TextField moveEnergyText = new TextField();
        moveEnergyText.setText("1");

        Label energyFromEatingLabel = new Label("Energy from eating: ");
        TextField energyFromEatingText = new TextField();
        energyFromEatingText.setText("1");

        Label numberOfNewDailyPlantsLabel = new Label("Daily new plant number: ");
        TextField numberOfNewDailyPlantsText = new TextField();
        numberOfNewDailyPlantsText.setText("5");

        Label neededEnergyToCopulateLabel = new Label("Energy to copulate: ");
        TextField neededEnergyToCopulateText = new TextField();
        neededEnergyToCopulateText.setText("20");

        Label parentEnergyToNewChildLabel = new Label("Energy from parent to child: ");
        TextField parentEnergyToNewChildText = new TextField();
        parentEnergyToNewChildText.setText("10");

        Label gensLenghtLabel = new Label("Gens length: ");
        TextField gensLenghtText = new TextField();
        gensLenghtText.setText("8");

        Button stop = new Button("Stop");
        Button resume = new Button("Resume");
        Button save = new Button("Save stats");

        //Button to open file
        Button loadData = new Button("Load data from file");
        loadData.setOnAction(event -> {
            // Utwórz obiekt FileChooser
            FileChooser fileChooser = new FileChooser();

            // Ustaw filtr plików na pliki JSON
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Files JSON", "*.json");
            fileChooser.getExtensionFilters().add(filter);

            // Pokaż okno wyboru pliku i pobierz wybrany plik
            File selectedFile = fileChooser.showOpenDialog(null);

            // Wczytaj plik JSON i przetwórz go
            try {
                // Utwórz obiekt reader, który będzie odczytywał dane z pliku
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));

                // Utwórz obiekt JSONParser
                JSONParser parser = new JSONParser();

                // Odczytaj plik JSON za pomocą obiektu JSONParser i przetwórz go na obiekt JSONObject
                JSONObject json = (JSONObject) parser.parse(reader);
                // Możesz teraz odczytywać poszczególne wartości z obiektu JSONObject według potrzeb
                long width = (long) json.get("width");
                long height = (long) json.get("height");
                long amountOfAnimals = (long) json.get("amountOfAnimals");
                long amountOfPlants = (long) json.get("amountOfPlants");
                long startEnergy = (long) json.get("startEnergy");
                long moveEnergy = (long) json.get("moveEnergy");
                long eatingEnergy = (long) json.get("eatingEnergy");
                long dailyPlantsNumber = (long) json.get("dailyPlantsNumber");
                long energyToCopulate = (long) json.get("energyToCopulate");
                long energyFromParent = (long) json.get("energyFromParent");
                long lengthGens = (long) json.get("lengthGens");
                widthText.setText(String.valueOf(width));
                heightText.setText(String.valueOf(height));
                amountOfStartAnimalsText.setText(String.valueOf(amountOfAnimals));
                amountOfStartPlantsText.setText(String.valueOf(amountOfPlants));
                startAnimalEnergyText.setText(String.valueOf(startEnergy));
                moveEnergyText.setText(String.valueOf(moveEnergy));
                energyFromEatingText.setText(String.valueOf(eatingEnergy));
                numberOfNewDailyPlantsText.setText(String.valueOf(dailyPlantsNumber));
                neededEnergyToCopulateText.setText(String.valueOf(energyToCopulate));
                parentEnergyToNewChildText.setText(String.valueOf(energyFromParent));
                gensLenghtText.setText(String.valueOf(lengthGens));


//                setStartAnimalEnergy(startAnimalEnergyText);
//                dataReader.setStartAnimalNumber(amountOfStartAnimalsText);
//                dataReader.setMoveEnergy(moveEnergyText);
//                dataReader.setStartPlantsNumber(amountOfStartPlantsText);
//                dataReader.setEnergyFromEating(energyFromEatingText);
//                dataReader.setNumberOfNewDailyPlants(numberOfNewDailyPlantsText);
//                dataReader.setNeededEnergyToCopulate(neededEnergyToCopulateText);
//                dataReader.setParentEnergyToNewChild(parentEnergyToNewChildText);
//                dataReader.setGensLenght(gensLenghtText);
                this.widthText1 = widthText;
                this.heightText1 = heightText;

            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }});

        this.widthText1 = widthText;
        this.heightText1 = heightText;

        VBox controls = new VBox(new HBox(widthLabel, widthText), new HBox(heightLabel, heightText),
                new HBox(amountOfAnimalsLabel, amountOfStartAnimalsText), new HBox(amountOfPlantsLebel, amountOfStartPlantsText),
                new HBox(startEnergyLabel, startAnimalEnergyText), new HBox(moveEnergyLabel, moveEnergyText),
                new HBox(energyFromEatingLabel, energyFromEatingText), new HBox(numberOfNewDailyPlantsLabel, numberOfNewDailyPlantsText),
                new HBox(neededEnergyToCopulateLabel, neededEnergyToCopulateText),  new HBox(parentEnergyToNewChildLabel, parentEnergyToNewChildText),
                new HBox(gensLenghtLabel, gensLenghtText), loadData, stop, resume, save);

        return controls;
    }

    public void openMapWindow(Stage primaryStage, HBox startSimulation, String title) {
        // utworzenie nowego okna "Earth Map"
        Stage mapWindow = new Stage();
        if (title.equals("Earth Map")) {
            mapWindow.setTitle("Earth Map");
        } else {
            mapWindow.setTitle("Hell Map");
        }

        // utworzenie kontenera typu StackPane oraz przycisków
        StackPane secondaryLayout  = new StackPane();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(buttonsForMaps(), startSimulation);
        secondaryLayout.getChildren().addAll(vBox);

        // utworzenie nowej sceny z kontenerem jako głównym elementem oraz ustawienie jej wymiarów
        Scene secondScene = new Scene(secondaryLayout, 400, 400);
        mapWindow.setScene(secondScene);

        // ustawienie pozycji okna względem okna głównego

        if (title.equals("Earth Map")) {
            mapWindow.setX(primaryStage.getX() - 300);
            mapWindow.setY(primaryStage.getY() + 200);
        } else {
            mapWindow.setX(primaryStage.getX() - 300);
            mapWindow.setY(primaryStage.getY() - 200);
        }
        // wyświetlenie okna
        mapWindow.show();
    }

    public TextField getWidthText1() {
        return widthText1;
    }

    public TextField getHeightText1() {
        return heightText1;
    }
}
