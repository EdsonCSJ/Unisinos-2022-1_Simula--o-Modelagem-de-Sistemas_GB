package engine.restaurant.events;

import engine.Event;
import engine.Scheduler;
import engine.Resource;
import engine.restaurant.entities.Clients;

public class StartOrder extends Event {

  private Clients clients;
  private Resource resource;

  public StartOrder(int id, Scheduler scheduler, Clients clients, Resource resource) {
    super(id, scheduler);
    this.clients = clients;
    this.resource = resource;
  }

  public void execute() {
    /* Agenda a finalização do atendimento */
    Scheduler s = this.scheduler;
    System.out
        .println(s.time + " - " + "Evento " + this.eventId
            + ": Cliente " + clients.getId() + " sendo atendido pelo caixa " + (resource.getId() + 1));
    resource.allocate(1);
    FinishOrder ss = new FinishOrder(s.getAndIncrementCurrentEventId(), s, this.clients, this.resource);
    s.scheduleIn(ss, s.normalDist(8, 2));
  }

}