package agh.project.Engine;

import agh.project.Map.IMapUpdateObserver;

/**
 * The interface responsible for a well-functioning engine with simulation every day in our world.
 *
 * @author Radosław Kluczewski, Szczepan Polak
 */
public interface IEngine extends Runnable {

    /**
     * Run the simulation engine every day on the selected map.
     */
    void run();

    /**
     * Add a follower on the map to keep track of your day on a daily basis.
     *
     * @param observer Observer added to map for simulation.
     */

    void addObserver(IMapUpdateObserver observer);

    /**
     * Set a delay between each day.
     *
     * @param moveDelay The delay in milliseconds
     */
    void setMoveDelay(int moveDelay);

    /**
     * Stop the engine during the simulation.
     */
    void pause();

    /**
     * Resume the engine during the simulation.
     */
    void resume();

}