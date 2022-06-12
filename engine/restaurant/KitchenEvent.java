package engine.restaurant;

import engine.Entity;
import engine.EntitySet;
import engine.Resource;

public class KitchenEvent {

	private EntitySet ordersQueue;
	private EntitySet ordersBeingMade;
	private EntitySet ordersReady;
	private Resource cooks;

	public KitchenEvent(Resource cooks) {
		this.cooks = cooks;
		this.ordersQueue = new EntitySet("FIFO", 100);
		this.ordersBeingMade = new EntitySet("FIFO", 3);
	}

	public void insert(Order order) {
		if (cooks.allocate(1)) {
			ordersBeingMade.insert(order);
		} else {
			ordersQueue.insert(order);
		}
	}

	public void execute(double currentTime, double eatingTime, double timeToCompletion) {
		if (!ordersBeingMade.isEmpty()) {
			for (Entity order : ordersBeingMade.getEntityList()) {
				Order aux = (Order) order;
				if (aux.getTimeToCompletion() == currentTime) {
					ordersReady.insert(ordersBeingMade.removeById(aux.getId()));
					cooks.release(1);
					moveQueue(timeToCompletion);
					deliverOrder(eatingTime);
				}
			}
		}
	}

	private void moveQueue(double timeToCompletion) {
		if (!ordersBeingMade.isFull()) {
			Order c = (Order) ordersQueue.remove();
			if (c != null) {
				c.setTimeToCompletion(timeToCompletion);
				ordersBeingMade.insert(c);
			}
		}
	}

	private void deliverOrder(double eatingTime) {
		if (!ordersReady.isEmpty()) {
			for (Entity order : ordersReady.getEntityList()) {
				Order aux = (Order) order;
				if (aux.getClients().getIsSeated()) {
					aux.getClients().setAreEating(true);
					aux.getClients().setEatingTime(eatingTime);
				}
			}
		}
	}
}
