package agh.project.GUI;

import agh.project.Classes.HellMap;
import agh.project.Classes.SimulationEngine;
import agh.project.Interfaces.IEngine;
import agh.project.Interfaces.IMapUpdateObserver;
import agh.project.Interfaces.IWorldMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HellMapWindow extends Application implements IMapUpdateObserver {

    private IWorldMap map;
    private final GridPane grid = new GridPane();
    private GuiElement elementCreator;
    private IEngine engine;

    @Override
    public void init() throws Exception {
        this.map = new HellMap(20, 20);
        elementCreator = new GuiElement();
        this.engine = new SimulationEngine(this.map);
        this.engine.addObserver(this);
        int moveDelay = 3000;
        this.engine.setMoveDelay(moveDelay);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox bottomsConfiguration = new HBox();
        Button startSimulation = new Button("Start Simulation");
        startSimulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage hellSimulation = new Stage();
                elementCreator.createGrid(map, grid);
                Thread eThread = new Thread(engine);
                eThread.start();
                Scene secondScene = new Scene(grid, 500, 500);
                hellSimulation.setScene(secondScene);

//              Set position of second window, related to primary window.
                hellSimulation.setX(primaryStage.getX() + 300 );
                hellSimulation.setY(primaryStage.getY());
                hellSimulation.show();
            }
        });

        bottomsConfiguration.getChildren().addAll(elementCreator.buttonsForMaps(), startSimulation);
        BorderPane borderPlane = new BorderPane();
        borderPlane.setTop(bottomsConfiguration);
        Scene scene = new Scene(borderPlane, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hell Map");
        // Set position of second window, related to primary window.
        primaryStage.show();
    }

    @Override
    public void positionChanged() {
        Platform.runLater(() -> {
                    grid.getChildren().clear();
                    elementCreator.createGrid(map, grid);
                }
        );
    }

    public static void main(String[] args) {
        Application.launch(HellMapWindow.class, args);
    }
}
