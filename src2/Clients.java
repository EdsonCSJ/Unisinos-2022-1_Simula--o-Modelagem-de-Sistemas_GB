package src2;

public class Clients extends Entity{

    private int groupSize;
    private boolean areEating;
    
    public Clients(int id, double creationTime) {
        super(id, creationTime);
        setRandGroupSize();
    }

    public boolean getAreEating() {
        return areEating;
    }

    public void getAreEating(boolean areEating) {
        this.areEating = areEating;
    }

    public int getGroupSize() {
        return this.groupSize;
    }

    private void setRandGroupSize() {
        groupSize = (int)Math.floor(Math.random()*(4-1+1)+1);
    }
}