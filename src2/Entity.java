package src2;

public class Entity {

    private int id; // atribuído pelo Scheduler
    private double creationTime; // atribuído pelo Scheduler
    private int priority; // sem prioridade: -1 (0: + alta e 255: + baixa)
    // private PetriNet PetriNet;

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
