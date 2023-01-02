package agh.project.GUI;

import agh.project.Classes.Animal;
import agh.project.Interfaces.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

    //    @Override
//    public Color getColor() {
//        if(energy == 0) {
//            return new Color(0x8C040A); // very dark red
//        } else if (energy <= startEnergy * 0.3) {
//            return new Color(0xFF0008); // red
//        } else if ((energy <= startEnergy * 0.5) && (energy > startEnergy * 0.3)) {
//            return new Color(0xFD6801); // orange
//        } else if ((energy <= startEnergy *0.7) && (energy > startEnergy * 0.5)) {
//            return new Color(0xE59E6C); // light orange
//        } else if ((energy <= startEnergy) && (energy > startEnergy * 0.7)) {
//            return new Color(0xC1C94F); // gold
//        } else if (energy > startEnergy) {
//            return new Color(0x3F1010); // brown
//        }
//        return new Color(0x000000); // dark
//    }
}
