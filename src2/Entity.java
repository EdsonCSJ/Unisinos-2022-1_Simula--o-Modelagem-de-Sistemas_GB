package src2;

public class Entity {

    private int id; 
    private double creationTime; 
    private int priority; 

    public Entity(int id, double creationTime) {
        this.id = id;
        this.creationTime = creationTime;
    }

    public int getId() {
        return this.id;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double getCreationTime() {
        return this.creationTime;
    }

}
