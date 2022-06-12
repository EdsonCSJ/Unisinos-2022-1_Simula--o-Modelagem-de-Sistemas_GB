package engine.restaurant;

import engine.Entity;

public class Order extends Entity{
    
    private double timeToCompletion;
    private Clients clients;

    public Order(int id, double creationTime, Clients clients) {
        super(id, creationTime);
        this.clients = clients;
    }

    public Clients getClients() {
        return this.clients;
    }

    public void setTimeToCompletion(double timeToCompletion) {
        this.timeToCompletion = timeToCompletion;
    }

    public double getTimeToCompletion() {
        return this.timeToCompletion;
    }


}
