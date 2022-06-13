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
    if (s.getTime() < 180) {
      /* Agenda próxima chegada */
      s.scheduleIn(s.createEvent("ClientsArrival"), s.getNumberGenerators().exponencial(3));

      Clients clients = new Clients(s.getAndIncrementCurrentClientId(), s.getTime());

      System.out.printf("%.2f - " + "Evento " + this.eventId + ": Chegada do cliente: " + clients.getId() + "\n",
          s.time);
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
          System.out
              .printf("%.2f - " + "Evento " + this.eventId
                  + ": Cliente " + clients.getId() + " esperando na fila do Caixa 1\n", s.time);
        } else {
          System.out
              .printf("%.2f - " + "Evento " + this.eventId
                  + ": Cliente " + clients.getId() + " esperando na fila do Caixa 2\n", s.time);
          cashierQueue2.insert(clients);

        }
      }
    }
  }
}