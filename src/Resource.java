package src;

public class Resource {
    private String name;
    private int id; // atribuído pelo Scheduler
    private int quantity; // quantidade de recursos total
    private int currentQuantity; // quantidade de recursos atual

    public Resource(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public boolean allocate(int quantity) {
        if (this.quantity <= quantity) {
            this.currentQuantity -= quantity;
            return true;
        }
        return false;
    }

    public void release(int quantity) {
        currentQuantity += quantity;
        if (currentQuantity > this.quantity)
            currentQuantity = this.quantity;
    }

}
