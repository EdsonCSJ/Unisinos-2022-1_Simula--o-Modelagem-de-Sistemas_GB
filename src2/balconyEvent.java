package src2;

public class balconyEvent {
    
    private double duration;
    private EntitySet balconyQueue;
    private Resource balconySeats;

    public balconyEvent( double duration, Resource balconySeats) {
		this.duration = duration;
		this.balconySeats = balconySeats;
		this.balconyQueue = new EntitySet("FIFO", 100);
	}

    public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getDuration() {
		return this.duration;
	}

	public void insertIntoQueue(Clients client) {
		balconyQueue.insert(client);
	}

	public Entity execute(double currentTime) {
		if (duration > currentTime) {
			if (!balconyQueue.isEmpty()) {
				if (balconySeats.allocate(1)) {
					System.out.println("Clientes sendo atendidos ");
				} else {
					System.out.println("Por favor espere na fila!");
				}
			}
		} else if(currentTime == duration) {
			return balconyQueue.remove();
		}
		return null;
	}
}
