package agh.project.Interfaces;

import agh.project.Classes.Vector2d;

import java.awt.*;


public interface IMapElement {
    /*
     * The interface responsible for correctly working with the grass and animal.
     * Assumes that the Vector2d class are defined.
     *
     * @author
     */

    Vector2d getPosition();
    /*
     * Description in progress
     */

    Color getColor();
    /*
     * Description in progress
     */
}
