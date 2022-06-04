package src;

public class Entity {
    private String name;
    private Integer id; // atribuído pelo Scheduler
    private Double creationTime; // atribuído pelo Scheduler
    private Integer priority; // sem prioridade: -1 (0: + alta e 255: + baixa)
    // private PetriNet PetriNet;

    public Entity(String name) {

    }

    /*
     * public Entity(String name, PetriNet petriNet){
     * 
     * }
     */

    public Integer getId() {
        return this.id;
    }

    public Integer getPriority() {
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

    // getSets():EntitySet List  retorna lista de EntitySets nas quais a entidade
    // está inserida

}
