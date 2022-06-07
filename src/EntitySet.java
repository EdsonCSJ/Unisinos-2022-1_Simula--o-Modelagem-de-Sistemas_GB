package src;

import java.util.*;

public class EntitySet {

    private int id;
    private String mode; /*
                          * mode: FIFO/LIFO/Priority based/None  no mode None, método remove() sorteia
                          * qual entidade será
                          * removida; neste mode, é + interessante empregar removeById(id)
                          */
    private int size;
    private int maxPossibleSize; // tamanho máximo que o conjunto pode ter
    private List<Entity> entityList = new ArrayList<>();

    public EntitySet(String mode, int maxPossibleSize) {
        this.mode = mode;
        this.maxPossibleSize = maxPossibleSize;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void insert(Entity entity) { // similar a enqueue ou push
        if (size < maxPossibleSize) {
            entityList.add(entity);
            this.size++;
        } else {
            System.out.println("Fila Cheia!");
        }
    }

    public Entity remove() { // similar a dequeue ou pop...
        this.size--;
        return entityList.remove(0);
    }

    public Entity removeById(int id) {
        for (Entity entity : entityList) {
            if (entity.getId() == id) {
                this.size--;
                Entity aux = entity;
                entityList.remove(entity);
                return aux;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return entityList.isEmpty();
    }

    public boolean isFull() {
        return (size == maxPossibleSize) ? true : false;
    }

    public Entity findEntity(int id) {
        for (Entity entity : entityList) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        return null;
    }

    public int getSize() {
        return this.size;
    }

    public int getMaxPossibleSize() {
        return this.maxPossibleSize;
    }



}