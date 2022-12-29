package agh.project.GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static java.lang.System.out;

public class App extends Application {
    private String pathFile;

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hBoxFirstWindow = new HBox();
        hBoxFirstWindow.setSpacing(20);
        Button buttonStartMap1 = new Button("Earth Map");
        buttonStartMap1.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                // New window (Stage)
                Stage EarthMapWindow = new Stage();
                EarthMapWindow.setTitle("Earth Map");

                StackPane secondaryLayout  = new StackPane();
                secondaryLayout.getChildren().add(buttonsForMaps(EarthMapWindow));

                Scene secondScene = new Scene(secondaryLayout, 300, 200);

                EarthMapWindow.setScene(secondScene);

                // Set position of second window, related to primary window.
                EarthMapWindow.setX(primaryStage.getX() + 200);
                EarthMapWindow.setY(primaryStage.getY() + 100);
                EarthMapWindow.show();
            }
        });

        Button buttonStartMap2 = new Button("Hell Map");
        buttonStartMap2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // New window (Stage)
                Stage HellMapWindow = new Stage();
                HellMapWindow.setTitle("Hell Map");

                // dodanie pierwszej powloki do nowej sceny
                StackPane secondaryLayout  = new StackPane();
                secondaryLayout.getChildren().add(buttonsForMaps(HellMapWindow));

                Scene secondScene = new Scene(secondaryLayout, 300, 200);

                // zatwierdzenie zmian
                HellMapWindow.setScene(secondScene);

                // Set position of second window, related to primary window.
                HellMapWindow.setX(primaryStage.getX() - 200);
                HellMapWindow.setY(primaryStage.getY() - 100);

                HellMapWindow.show();
            }
        });
        Label label = new Label("Please, select a map variant");
        hBoxFirstWindow.getChildren().addAll(buttonStartMap1, buttonStartMap2);
        hBoxFirstWindow.getChildren().add(label);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBoxFirstWindow);

        Scene scene = new Scene(borderPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Evolution Simulator");
        primaryStage.show();
        primaryStage.show();
    }

    private VBox buttonsForMaps(Stage actualStage) {
        // ta funkcja jest do wpisywania wartosci poczatkowych

        Button startSimulation = new Button("Start Simulation");
        startSimulation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /* tutaj napisac do do odpalenia map jakis warunek
                * np jesli actualStage is equal Hell Map run Hell map i odwrotnie
                 */
            }
        });
        Label widthLabel = new Label("Width: ");
        TextField widthText = new TextField();
        widthText.setText("30");
        Label heightLabel = new Label("Height: ");
        TextField heightText = new TextField();
        heightText.setText("30");
        Label amountOfAnimalsLabel = new Label("Amount of animals: ");
        TextField amountOfAnimalsText = new TextField();
        amountOfAnimalsText.setText("10");
        Label startEnergyLabel = new Label("Start energy: ");
        TextField startEnergyText = new TextField();
        startEnergyText.setText("2");
        Label moveEnergyLabel = new Label("Move energy: ");
        TextField moveEnergyText = new TextField();
        moveEnergyText.setText("2");

        //Button to open file
        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File("./gui"));
        Button loadData = new Button("Load data from file");
        loadData.setOnAction(event -> {
            fileChooser.setTitle("Open JSON configuration file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
            File selectedFile = fileChooser.showOpenDialog(actualStage);

            if (selectedFile != null) {
                pathFile = selectedFile.getPath();
                out.println(pathFile); // ok pobiera

                // to jest do poprawy, nie chce dzialac :(

//                JSONParser parser = new JSONParser();
//                try {
//                    //Read JSON file
//                    Object object =  parser.parse(new FileReader(pathFile));
//                    JSONObject jsonObject = (JSONObject) object;
//                    // set new values
//                    int asas = (int) jsonObject.get("width");
//                    out.println(asas);
//
//                    widthText.setText((String) ((JSONObject) object).get("width"));
//                    heightText.setText((String) ((JSONObject) object).get("height"));
//                    amountOfAnimalsText.setText((String) ((JSONObject) object).get("amountOfAnimals"));
//                    startEnergyText.setText((String) ((JSONObject) object).get("startEnergy"));
//                    moveEnergyText.setText((String) ((JSONObject) object).get("moveEnergy"));
//                } catch (ParseException | FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
            } else {
                out.println("Error");
            }
        });




        VBox controls = new VBox(startSimulation, new HBox(widthLabel, widthText), new HBox(heightLabel, heightText),
                new HBox(amountOfAnimalsLabel, amountOfAnimalsText), new HBox(startEnergyLabel, startEnergyText),
                new HBox(moveEnergyLabel, moveEnergyText), loadData);

        return controls;
    }
}

