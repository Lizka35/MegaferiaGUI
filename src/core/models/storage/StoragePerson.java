
package core.models.storage;

import core.models.Author;
import core.models.Person;
import java.util.ArrayList;

public class StoragePerson {
    private static StoragePerson instance;
    ArrayList<Person> persons;

    public StoragePerson() {
        this.persons = new ArrayList<>();
    }
    
    public static StoragePerson getInstance() {
        if (instance == null) {
            instance = new StoragePerson();
        }
        return instance;
    }
    
    public boolean addPerson(Person person){
        for (Person per : this.persons) {
            if(per.getId() == person.getId()){
                return false;
            }
        }
        this.persons.add(person);
        return true;
    }
    
    public Person getPerson(long id){
        for (Person person : this.persons) {
            if(person.getId() == id){
                return person;
            }
        }
        return null;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }
    
}
