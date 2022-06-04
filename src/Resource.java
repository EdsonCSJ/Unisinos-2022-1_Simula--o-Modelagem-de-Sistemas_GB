package src;

public class Resource {
    private String name;
    private Integer id; // atribuído pelo Scheduler
    private Integer quantity; // quantidade de recursos disponíveis

    public Resource(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public boolean allocate(Integer quantity) {
        // ****** MUDAR */
        return true;
    }

    public void release(Integer quantity) {

    }

    public double allocationRate() {

    }

    public double averageAllocation() {

    }
}
