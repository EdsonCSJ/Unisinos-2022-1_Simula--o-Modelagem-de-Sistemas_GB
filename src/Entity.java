package src;

public class Entity {
    private String name;
    private int id; // atribuído pelo Scheduler
    private Double creationTime; // atribuído pelo Scheduler
    private int priority; // sem prioridade: -1 (0: + alta e 255: + baixa)
    // private PetriNet PetriNet;

    public Entity(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /*
     * public Entity(String name, PetriNet petriNet){
     * 
     * }
     */

    public int getId() {
        return this.id;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public double getTimeSinceCreation() {
        return this.creationTime;
    }   

    // getSets():EntitySet List  retorna lista de EntitySets nas quais a entidade
    // está inserida


}
