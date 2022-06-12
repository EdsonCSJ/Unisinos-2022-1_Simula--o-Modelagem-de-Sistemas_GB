package engine.restaurant.entities;

import engine.Entity;

public class Clients extends Entity {

  private boolean isBeingAttended = false;

  public Clients(int id, double creationTime) {
    super(id, creationTime);
  }

  public boolean getIsBeingAttended() {
    return this.isBeingAttended;
  }

  public void setIsBeingAttended(boolean isBeingAttended) {
    this.isBeingAttended = isBeingAttended;
  }

}
