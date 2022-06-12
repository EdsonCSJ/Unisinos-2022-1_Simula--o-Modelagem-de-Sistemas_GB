package engine.restaurant.events;

import engine.Event;
import engine.Scheduler;
import engine.Resource;
import engine.restaurant.entities.*;

public class StartCooking extends Event {

  private Order order;
  private Resource resource;

  public StartCooking(int id, Scheduler scheduler, Order order, Resource resource) {
    super(id, scheduler);
    this.order = order;
    this.resource = resource;
  }

  public void execute() {
    Scheduler s = this.scheduler;
    /* Agenda a finalização da preparação do pedido */
    resource.allocate(1);
    FinishCooking fc = new FinishCooking(s.getAndIncrementCurrentEventId(), s, this.order, this.resource);
    s.scheduleIn(fc, s.fakeExponential(14.00));
  }

}