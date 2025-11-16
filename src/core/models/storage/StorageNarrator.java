
package core.models.storage;

import core.models.Narrator;
import java.util.ArrayList;

public class StorageNarrator {
    private static StorageNarrator instance;
    ArrayList<Narrator> narrators;

    public StorageNarrator() {
        this.narrators = new ArrayList<>();
    }
    
    public static StorageNarrator getInstance() {
        if (instance == null) {
            instance = new StorageNarrator();
        }
        return instance;
    }
    
    public boolean addNarrator(Narrator narrator){
        for (Narrator narr : this.narrators) {
            if(narr.getId() == narrator.getId()){
                return false;
            }
        }
        this.narrators.add(narrator);
        return true;
    }
    
    public Narrator getNarrator(long id){
        for (Narrator narrator : this.narrators) {
            if(narrator.getId() == id){
                return narrator;
            }
        }
        return null;
    }
}
