package src;

public class OrderingProcess extends Event {

	private double duration;
	private EntitySet cashierQueue;
	private Resource clerks;

	public OrderingProcess(String name, double duration, Resource clerks, EntitySet cashierQueue) {
		super(name);
		this.duration = duration;
		this.clerks = clerks;
		this.cashierQueue = cashierQueue;
	}

	public EntitySet getcashierQueue() {
		return this.cashierQueue;
	}

	public Entity execute() {
		if (!cashierQueue.isEmpty()) {
			if (clerks.allocate(1)) {
				return cashierQueue.remove();
			}
		}
		return null;
	}
}
