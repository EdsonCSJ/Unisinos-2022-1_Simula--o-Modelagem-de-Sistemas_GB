package src2;

public class balconyEvent {
    
    private EntitySet balconyQueue;
    private EntitySet inBalcony;
    private Resource balconySeats;

    public balconyEvent(Resource balconySeats) {
		this.balconySeats = balconySeats;
		this.balconyQueue = new EntitySet("FIFO", 100);
		this.inBalcony = new EntitySet("FIFO", 6);
	}

	public void insertIntoQueue(Clients client) {

		balconyQueue.insert(client);
	}

	public void execute() {
        
	}
}
