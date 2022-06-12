package engine;

public class Entity {

    private int id;
    private double creationTime;

    public Entity(int id, double creationTime) {
        this.id = id;
        this.creationTime = creationTime;
    }

    public int getId() {
        return this.id;
    }

    public double getCreationTime() {
        return this.creationTime;
    }

}
