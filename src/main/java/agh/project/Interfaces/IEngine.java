package agh.project.Interfaces;

public interface IEngine extends Runnable {
    void run();

    void addObserver(IMapUpdateObserver observer);

    void setMoveDelay(int moveDelay);

    void pause();

    void resume();

}