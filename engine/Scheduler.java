package engine;

import java.util.List;

import engine.restaurant.OrderingEvent;
import engine.restaurant.SeatingEvent;
import engine.restaurant.Clients;
import engine.restaurant.KitchenEvent;
import engine.restaurant.Order;
import engine.restaurant.ClientsArrival;

public class Scheduler {

  public double time;
  public double executionMaxTime = 180;
  public List<TimedEvent> eventList;

  public double getTime() {
    return this.time;
  }

  public double normalDist(int media, int desvio) {
    double res = 0;
    double rand1 = 0;
    double rand2 = 0;
    double w = 2;
    double y = 0;
    double var = 0;
    double vAux1 = 0;
    double vAux2 = 0;

    while (w > 1) {

      vAux1 = (2 * rand1) - 1;
      vAux2 = (2 * rand2) - 1;

      w = (Math.pow(vAux1, 2)) + (Math.pow(vAux2, 2));
    }

    y = Math.sqrt((-2 * Math.log(w)) / w);

    var = vAux1 * y;

    res = media + (desvio * var);

    return res;

  }

  public void simulate() {
    Resource clerks = new Resource("Clerks", 1, 2);
    OrderingEvent oe = new OrderingEvent("FIFO", clerks);
    Resource balconySeats = new Resource("Balcony Seats", 1, 6);
    Resource tableSeats1 = new Resource("Balcony Seats", 2, 4);
    Resource tableSeats2 = new Resource("Balcony Seats", 3, 4);
    SeatingEvent balcony = new SeatingEvent(balconySeats);
    SeatingEvent tablesForTwo = new SeatingEvent(tableSeats1);
    SeatingEvent tablesForFour = new SeatingEvent(tableSeats2);
    Resource cooks = new Resource("cooks", 4, 3);
    KitchenEvent kitchen = new KitchenEvent(cooks);

    oe.setTimeToArrival(time + 5);

    while (time < 180) {
      if (oe.clientsArrival(1, time))
        oe.setTimeToArrival(time + 3);
      if (!oe.getIsAttending())
        oe.setDuration(time + 8);
      oe.atendClient();
      Clients c = (Clients) oe.sendToTable(time);
      if (c != null) {
        Order newOrder = new Order(c.getId(), time, c);
        kitchen.insert(newOrder);
        int groupSize = c.getGroupSize();
        if (groupSize == 1) {
          System.out.println("inserido no balcão");
          balcony.insert(c);
        } else if (groupSize == 2) {
          System.out.println("inserido nas mesas de 2");
          tablesForTwo.insert(c);
        } else {
          System.out.println("inserido nas mesas de 4");
          tablesForFour.insert(c);
        }
      }
      kitchen.execute(time, time + 20, time + 14);
      balcony.execute(time);
      tablesForTwo.execute(time);
      tablesForFour.execute(time);
      time++;
    }
  }

  private int currentEventId = 0;

  public int getCurrentEventId() {
    return this.currentEventId;
  }

  public void scheduleNow(Event event) {
    this.eventList.add(new TimedEvent(event, time));
  }

  public void scheduleAt(Event event, Double absoluteTime) {
    this.eventList.add(new TimedEvent(event, absoluteTime));
  }

  public void scheduleIn(Event event, Double timeToEvent) {
    this.eventList.add(new TimedEvent(event, time + timeToEvent));
  }

  public Event createEvent(String name) {
    if (name == "ClientArrival") {
      ClientsArrival ca = new ClientsArrival(currentEventId, this);
      return ca;
    }
    return null;
  }

  private void startArrival(String name) {
    scheduleIn(createEvent(name), fakeExponential(3.00));
  }

  private Event getNextEvent() {
    if (this.eventList.isEmpty()) {
      return null;
    }

    Event nextEvent = new Event();
    Double nextEventExecutionTime = Double.MAX_VALUE;
    for (TimedEvent timedEvent : eventList) {
      if (timedEvent.getExecutionTime() < nextEventExecutionTime) {
        nextEvent = timedEvent.getEvent();
        nextEventExecutionTime = timedEvent.getExecutionTime();
      }
    }

    if (nextEventExecutionTime > this.time) {
      this.time = nextEventExecutionTime;
    }

    return nextEvent;
  }

  public void simulate2() {
    startArrival("ClientArrival");
    while (this.time < this.executionMaxTime) {
      Event event = getNextEvent();
      if (event != null) {
        event.execute();
      } else {
        System.out.println(this.time + ": Execução finalizada!");
      }
    }
  }

  public Double fakeExponential(Double meanValue) {
    return meanValue;
  }
}
