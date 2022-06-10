package src;

public class balconyEvent extends Event {

    private double duration;
    private EntitySet balconyQueue;
    private Resource balconySeats;
    private boolean isAttending = false;

    public balconyEvent(String name, double duration, Resource balconySeats) {
        super(name);
        this.duration = duration;
        this.balconySeats = balconySeats;
    }

    public EntitySet getBalconyQueue() {
        return this.balconyQueue;
    }

    public Entity execute(double currentTime) {
        
    }

}