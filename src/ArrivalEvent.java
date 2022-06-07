package src;

public class ArrivalEvent extends Event {
    
    private double timeToArrival;
    private EntitySet cashierQueue[] = new EntitySet[2];

    public ArrivalEvent(String name, double timeToArrival) {
        super(name);
        this.timeToArrival = timeToArrival;
        cashierQueue[0] = new EntitySet("FIFO", 100);
        cashierQueue[1] = new EntitySet("FIFO", 100);
    }

    public double timeToArrival() {
        return this.timeToArrival;
    }

    public void timeToArrival(double timeToArrival) {
        this.timeToArrival = timeToArrival;
    }

    public void clientsArrival(Clients clients) {
        if(cashierQueue[0].getSize() < cashierQueue[1].getSize()) {
            cashierQueue[0].insert(clients);
        } else {
            cashierQueue[1].insert(clients);
        }
    }

    public EntitySet[] getQueues() {
        return cashierQueue;
    }
}
