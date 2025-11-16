
package core.models.storage;

import core.models.Author;
import java.util.ArrayList;

public class StorageAuthor {
    private static StorageAuthor instance;
    ArrayList<Author> authors;

    public StorageAuthor() {
        this.authors = new ArrayList<>();
    }
    
    public static StorageAuthor getInstance() {
        if (instance == null) {
            instance = new StorageAuthor();
        }
        return instance;
    }
    
    public boolean addAuthor(Author author){
        for (Author au : this.authors) {
            if(au.getId() == author.getId()){
                return false;
            }
        }
        this.authors.add(author);
        return true;
    }
    
    public Author getAuthor(long id){
        for (Author author : this.authors) {
            if(author.getId() == id){
                return author;
            }
        }
        return null;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }
    
}
