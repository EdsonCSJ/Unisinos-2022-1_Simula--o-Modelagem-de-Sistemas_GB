// package engine.restaurant;

// import engine.Entity;
// import engine.EntitySet;
// import engine.Resource;

// public class OrderingEvent {

// 	private double timeToArrival;
// 	private EntitySet cashierQueues[] = new EntitySet[2];
// 	private Clients cashierOneFirstClient;
// 	private Clients cashierTwoFirstClient;
// 	private Resource clerks;


// 	public OrderingEvent(String mode, Resource clerks) {
// 		this.clerks = clerks;
// 		cashierQueues[0] = new EntitySet(mode, 100);
// 		cashierQueues[1] = new EntitySet(mode, 100);
// 	}

// 	public void setTimeToArrival(double timeToArrival) {
// 		this.timeToArrival = timeToArrival;
// 	}

// 	public double getTimeToArrival() {
// 		return this.timeToArrival;
// 	}

// 	public boolean clientsArrival(int clientId, double currentTime) {
// 		if (timeToArrival == currentTime) {
// 			Clients clients = new Clients(clientId, currentTime);
// 			if (cashierQueues[0].getSize() < cashierQueues[1].getSize()) {
// 				cashierQueues[0].insert(clients);
// 			} else {
// 				cashierQueues[1].insert(clients);
// 			}
// 			return true;
// 		}
// 		return false;
// 	}

// 	public Entity sendToTable(double currentTime) {
// 		if (currentTime == duration) {
// 			if (cashierQueues[0].getFirst().getCreationTime() < cashierQueues[1].getFirst().getCreationTime())
// 				return cashierQueues[0].remove();
// 			return cashierQueues[1].remove();
// 		}
// 		return null;
// 	}

// 	public void atendClient() {
// 		if (cashierOneFirstClient != null || cashierTwoFirstClient != null) {
// 			moveQueue();
// 			if (clerks.allocate(1)) {
// 				System.out.println("Clientes sendo atendidos ");
// 			}
// 		} else {
// 			System.out.println("Por favor espere na fila!");
// 		}
// 	}

// 	private Clients moveQueue() {

// 	}

// }
