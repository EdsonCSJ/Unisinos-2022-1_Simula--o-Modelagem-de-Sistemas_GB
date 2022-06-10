package src;

import java.util.*;

import src.Entity;

public class EntitySet {

    private int id;
    private String mode; /*
                          * mode: FIFO/LIFO/Priority based/None  no mode None, método remove() sorteia
                          * qual entidade será
                          * removida; neste mode, é + interessante empregar removeById(id)
                          */
    private int size;
    private int maxPossibleSize; // tamanho máximo que o conjunto pode ter
    private List<Entity> entityList;

    public EntitySet(String mode, int maxPossibleSize) {
        this.mode = mode;
        this.maxPossibleSize = maxPossibleSize;
        entityList = new ArrayList<>();
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
        if (isEmpty() == true) {
            System.out.println("Estrutura vazia!");
            return null;
        } else {
            Entity aux;
            if (mode == "LIFO") { // Remove sempre o ultimo registro
                aux = entityList.get(this.size - 1);
                entityList.remove(this.size - 1);

            } else if (mode == "FIFO") { // Remove sempre o primeiro registro e reorganiza a lista
                aux = entityList.get(0);
                entityList.remove(0);
                List<Entity> auxList = new ArrayList<>(); // Lista aux para reordenar lista nova
                if (this.size > 1) {
                    for (int i = 1; i < this.size; i++) {
                        auxList.add(entityList.get(i - 1));
                    }
                    entityList = auxList;
                }
            } else { // mode None: sorteia um ID existente e chama o removeById
                int random = (int) (Math.random() * this.size);
                aux = removeById(random);
            }
            this.size--;
            return aux;
        }
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

    public Entity getFirst() {
        return entityList.get(0);
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

    public void printElements() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(entityList.get(i).getName());
        }
    }

}