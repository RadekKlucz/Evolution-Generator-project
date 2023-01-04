package agh.project.GUI;

import javafx.application.Application;
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

public class HellMapWindow extends Application {
    private GuiElement elementCreator = new GuiElement();

    public HellMapWindow() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(elementCreator.buttonsForMaps(), 500, 300);
        primaryStage.setTitle("Hell Map");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
