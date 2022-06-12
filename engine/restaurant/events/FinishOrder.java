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

  public void execute() {
    Scheduler s = this.scheduler;
    /*
     * Agenda evento de envio de clientes à mesa de acordo com o tamanho do grupo de
     * clientes
     */
    sendClientsToTable(s);
    /* Agenda envio do pedido à cozinha */
    sendOrderToKitchen(s);
    /* Agenda próximo atendimento caso possua fila no caixa */
    pickNextAttende(s);
    // Libera o caixa
    resource.release(1);

  }

  private void sendClientsToTable(Scheduler s) {
    Resource table = null;
    if (clients.getGroupSize() == 1)
      table = s.getResource(2);
    else if (clients.getGroupSize() == 2)
      table = s.getResource(3);
    else
      table = s.getResource(4);

    if (table.isAvailable()) {
      Seat ss = new Seat(s.getAndIncrementCurrentEventId(), s, clients, table);
      s.scheduleNow(ss);
    } else {
      EntitySet seatQueue = s.getEntitySet(table.getId());
      seatQueue.insert(this.clients);
    }
  }

  private void pickNextAttende(Scheduler s) {
    EntitySet cashierQueue = s.getEntitySet(resource.getId());
    if (!(cashierQueue.isEmpty())) {
      Clients clients = (Clients) cashierQueue.getFirst();
      cashierQueue.remove();
      s.scheduleNow(new StartOrder(s.getAndIncrementCurrentEventId(), s, clients, this.resource));
    }
  }

  private void sendOrderToKitchen(Scheduler s) {
    Resource cooks = s.getResource(5);
    Order order = new Order(clients.getId(), s.getTime(), clients);
    if (cooks.isAvailable()) {
      StartCooking sc = new StartCooking(s.getAndIncrementCurrentEventId(), s, order, cooks);
      s.scheduleNow(sc);
    } else {
      EntitySet kitchenQueue = s.getEntitySet(cooks.getId());
      kitchenQueue.insert(order);
    }
  }
}