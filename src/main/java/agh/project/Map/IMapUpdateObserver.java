package agh.project.Map;

/**
 * The interface responsible for updating information about the mesh and objects.
 *
 * @author Radosław Kluczewski, Szczepan Polak
 */
public interface IMapUpdateObserver {

    /**
     * Update information about each element on the map and clean the grid after each day.
     */
    void positionChanged();

}
