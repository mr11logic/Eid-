package db;
import db.exception.EntityNotFoundException;
import java.util.ArrayList;

public class Database {
    private static final ArrayList<Entity> entities = new ArrayList<>();
    private static int nextId = 1;

    private Database() {}

    public static void add(Entity e) {
        Entity copy = e.copy();
        copy.id = nextId;
        e.id = nextId++;
        entities.add(copy);
    }

    public static Entity get(int id) throws EntityNotFoundException {
        for (Entity e : entities) {
            if (e.id == id) {
                return e.copy();
            }
        }
        throw new EntityNotFoundException(id);
    }

    public static void delete(int id) throws EntityNotFoundException {
        boolean removed = entities.removeIf(e -> e.id == id);
        if (!removed) {
            throw new EntityNotFoundException(id);
        }
    }

public static void update(Entity e) throws EntityNotFoundException {
    delete(e.id);
    Entity updatedCopy = e.copy();
    entities.add(updatedCopy);
    }
}