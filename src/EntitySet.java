package src;

public class EntitySet {

    private String name;
    private int id;
    private String mode; /*
                          * mode: FIFO/LIFO/Priority based/None  no mode None, método remove() sorteia
                          * qual entidade será
                          * removida; neste mode, é + interessante empregar removeById(id)
                          */
    private int size;
    private int maxPossibleSize; // tamanho máximo que o conjunto pode ter
    private List<Entity> entityList = new ArrayList<>();

    public EntitySet(String name, String mode, int maxPossibleSize) {
        this.name = name;
        this.mode = mode;
        this.maxPossibleSize = maxPossibleSize;
        this.entityList = new Entity[maxPossibleSize];
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void insert(Entity entity) { // similar a enqueue ou push

    }

    public Entity remove() { // similar a dequeue ou pop...

    }

    public Entity removeById(int id) {

    }

    public boolean isEmpty() {

    }

    public boolean isFull() {

    }

    public Entity findEntity(int id) {

    }

}