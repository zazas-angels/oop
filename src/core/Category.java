package core;

/**
 * Created by nika on 5/24/15.
 */
public class Category implements CategoryInterface {
    String name;
    int id;

    public Category(int id, String name) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }
}
