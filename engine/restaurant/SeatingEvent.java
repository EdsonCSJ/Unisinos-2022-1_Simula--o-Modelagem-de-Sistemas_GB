package engine.restaurant;

import engine.Entity;
import engine.EntitySet;
import engine.Resource;

public class SeatingEvent {
    
    private EntitySet seatingQueue;
    private EntitySet seatingClients;
    private Resource seatsAvailable;

    public SeatingEvent(Resource seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
		this.seatingQueue = new EntitySet("FIFO", 100);
		this.seatingClients = new EntitySet("FIFO", 6);
	}

	public void insert(Clients client) {
		if(seatsAvailable.allocate(1)) {
			client.setIsSeated(true);
			seatingClients.insert(client);
		} else {
			seatingQueue.insert(client);
		}
	}

	public void execute(double currentTime) {
		if(!seatingClients.isEmpty()) {
			for (Entity clients : seatingClients.getEntityList()) {
				Clients aux = (Clients)clients;
				if(aux.getAreEating() && aux.getEatingTime() == currentTime){
					seatingClients.removeById(aux.getId());
					seatsAvailable.release(1);
					moveQueue();
				}
			}
		}
	}

	private void moveQueue() {
		if(!seatingClients.isFull()){
			Clients c = (Clients) seatingQueue.remove();
			if(c != null) {
				c.setIsSeated(true);
				seatingClients.insert(c);
			}
		}
	}

}
