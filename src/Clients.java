package src;

public class Clients extends Entity{

    private int groupSize;
    
    public Clients(String name, int id) {
        super(name, id);
        setRandGroupSize();
    }

    public int getGroupSize() {
        return this.groupSize;
    }

    private void setRandGroupSize() {
        groupSize = (int)Math.floor(Math.random()*(4-1+1)+1);
    }
}
