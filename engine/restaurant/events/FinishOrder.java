package engine.restaurant.events;

import engine.*;
import engine.restaurant.entities.*;

public class FinishOrder extends Event {

  private Clients clients;
  private Resource resource;

  public FinishOrder(int id, Scheduler scheduler, Clients clients, Resource resource) {
    super(id, scheduler);
    this.clients = clients;
    this.resource = resource;
  }

  public void Execute() {
    Scheduler s = this.scheduler;

    /* Agenda sentada */
    

    /* Agenda próximo atendimento caso possua fila */
    EntitySet cashierQueue = s.getEntitySet(resource.getId());
    if (!(cashierQueue.isEmpty())) {
      Clients clients = (Clients) cashierQueue.getFirst();
      s.scheduleNow(new StartOrder(s.getAndIncrementCurrentEventId(), s, clients, this.resource));
      cashierQueue.insert(clients);
    }
  }
}