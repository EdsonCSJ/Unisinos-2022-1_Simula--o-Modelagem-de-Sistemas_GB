package src;

import java.util.*;

public class Scheduler {
    
    private double time;
    private List<Event> eventList;

    public double getTime() {
        return this.time;
    }

    public void scheduleNow(Event e) {
        e.execute();
    }

    public void scheduleIn(Event e, double timeToEvent) {
        
    }

    public void createEntity(Entity entity){

    }

}
