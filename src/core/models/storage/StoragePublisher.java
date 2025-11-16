
package core.models.storage;
import core.models.Publisher;
import core.models.Stand;
import java.util.ArrayList;

public class StoragePublisher {
    private static StoragePublisher instance;
    ArrayList<Publisher> publishers;

    public StoragePublisher() {
        this.publishers = new ArrayList<>();
    }
    
    public static StoragePublisher getInstance() {
        if (instance == null) {
            instance = new StoragePublisher();
        }
        return instance;
    }
    
    public boolean addPublisher(Publisher publisher){
        for (Publisher publisher1 : this.publishers) {
            if(publisher1.getNit().equals(publisher.getNit())){
                return false;
            }
        }
        this.publishers.add(publisher);
        return true;
    }
    
    public Publisher getPublisher(String nit){
        for (Publisher publisher : this.publishers) {
            if(publisher.getNit().equals(nit)){
                return publisher;
            }
        }
        return null;
    }

    public ArrayList<Publisher> getPublishers() {
        return publishers;
    }
    
}
