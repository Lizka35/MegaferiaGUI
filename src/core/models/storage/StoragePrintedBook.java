
package core.models.storage;

import core.models.PrintedBook;
import java.util.ArrayList;

public class StoragePrintedBook {
    private static StoragePrintedBook instance;
    ArrayList<PrintedBook> printedBooks;

    public StoragePrintedBook() {
        this.printedBooks = new ArrayList<>();
    }
    
    public static StoragePrintedBook getInstance() {
        if (instance == null) {
            instance = new StoragePrintedBook();
        }
        return instance;
    }
    
    public boolean addPrintedBook(PrintedBook printedBook){
        for (PrintedBook prin : this.printedBooks) {
            if(prin.getIsbn().equals(printedBook.getIsbn())){
                return false;
            }
        }
        this.printedBooks.add(printedBook);
        return true;
    }
    
    public PrintedBook getPrintedBook(String isbn){
        for (PrintedBook printedBook : this.printedBooks) {
            if(printedBook.equals(isbn)){
                return printedBook;
            }
        }
        return null;
    }
}
