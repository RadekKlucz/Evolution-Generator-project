package agh.project.GUI;

import agh.project.Classes.Animal;
import agh.project.Classes.Vector2d;
import agh.project.Interfaces.IEngine;
import agh.project.Interfaces.IMapElement;
import agh.project.Interfaces.IWorldMap;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

import static java.lang.System.out;

public class GuiElement {
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
            var startEnergy =        ((Animal) element).getStartEnergy();
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

    public void createGrid(IWorldMap map, GridPane grid, GuiElement elementCreator) {
        int cellSize = 2;

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
                    VBox element = elementCreator.showElement(object);
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

        Button startSimulation = new Button("Start Simulation");
        startSimulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                /* tutaj napisac do do odpalenia map jakis warunek
//                 * np jesli actualStage is equal Hell Map run Hell map i odwrotnie
//                 */
////                GridPane grid = createGrid();
//                Stage hellGridWindow = new Stage();
////                createGrid();
//                Thread eThread = new Thread(engine);
//                eThread.start();
//                Scene secondScene = new Scene(grid, 500, 500);
//                hellGridWindow.setScene(secondScene);
//
//                // Set position of second window, related to primary window.
//                hellGridWindow.setX(actualStage.getX() );
//                hellGridWindow.setY(actualStage.getY());
//                hellGridWindow.show();
            }
        });
        Label widthLabel = new Label("Width of map: ");
        TextField widthText = new TextField();
        widthText.setText("100");
        Label heightLabel = new Label("Height of map: ");
        TextField heightText = new TextField();
        heightText.setText("100");
        Label amountOfPlantsLabel = new Label("Amount of plants: ");
        TextField amountOfPlantsText = new TextField();
        amountOfPlantsText.setText("20");
        Label amountOfAnimalsLabel = new Label("Amount of animals: ");
        TextField amountOfAnimalsText = new TextField();
        amountOfAnimalsText.setText("30");
        Label startEnergyLabel = new Label("Start energy: ");
        TextField startEnergyText = new TextField();
        startEnergyText.setText("15");
        Label moveEnergyLabel = new Label("Move energy: ");
        TextField moveEnergyText = new TextField();
        moveEnergyText.setText("2");
        Label jungleRatioLabel = new Label("Jungle Ratio: ");
        TextField jungleRatioText = new TextField();
        jungleRatioText.setText("0.25");
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
                widthText.setText(String.valueOf(width));
                heightText.setText(String.valueOf(height));
                amountOfAnimalsText.setText(String.valueOf(amountOfAnimals));
                amountOfPlantsText.setText(String.valueOf(amountOfPlants));
                startEnergyText.setText(String.valueOf(startEnergy));
                moveEnergyText.setText(String.valueOf(moveEnergy));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }});

        VBox controls = new VBox(new HBox(widthLabel, widthText), new HBox(heightLabel, heightText),
                new HBox(amountOfAnimalsLabel, amountOfAnimalsText), new HBox(amountOfPlantsLabel, amountOfPlantsText),
                new HBox(startEnergyLabel, startEnergyText), new HBox(moveEnergyLabel, moveEnergyText),
                new HBox(jungleRatioLabel, jungleRatioText), startSimulation, loadData, stop, resume, save);
        return controls;
    }

    public void openMapWindow(Stage primaryStage, String title) {
        // utworzenie nowego okna "Earth Map"
        Stage mapWindow = new Stage();
        if (title.equals("Earth Map")) {
            mapWindow.setTitle("Earth Map");
        } else {
            mapWindow.setTitle("Hell Map");
        }

        // utworzenie kontenera typu StackPane oraz przycisków
        StackPane secondaryLayout  = new StackPane();
        secondaryLayout.getChildren().add(buttonsForMaps());

        // utworzenie nowej sceny z kontenerem jako głównym elementem oraz ustawienie jej wymiarów
        Scene secondScene = new Scene(secondaryLayout, 300, 300);
        mapWindow.setScene(secondScene);

        // ustawienie pozycji okna "Earth Map" względem okna głównego

        if (title.equals("Earth Map")) {
            mapWindow.setX(primaryStage.getX() - 300);
            mapWindow.setY(primaryStage.getY() + 200);
        } else {
            mapWindow.setX(primaryStage.getX() - 300);
            mapWindow.setY(primaryStage.getY() - 200);
        }
        // wyświetlenie okna "Earth Map"
        mapWindow.show();
    }


}
