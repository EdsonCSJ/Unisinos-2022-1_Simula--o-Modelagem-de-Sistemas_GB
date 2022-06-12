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

  public Double geraRandom() {
    Integer semente = 0;
    Integer auxInt;
    while (semente < 1000) {
        semente = (int) Math.floor(Math.random() * (9999 - 1000 + 1));
    }
    String aux;
    String aux2;

    for (int i = 0; i < 4; i++) {
        auxInt = semente * semente;

        aux = Integer.toString(auxInt);
        aux = aux.substring(3, 7);
        semente = Integer.parseInt(aux);
        if (semente < 1000) {
            aux = Integer.toString(auxInt);
        }
        while (semente < 1000) {
            aux2 = "0" + aux;

            aux = aux2.substring(3, 7);

            semente = Integer.parseInt(aux);

            aux = aux2;

        }

    }

    double d = semente / 9999.0;

    return d;
}

public double normalDist(int media, int desvio) {
    double res = 0;
    double rand1;
    double rand2;
    double w = 2;
    double y = 0;
    double var = 0;
    double vAux1 = 0;
    double vAux2 = 0;

    while (w > 1) {
        rand1 = geraRandom();
        rand2 = geraRandom();

        vAux1 = (2 * rand1) - 1;
        vAux2 = (2 * rand2) - 1;

        w = (Math.pow(vAux1, 2)) + (Math.pow(vAux2, 2));
    }

    y = Math.sqrt((-2 * Math.log(w)) / w);

    var = vAux1 * y;

    res = media + (desvio * var);

    return res;

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
