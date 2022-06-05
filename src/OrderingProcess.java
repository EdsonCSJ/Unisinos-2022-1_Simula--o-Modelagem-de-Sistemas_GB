package src;

public class OrderingProcess extends Process {

	private EntitySet cashierQueue;
	private Resource clerks;

	public OrderingProcess(double duration, Resource clerks, EntitySet cashierQueue) {
		super(duration);
		this.clerks = clerks;
		this.cashierQueue = cashierQueue;
	}

	public EntitySet getcashierQueue() {
		return this.cashierQueue;
	}

	@Override
	public Entity executeOnStart() {
		if (!cashierQueue.isEmpty()) {
			if (clerks.allocate(1)) {
				return cashierQueue.remove();
			}
		}
		return null;
	}
}