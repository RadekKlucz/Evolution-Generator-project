package agh.project.Engine;

import agh.project.Map.IMapUpdateObserver;

public interface IEngine extends Runnable {
    void run();

    void addObserver(IMapUpdateObserver observer);

    void setMoveDelay(int moveDelay);

    void pause();

    void resume();

}