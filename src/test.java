import src.Entity;
import src.EntitySet;

public class test {
    public static void main(String[] args) {
        Entity a = new Entity("aaa", 1);
        Entity b = new Entity("bbb", 2);
        Entity c = new Entity("ccc", 3);
        Entity d = new Entity("ddd", 4);
        Entity e = new Entity("eee", 5);

        EntitySet ee = new EntitySet("FIFO", 5);

        ee.insert(a);
        ee.insert(b);
        ee.insert(c);
        ee.insert(d);
        ee.insert(e);

        ee.printElements();

        ee.remove();
        ee.remove();
        ee.remove();
        ee.remove();
        ee.remove();
        ee.remove();

        System.out.println("result");

        ee.printElements();
        System.out.println(ee.getSize());
    }
}
