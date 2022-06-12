package engine.restaurant.events;

import engine.*;
import engine.restaurant.entities.*;

public class FinishCooking extends Event {

  private Order order;
  private Resource resource;

  public FinishCooking(int id, Scheduler scheduler, Order order, Resource resource) {
    super(id, scheduler);
    this.order = order;
    this.resource = resource;
  }

  public void execute() {
    Scheduler s = this.scheduler;
    /* Agenda início da refeição do cliente caso ele já esteja sentado */
    if (this.order.getClients().getIsSeated()) {
      s.scheduleNow(new StartEating(s.getAndIncrementCurrentEventId(), s, this.order.getClients()));
      /* Ou adiciona na fila de pedidos prontos */
    } else {
      EntitySet finishedOrders = s.getEntitySet(6);
      finishedOrders.insert(order);
      System.out.println(s.time + " - " + "Evento " + this.eventId + ": Cliente " + order.getId()
          + " Pedido esperando que o cliente sente");
    }

    resource.release(1);

    EntitySet orderQueue = s.getEntitySet(resource.getId());
    if (!(orderQueue.isEmpty())) {
      Order order = (Order) orderQueue.getFirst();
      orderQueue.remove();
      s.scheduleNow(new StartCooking(s.getAndIncrementCurrentEventId(), s, order, this.resource));
    }
  }

}