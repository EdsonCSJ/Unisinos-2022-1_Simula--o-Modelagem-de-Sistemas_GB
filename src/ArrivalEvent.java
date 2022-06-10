package src;

public class ArrivalEvent extends Event {

    private double timeToArrival;
    private EntitySet arrivalOrderQueues[] = new EntitySet[2];

    public ArrivalEvent(String name, String mode, double timeToArrival) {
        super(name);
        this.timeToArrival = timeToArrival;
        arrivalOrderQueues[0] = new EntitySet(mode, 100);
        arrivalOrderQueues[1] = new EntitySet(mode, 100);
    }

    public double getTimeToArrival() {
        return this.timeToArrival;
    }

    public void setTimeToArrival(double timeToArrival) {
        this.timeToArrival = timeToArrival;
    }

    public void clientsArrival(Clients clients, double currentTime) {
        if (timeToArrival == currentTime)
            if (arrivalOrderQueues[0].getSize() < arrivalOrderQueues[1].getSize()) {
                arrivalOrderQueues[0].insert(clients);
            } else {
                arrivalOrderQueues[1].insert(clients);
            }
    }

    public Entity moveToCashierQueue() {
        if(arrivalOrderQueues[0].getFirst().getCreationTime() < arrivalOrderQueues[1].getFirst().getCreationTime())
            return arrivalOrderQueues[0].remove();
        return arrivalOrderQueues[1].remove();
    }

    public EntitySet[] getQueues() {
        return arrivalOrderQueues;
    }
}
