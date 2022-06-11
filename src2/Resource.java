package src2;

public class Resource {

    private String name;
    private int id; 
    private int quantity; 
    private int currentQuantity; 

    public Resource(String name, int id, int quantity) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
    }

    public boolean allocate(int quantity) {
        if (this.quantity >= quantity) {
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
