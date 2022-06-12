package engine.restaurant;

import engine.Entity;

public class Clients extends Entity {

    private int groupSize;
    private boolean isBeingAttended = false;
    private boolean isSeated = false;
    private boolean areEating = false;
    private double attendeTime;
    private double eatingTime;

    public Clients(int id, double creationTime) {
        super(id, creationTime);
        setRandGroupSize();
    }

    public boolean getIsBeingAttended() {
        return this.isBeingAttended;
    }

    public void setIsBeingAttended(boolean isBeingAttended) {
        this.isBeingAttended = isBeingAttended;
    }

    public double getAttendeTime() {
        return this.attendeTime;
    }

    public void setAttendeTime(double attendeTime) {
        this.attendeTime = attendeTime;
    }

    public boolean getAreEating() {
        return this.areEating;
    }

    public void setEatingTime(double eatingTime) {
        this.eatingTime = eatingTime;
    }

    public double getEatingTime() {
        return this.eatingTime;
    }

    public void setAreEating(boolean areEating) {
        this.areEating = areEating;
    }

    public int getGroupSize() {
        return this.groupSize;
    }

    public void setIsSeated(boolean isSeated) {
        this.isSeated = isSeated;
    }

    public boolean getIsSeated() {
        return this.isSeated;
    }

    private void setRandGroupSize() {
        groupSize = (int) Math.floor(Math.random() * (4 - 1 + 1) + 1);
    }
}
