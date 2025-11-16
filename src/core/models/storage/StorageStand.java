
package core.models.storage;

import core.models.Stand;
import java.util.ArrayList;

public class StorageStand {
    private static StorageStand instance;
    ArrayList<Stand> stands;

    public StorageStand() {
        this.stands = new ArrayList<>();
    }
    
    public static StorageStand getInstance() {
        if (instance == null) {
            instance = new StorageStand();
        }
        return instance;
    }
    
    public boolean addStand(Stand stand){
        for (Stand stand1 : this.stands) {
            if(stand1.getId() == stand.getId()){
                return false;
            }
        }
        this.stands.add(stand);
        return true;
    }
    
    public Stand getStand(long id){
        for (Stand stand : this.stands) {
            if(stand.getId() == id){
                return stand;
            }
        }
        return null;
    }

    public ArrayList<Stand> getStands() {
        return stands;
    }
    
}
