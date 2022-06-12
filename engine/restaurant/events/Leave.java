package engine.restaurant.events;

import engine.*;
import engine.restaurant.entities.*;

public class Leave extends Event {

  private Resource resource;

  public Leave(int id, Scheduler scheduler, Resource resource) {
    super(id, scheduler);
    this.resource = resource;
  }

  public void execute() {
    Scheduler s = this.scheduler;
    // Cliente senta
    System.out.println(s.time + " - " + this.eventId + ": Cliente deixou o restaurante");
    resource.release(1);
    EntitySet tableQueue = s.getEntitySet(resource.getId());
    if (!(tableQueue.isEmpty())) {
      Clients clients = (Clients) tableQueue.getFirst();
      tableQueue.remove();
      s.scheduleNow(new Seat(s.getAndIncrementCurrentEventId(), s, clients, this.resource));
    }

  }
}
