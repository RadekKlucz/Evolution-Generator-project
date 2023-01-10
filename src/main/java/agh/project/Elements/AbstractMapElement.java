package agh.project.Elements;

public abstract class AbstractMapElement extends DataReader implements IMapElement {
    protected Vector2d position;
    protected int energy;

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }
}
