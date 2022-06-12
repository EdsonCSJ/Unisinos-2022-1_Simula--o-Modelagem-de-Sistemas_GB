package engine.restaurant.events;

import engine.*;
import engine.restaurant.entities.*;

public class StartEating extends Event {

    private Clients clients;

    public StartEating(int id, Scheduler scheduler, Clients clients) {
        super(id, scheduler);
        this.clients = clients;
    }

    public void execute() {
        Scheduler s = this.scheduler;
        Resource table = null;
        if (clients.getGroupSize() == 1)
            table = s.getResource(2);
        else if (clients.getGroupSize() == 2)
            table = s.getResource(3);
        else
            table = s.getResource(4);
        Leave l = new Leave(s.getAndIncrementCurrentEventId(), s, table);
        s.scheduleIn(l, s.fakeExponential(20.00));
    }
}
