package agh.project.GUI;

import agh.project.Classes.EarthMap;
import agh.project.Classes.HellMap;
import agh.project.Classes.SimulationEngine;
import agh.project.Interfaces.IEngine;
import agh.project.Interfaces.IMapUpdateObserver;
import agh.project.Interfaces.IWorldMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class App extends Application implements IMapUpdateObserver {

    private int width = 5;
    private int height = 5;
    private IWorldMap mapOfHell;
    private IWorldMap mapOfEarth;
    private final GridPane gridForHell = new GridPane();
    private final GridPane gridForEarth = new GridPane();
    private GuiElement elementCreator = new GuiElement();
    private IEngine engineForHell;
    private IEngine engineForEarth;

    public void initMaps() throws Exception {

        this.mapOfHell = new HellMap(20, 20);
        this.mapOfEarth = new EarthMap(20, 20);
        elementCreator = new GuiElement();
        this.engineForHell = new SimulationEngine(this.mapOfHell);
        this.engineForEarth = new SimulationEngine(this.mapOfEarth);
        this.engineForHell.addObserver(this);
        this.engineForEarth.addObserver(this);
        int moveDelay = 3000;
        this.engineForHell.setMoveDelay(moveDelay);
        this.engineForEarth.setMoveDelay(moveDelay);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPaneToLabel = new BorderPane();
        Label label = new Label("Welcome to Evolution Generator!\nThis project was created by: \n" +
                "Radoslaw Kluczewski and Szczepan Polak.\n" +
                "In the first step, please select a map variant.");
        borderPaneToLabel.setCenter(label);
        borderPaneToLabel.setStyle("-fx-background-color: white");
        borderPaneToLabel.setOpacity(0.8);
        borderPaneToLabel.setBorder(new Border(new BorderStroke(
                Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        borderPaneToLabel.setMaxSize(400, 90);

        Button hellMapButton = new Button("Hell Map");
        hellMapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String typeOfMap = "Hell Map";
                elementCreator.openMapWindow(primaryStage, startSimulationButton(primaryStage, typeOfMap), typeOfMap);
            }
        });

        Button earthMapButton = new Button("Earth Map");
        earthMapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(() -> {
                    String typeOfMap = "Earth Map";
                    elementCreator.openMapWindow(primaryStage, startSimulationButton(primaryStage, typeOfMap), typeOfMap);
                });
            }
        });
        // add HBox
        HBox buttons = new HBox(borderPaneToLabel, hellMapButton, earthMapButton);
        buttons.setAlignment(Pos.TOP_LEFT);
        buttons.setSpacing(20);

        StackPane main = new StackPane();
        main.getChildren().addAll(buttons);

        // add background
        Image background = new Image("file:src/main/resources/backgroundImage.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(
                BackgroundSize.AUTO,
                BackgroundSize.AUTO,
                false,
                false,
                true,
                false);

        main.setBackground(new Background(new BackgroundImage(
                background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                backgroundSize)));

        Scene scene = new Scene(main, 640, 360);

        primaryStage.setTitle("Evolution Simulator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private HBox startSimulationButton(Stage primaryStage, String typeOfMap) {
        HBox hBox = new HBox();
        Button startButton = new Button("Start Simulation");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    initMaps();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                new Thread(() -> {
                    Platform.runLater(() -> {
                        if (typeOfMap.equals("Hell Map")) {
                            Stage hellSimulation = new Stage();
                            elementCreator.createGrid(mapOfHell, gridForHell);
                            Thread eThread = new Thread(engineForHell);
                            eThread.start();
                            Scene secondScene = new Scene(gridForHell, 500, 500);
                            hellSimulation.setTitle("Hell Simulation");
                            hellSimulation.setScene(secondScene);

//              Set position of second window, related to primary window.
                            hellSimulation.setX(primaryStage.getX() - 500);
                            hellSimulation.setY(primaryStage.getY());
                            hellSimulation.show();
                        } else {
                            Stage earthSimulation = new Stage();
                            elementCreator.createGrid(mapOfEarth, gridForEarth);
                            Thread eThread = new Thread(engineForEarth);
                            eThread.start();
                            Scene secondScene = new Scene(gridForEarth, 500, 500);
                            earthSimulation.setTitle("Earth Simulation");
                            earthSimulation.setScene(secondScene);
//              Set position of second window, related to primary window.
                            earthSimulation.setX(primaryStage.getX() + 500);
                            earthSimulation.setY(primaryStage.getY());
                            earthSimulation.show();
                        }
                    });
                }).start();
            }
        });
        hBox.getChildren().add(startButton);
        return hBox;
    }

    @Override
    public void positionChanged() {
        Platform.runLater(() -> {
            gridForHell.getChildren().clear();
            gridForEarth.getChildren().clear();
            elementCreator.createGrid(mapOfHell, gridForHell);
            elementCreator.createGrid(mapOfEarth, gridForEarth);
        });
    }
}
