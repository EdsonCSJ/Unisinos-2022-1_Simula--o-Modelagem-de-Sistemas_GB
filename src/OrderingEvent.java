package src;

public class OrderingEvent extends Event {

	private double duration;
	private EntitySet cashierQueue;
	private Resource clerks;
	private boolean isAttending = false;

	public OrderingEvent(String name, double duration, Resource clerks) {
		super(name);
		this.duration = duration;
		this.clerks = clerks;
		this.cashierQueue = new EntitySet("FIFO", 100);
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getDuration() {
		return this.duration;
	}

	public void insertIntoQueue(Clients client) {
		cashierQueue.insert(client);
	}

	public Entity execute(double currentTime) {
		if (duration > currentTime && isAttending == false) {
			if (!cashierQueue.isEmpty()) {
				if (clerks.allocate(1)) {
					System.out.println("Clientes sendo atendidos ");
					isAttending = true;
				} else {
					System.out.println("Por favor espere na fila!");
				}
			}
		} else if(currentTime == duration) {
			isAttending = false;
			return cashierQueue.remove();
		}
		return null;
	}


}
