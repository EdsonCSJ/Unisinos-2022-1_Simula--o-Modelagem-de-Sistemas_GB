package engine.restaurant.events;

import engine.Event;
import engine.Scheduler;
import engine.EntitySet;
import engine.restaurant.entities.Clients;

public class ClientsArrival extends Event {

  public ClientsArrival(int id, Scheduler scheduler) {
    super(id, scheduler);
  }

  @Override
  public void execute() {
    Scheduler s = this.scheduler;
    /* Agenda próxima chegada */
    s.scheduleIn(s.createEvent("ClientsArrival"), s.fakeExponential(3.00));

    Clients clients = new Clients(3, s.getTime());

    /* Agenda início do atendimento */
    if (s.getResource(0).isAvailable()) {
      StartOrder ss = new StartOrder(s.getAndIncrementCurrentEventId(), s, clients, s.getResource(0));
      s.scheduleNow(ss);
    } else if (s.getResource(1).isAvailable()) {
      StartOrder ss = new StartOrder(s.getAndIncrementCurrentEventId(), s, clients, s.getResource(1));
      s.scheduleNow(ss);
    }
    /* Ou insere na menor fila do Caixa */
    else {
      EntitySet cashierQueue = s.getEntitySet(0);
      EntitySet cashierQueue2 = s.getEntitySet(1);
      if (cashierQueue.getSize() < cashierQueue2.getSize()) {
        cashierQueue.insert(clients);
      } else {
        cashierQueue2.insert(clients);
      }
    }
  }
}