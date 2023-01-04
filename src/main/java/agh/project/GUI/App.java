package agh.project.GUI;

import agh.project.Classes.*;
import agh.project.Interfaces.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.System.out;

public class App extends Application implements IMapUpdateObserver {
    private String pathFile;
    private Image backgroundImage = new Image("file:src/main/resources/backgroundImage.jpg");
    private IWorldMap map;
    private final GridPane grid = new GridPane();
    private GuiElement elementCreator;
    private int cellSize;

    private IEngine engine;

    @Override
    public void init() throws Exception {
        this.map = new EarthMap(15, 5);
        this.elementCreator = new GuiElement();
        this.cellSize = 21;
        this.engine = new SimulationEngine(this.map);
        this.engine.addObserver(this);
        int moveDelay = 3000;
        this.engine.setMoveDelay(moveDelay);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting for buttons on the first window
        HBox bottomsInFirstWindow = new HBox();
        bottomsInFirstWindow.setSpacing(20);

        // Earth Map button
        Button buttonStartMap1 = new Button("Earth Map");
        buttonStartMap1.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                // New window (Stage)
                Stage EarthMapWindow = new Stage();
                EarthMapWindow.setTitle("Earth Map");

                // New Layout and button
                StackPane secondaryLayout  = new StackPane();
//                secondaryLayout.getChildren().add(buttonsForMaps(EarthMapWindow));

                // New Scene
                Scene secondScene = new Scene(secondaryLayout, 500, 500);
                EarthMapWindow.setScene(secondScene);

                // Set position of second window, related to primary window.
                EarthMapWindow.setX(primaryStage.getX() + 300);
                EarthMapWindow.setY(primaryStage.getY() + 200);
                EarthMapWindow.show();
            }
        });

        // Hell Map button
        Button buttonStartMap2 = new Button("Hell Map");
        buttonStartMap2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // New window (Stage)
                Stage HellMapWindow = new Stage();
                HellMapWindow.setTitle("Hell Map");

                // New Layout and button
                StackPane secondaryLayout  = new StackPane();
//                secondaryLayout.getChildren().add(buttonsForMaps(HellMapWindow));

                // New Scene
                Scene secondScene = new Scene(secondaryLayout, 500, 500);
                HellMapWindow.setScene(secondScene);

                // Set position of second window, related to primary window.
                HellMapWindow.setX(primaryStage.getX() - 300);
                HellMapWindow.setY(primaryStage.getY() - 200);
                HellMapWindow.show();
            }
        });
        Label label = new Label("Welcome to Evolution Generator. This project was created by\n" +
                "Radoslaw Kluczewski and Szczepan Polak.\n" +
                "In the first step, please select a map variant");



        // Add all new bottoms, label and image to first window
        bottomsInFirstWindow.getChildren().addAll(buttonStartMap1, buttonStartMap2, label);

        // Set border plane and add the first bottoms
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(bottomsInFirstWindow);

        // Set background Image
        BackgroundSize backgroundSize = new BackgroundSize(
                BackgroundSize.AUTO,
                BackgroundSize.AUTO,
                false,
                false,
                true,
                false);

        borderPane.setBackground(new Background(new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize)));

        // New main scene
        Scene scene = new Scene(borderPane, 500, 270);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Evolution Simulator");
        primaryStage.show();
    }

    /**
     * The function to control maps in the gui
     *
     * @param actualStage Actual stage for specified map
     * @return all controls needed to control maps
     */
//    private VBox buttonsForMaps(Stage actualStage) {
//
//        Button startSimulation = new Button("Start Simulation");
//        startSimulation.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                /* tutaj napisac do do odpalenia map jakis warunek
//                * np jesli actualStage is equal Hell Map run Hell map i odwrotnie
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
//            }
//        });
//        Label widthLabel = new Label("Width of map: ");
//        TextField widthText = new TextField();
//        widthText.setText("100");
//        Label heightLabel = new Label("Height of map: ");
//        TextField heightText = new TextField();
//        heightText.setText("100");
//        Label amountOfPlantsLabel = new Label("Amount of plants: ");
//        TextField amountOfPlantsText = new TextField();
//        amountOfPlantsText.setText("20");
//        Label amountOfAnimalsLabel = new Label("Amount of animals: ");
//        TextField amountOfAnimalsText = new TextField();
//        amountOfAnimalsText.setText("30");
//        Label startEnergyLabel = new Label("Start energy: ");
//        TextField startEnergyText = new TextField();
//        startEnergyText.setText("15");
//        Label moveEnergyLabel = new Label("Move energy: ");
//        TextField moveEnergyText = new TextField();
//        moveEnergyText.setText("2");
//        Label jungleRatioLabel = new Label("Jungle Ratio: ");
//        TextField jungleRatioText = new TextField();
//        jungleRatioText.setText("0.25");
//        Button stop = new Button("Stop");
//        Button resume = new Button("Resume");
//        Button save = new Button("Save stats");
//
//        //Button to open file
//        Button loadData = new Button("Load data from file");
//        loadData.setOnAction(event -> {
//            // Utwórz obiekt FileChooser
//            FileChooser fileChooser = new FileChooser();
//
//            // Ustaw filtr plików na pliki JSON
//            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Files JSON", "*.json");
//            fileChooser.getExtensionFilters().add(filter);
//
//            // Pokaż okno wyboru pliku i pobierz wybrany plik
//            File selectedFile = fileChooser.showOpenDialog(null);
//
//            // Wczytaj plik JSON i przetwórz go
//            try {
//                // Utwórz obiekt reader, który będzie odczytywał dane z pliku
//                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
//
//                // Utwórz obiekt JSONParser
//                JSONParser parser = new JSONParser();
//
//                // Odczytaj plik JSON za pomocą obiektu JSONParser i przetwórz go na obiekt JSONObject
//                JSONObject json = (JSONObject) parser.parse(reader);
//                // Możesz teraz odczytywać poszczególne wartości z obiektu JSONObject według potrzeb
//                long width = (long) json.get("width");
//                long height = (long) json.get("height");
//                long amountOfAnimals = (long) json.get("amountOfAnimals");
//                long amountOfPlants = (long) json.get("amountOfPlants");
//                long startEnergy = (long) json.get("startEnergy");
//                long moveEnergy = (long) json.get("moveEnergy");
//                widthText.setText(String.valueOf(width));
//                heightText.setText(String.valueOf(height));
//                amountOfAnimalsText.setText(String.valueOf(amountOfAnimals));
//                amountOfPlantsText.setText(String.valueOf(amountOfPlants));
//                startEnergyText.setText(String.valueOf(startEnergy));
//                moveEnergyText.setText(String.valueOf(moveEnergy));
//            } catch (IOException | ParseException e) {
//                e.printStackTrace();
//            }});
//
//        VBox controls = new VBox(new HBox(widthLabel, widthText), new HBox(heightLabel, heightText),
//                new HBox(amountOfAnimalsLabel, amountOfAnimalsText), new HBox(amountOfPlantsLabel, amountOfPlantsText),
//                new HBox(startEnergyLabel, startEnergyText), new HBox(moveEnergyLabel, moveEnergyText),
//                new HBox(jungleRatioLabel, jungleRatioText), startSimulation, loadData, stop, resume, save);
//        return controls;
//    }

//    public void createGrid() {
//
//        Vector2d[] corners = this.map.getCorners();
//        int left = corners[0].x;
//        int right = corners[1].x;
//        int down = corners[0].y;
//        int up = corners[1].y;
//
//        grid.setGridLinesVisible(false);
//        grid.getRowConstraints().clear();
//        grid.getColumnConstraints().clear();
//        grid.setGridLinesVisible(true);
//
//        Label yx = new Label("y \\ x");
//        grid.add(yx, 0, 0, 1, 1);
//        grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
//        grid.getRowConstraints().add(new RowConstraints(cellSize));
//        GridPane.setHalignment(yx, HPos.CENTER);
//
//        for (int i = 1; i <= right - left + 1; i++) {
//            grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
//            Label label = new Label(String.format("%d", left + i - 1));
//            GridPane.setHalignment(label, HPos.CENTER);
//            grid.add(label, i, 0, 1, 1);
//        }
//
//        for (int i = 1; i <= up - down + 1; i++) {
//            grid.getRowConstraints().add(new RowConstraints(cellSize));
//            Label label = new Label(String.format("%d", up - i + 1));
//            GridPane.setHalignment(label, HPos.CENTER);
//            grid.add(label, 0, i, 1, 1);
//        }
//
//        for (int i = 1; i <= up - down + 1; i++) {
//            for (int j = 1; j <= right - left + 1; j++) {
////                out.println("test");
//                IMapElement object = map.objectAt(new Vector2d(left + j - 1, up - i + 1));
////                out.println(object);
//                if(object != null) {
//                    VBox element = elementCreator.showElement(object);
//                    GridPane.setHalignment(element, HPos.CENTER);
//                    grid.add(element, j, i, 1, 1);
//                } else {
//                    Label label = new Label("");
//                    grid.add(label,  j, i, 1, 1);
//                }
//            }
//        }
//    }

    @Override
    public void positionChanged() {
        Platform.runLater(() -> {
                    grid.getChildren().clear();
//                    createGrid();
                }
        );
    }
}