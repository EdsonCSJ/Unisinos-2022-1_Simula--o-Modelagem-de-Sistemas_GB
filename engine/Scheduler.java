package engine;

import java.util.*;

import engine.restaurant.events.ClientsArrival;

public class Scheduler {

  public double time;
  public double executionMaxTime = 180;
  public List<TimedEvent> eventList;
  public List<Resource> resourceList;
  public List<EntitySet> entitySetList;
  private int currentEventId = 0;
  private int currentResourceId = 0;
  private int currentEntitySetId = 0;
  private int currentClientId = 0;

  public Scheduler() {
    this.eventList = new ArrayList<>();
    this.resourceList = new ArrayList<>();
    this.entitySetList = new ArrayList<>();
  }

  public double getTime() {
    return this.time;
  }

  public int getAndIncrementCurrentEventId() {
    return this.currentEventId++;
  }

  public int getAndIncrementCurrentClientId() {
    return this.currentClientId++;
  }

  public int getCurrentResourceId() {
    return this.currentResourceId;
  }

  public int getCurrentEntitySetId() {
    return this.currentEntitySetId;
  }

  public int createEntitySet(String mode, int maxPossibleSize) {
    EntitySet entitySet = new EntitySet(mode, maxPossibleSize);
    this.entitySetList.add(entitySet);
    return this.currentEntitySetId++;
  }

  public EntitySet getEntitySet(int entitySetId) {
    return entitySetList.get(entitySetId);
  }

  public int createResource(String name, int quantity) {
    Resource resource = new Resource(name, currentResourceId, quantity);
    this.resourceList.add(resource);
    return this.currentResourceId++;
  }

  public Resource getResource(int resourceId) {
    return resourceList.get(resourceId);
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
    if (name == "ClientsArrival") {
      ClientsArrival ca = new ClientsArrival(getAndIncrementCurrentEventId(), this);
      return ca;
    }
    /* TODO: adicionar os outros eventos */
    return null;
  }

  private void startArrival(String name) {
    scheduleIn(createEvent(name), fakeExponential(3.00));
  }

  private Event getNextEvent() {
    if (this.eventList.isEmpty()) {
      return null;
    }

    TimedEvent nextTimedEvent = new TimedEvent(new Event(), Double.MAX_VALUE);
    for (TimedEvent timedEvent : eventList) {
      if (timedEvent.getExecutionTime() < nextTimedEvent.getExecutionTime()) {
        nextTimedEvent = timedEvent;
      }
    }
    Double nextExecutionTime = nextTimedEvent.getExecutionTime();
    if (nextExecutionTime > this.time) {
      this.time = nextExecutionTime;
    }
    this.eventList.remove(nextTimedEvent);
    return nextTimedEvent.getEvent();
  }

  public void simulate() {

    createResource("Caixa 1", 1); // 0
    createEntitySet("FIFO", 100);
    createResource("Caixa 2", 1); // 1
    createEntitySet("FIFO", 100);
    createResource("Balcão", 6); // 2
    createEntitySet("FIFO", 100);
    createResource("Mesas para 2", 4); // 3
    createEntitySet("FIFO", 100);
    createResource("Mesas para 4", 4); // 4
    createEntitySet("FIFO", 100);
    createResource("Cozinheiros", 3); // 5
    createEntitySet("FIFO", 100);
    createEntitySet("FIFO", 100); // Fila de pedidos prontos: ID 6

    startArrival("ClientsArrival");
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
