package engine.restaurant;

import engine.Event;
import engine.Scheduler;

public class ClientsArrival extends Event {

  public ClientsArrival(int id, Scheduler scheduler) {
    super(id, scheduler);
  }

  public void Execute() {
    /* Agenda próxima chegada */
    this.scheduler.scheduleIn(new ClientsArrival(scheduler.getCurrentEventId(), this.scheduler),
        this.scheduler.fakeExponential(3.00));
    /* Manda para fila do caixa */
    // this.scheduler.scheduleNow(event);
  }
}