package engine.restaurant;

import engine.Entity;
import engine.EntitySet;
import engine.Resource;

public class OrderingEvent {

	private double timeToArrival;
	private double duration;
	private EntitySet cashierQueues[] = new EntitySet[2];
	private Resource clerks;
	private boolean isAttending = false;

	public OrderingEvent(String mode, Resource clerks) {
		this.clerks = clerks;
		cashierQueues[0] = new EntitySet(mode, 100);
		cashierQueues[1] = new EntitySet(mode, 100);
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getDuration() {
		return this.duration;
	}

	public void setTimeToArrival(double timeToArrival) {
		this.timeToArrival = timeToArrival;
	}

	public double getTimeToArrivaln() {
		return this.timeToArrival;
	}

	public boolean getIsAttending() {
		return this.isAttending;
	}

	public boolean clientsArrival(int clientId, double currentTime) {
		if (timeToArrival == currentTime) {
			Clients clients = new Clients(clientId, currentTime);
			if (cashierQueues[0].getSize() < cashierQueues[1].getSize()) {
				cashierQueues[0].insert(clients);
			} else {
				cashierQueues[1].insert(clients);
			}
			return true;
		}
		return false;
	}

	public Entity sendToTable(double currentTime) {
		if (this.isAttending)
			if (currentTime == duration) {
				this.isAttending = false;
				if (cashierQueues[0].getFirst().getCreationTime() < cashierQueues[1].getFirst().getCreationTime())
					return cashierQueues[0].remove();
				return cashierQueues[1].remove();
			}
		return null;
	}

	public void atendClient() {
		if ((!cashierQueues[0].isEmpty() || !cashierQueues[1].isEmpty()) && !isAttending) {
			if (clerks.allocate(1)) {
				System.out.println("Clientes sendo atendidos ");
				this.isAttending = true;
			}
		} else {
			System.out.println("Por favor espere na fila!");
		}
	}

}
