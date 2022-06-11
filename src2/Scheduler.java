package src2;

public class Scheduler {

    public double time;

    public Scheduler() {
    };

    public double getTime() {
        return this.time;
    }

    public void simulate() {
        Resource clerks = new Resource("Clerks", 1, 2);
        OrderingEvent oe = new OrderingEvent("FIFO", clerks);
        oe.setTimeToArrival(time + 5);
        while (time < 30) {
            if (oe.clientsArrival(1, time))
                oe.setTimeToArrival(time + 5);
            if (!oe.getIsAttending())
                oe.setDuration(time + 8);
            oe.atendClient();
            oe.sendToTable(time);
            time++;
        }
    }
}
