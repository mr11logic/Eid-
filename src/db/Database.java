package db;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int Id = 1;
    private static HashMap<Integer, Validator> validators = new HashMap<>();

    public static void setValidators(HashMap<Integer, Validator> validators) {
        Database.validators = validators;
    }

    public static void add(Entity e) throws InvalidEntityException {
        Validator validator = validators.get(e.getEntityCode());
        if (validator != null) {
            validator.validate(e);
        }
        e.id = Id++;
        entities.add(e.copy());
    }

    public static Entity get(int id) {
        for(Entity e: entities){
            if(e.id == id){
                return e.copy();
            }
        }
        throw new EntityNotFoundException(id);
    }
    public static void delete(int id)  {
        for(Entity e: entities){
            if(e.id == id){
                entities.remove(e);
                return;
            }
        }
        throw new EntityNotFoundException(id);
    }
    public static void update(Entity e) throws InvalidEntityException {
        Validator validator = validators.get(e.getEntityCode());
        if (validator != null) {
            validator.validate(e);
        }
        Entity existing = get(e.id);
        entities.set(entities.indexOf(existing), e.copy());
    }
    public static void registerValidator(int entityCode, Validator validator) {
        if(validators.containsKey(entityCode)){
            throw new IllegalArgumentException("Entity with code " + entityCode + " already exists");
        }
        validators.put(entityCode, validator);

    }
}