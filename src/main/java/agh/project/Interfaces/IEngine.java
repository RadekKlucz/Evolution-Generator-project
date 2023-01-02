package agh.project.Interfaces;

import agh.project.EnumClasses.MoveDirection;

import java.util.List;

public interface IEngine extends Runnable {
    void run();

    void addObserver(IMapUpdateObserver observer);

    void setMoveDelay(int moveDelay);

//    void setDirections(List<MoveDirection> directions);
}
