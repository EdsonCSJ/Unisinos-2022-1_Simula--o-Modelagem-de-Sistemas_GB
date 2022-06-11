package src2;

public class Scheduler {

    public double time;

    public Scheduler() {
    };

    public double getTime() {
        return this.time;
    }

    public double normalDist(int media, int desvio) {
        double res = 0;
        double rand1 = 0;
        double rand2 = 0;
        double w = 2;
        double y = 0;
        double var = 0;
        double vAux1 = 0;
        double vAux2 = 0;

        while (w > 1) {

            vAux1 = (2 * rand1) - 1;
            vAux2 = (2 * rand2) - 1;

            w = (Math.pow(vAux1, 2)) + (Math.pow(vAux2, 2));
        }

        y = Math.sqrt((-2 * Math.log(w)) / w);

        var = vAux1 * y;

        res = media + (desvio * var);

        return res;

    }

    public void simulate() {
        Resource clerks = new Resource("Clerks", 1, 2);
        OrderingEvent oe = new OrderingEvent("FIFO", clerks);
        Resource balconySeats = new Resource("Balcony Seats", 2, 6);
        balconyEvent be = new balconyEvent(balconySeats);
        oe.setTimeToArrival(time + 5);

        while (time < 30) {
            if (oe.clientsArrival(1, time))
                oe.setTimeToArrival(time + 5);
            if (!oe.getIsAttending())
                oe.setDuration(time + 8);
            oe.atendClient();
            Clients c = (Clients) oe.sendToTable(time);
            if(c != null)
                if (c.getGroupSize() == 1) {
                    be.insertIntoQueue(c);
                } else {
                    
                }
            time++;
        }
    }
}
