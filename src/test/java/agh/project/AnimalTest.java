package agh.project;

import agh.project.Classes.Animal;
import agh.project.Classes.EarthMap;
import agh.project.Classes.Vector2d;
import agh.project.Interfaces.IWorldMap;
import org.junit.jupiter.api.Test;

public class AnimalTest {

    @Test
    void checkMove(){
        IWorldMap map = new EarthMap(10,15);
        Vector2d vector = new Vector2d(2,2);
        int energy = 20;
        Animal animal = new Animal(map, vector, energy);
        animal.getGenes().g
    }

}
