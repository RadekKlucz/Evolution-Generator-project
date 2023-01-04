package agh.project.GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.awt.*;

public class WelcomeWindow extends Application {
    private GuiElement elementCreator = new GuiElement();

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
                Platform.runLater(() -> {
                    elementCreator.openMapWindow(primaryStage, "Hell Map");
                });
            }

        });

        Button earthMapButton = new Button("Earth Map");
        earthMapButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(() -> {
                    elementCreator.openMapWindow(primaryStage, "Earth Map");
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

}
