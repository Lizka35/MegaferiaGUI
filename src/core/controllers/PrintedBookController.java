
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Author;
import core.models.PrintedBook;
import core.models.Publisher;
import core.models.storage.StorageAuthor;
import core.models.storage.StoragePrintedBook;
import core.models.storage.StoragePublisher;
import java.util.ArrayList;

public class PrintedBookController {
    public static Response createBook(String title, String authorsId, String isbn, String genre, String format, 
            String value, String publisherNIT, String pages, String copies){
        try {
            double valueDouble;
            if (title.equals("")) {
                return new Response("Title must be not empty", Status.BAD_REQUEST);
            }
            
            if(authorsId == null || authorsId.isEmpty()){
                return new Response("At least one author must be selected", Status.BAD_REQUEST);
            }
            
            if (isbn == null || isbn.trim().equals("")) {
                return new Response("ISBN must be not empty", Status.BAD_REQUEST);
            }
            if (!EsIsbnValido(isbn)) {
                return new Response("ISBN must be in format XXX-X-XX-XXXXXX-X", Status.BAD_REQUEST);
            }
            
            if (genre == null || genre.trim().equals("")) {
                return new Response("Genre must be not empty", Status.BAD_REQUEST);
            }
            
            if (format == null || format.trim().equals("")) {
                return new Response("Format must be selected", Status.BAD_REQUEST);
            }
            
            try {
                valueDouble = Double.parseDouble(value);
                if(valueDouble < 0){
                    return new Response("Value must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (value.equals("")) {
                    return new Response("Value must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Value must be numeric", Status.BAD_REQUEST);
            }
            if(publisherNIT == null || publisherNIT.isEmpty()){
                return new Response("Publisher must be selected", Status.BAD_REQUEST);
            }
            
            int pagesInt;
            try {
                pagesInt = Integer.parseInt(pages);
                if(pagesInt < 0){
                    return new Response("Pages must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (pages.equals("")) {
                    return new Response("Pages must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Pages must be numeric", Status.BAD_REQUEST);
            }
            
            int copiesInt;
            try {
                copiesInt = Integer.parseInt(copies);
                if(copiesInt < 0){
                    return new Response("Copies must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (copies.equals("")) {
                    return new Response("Copies must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Copies must be numeric", Status.BAD_REQUEST);
            }
            
            StorageAuthor storageAuthor = StorageAuthor.getInstance();
            String[] authorsIds = authorsId.split(",");
            ArrayList<Author> authors = new ArrayList<>();

            for (String idStr : authorsIds) {
                long id = Long.parseLong(idStr.trim());
                Author author = storageAuthor.getAuthor(id);
                if (author == null) {
                    return new Response("Author with ID " + id + " does not exist", Status.BAD_REQUEST);
                }
                authors.add(author);
            }
            
            StoragePublisher storagePublisher = StoragePublisher.getInstance();
            Publisher publisher = storagePublisher.getPublisher(publisherNIT);
            
            StoragePrintedBook storagePrint = StoragePrintedBook.getInstance();
            if(!storagePrint.addPrintedBook(new PrintedBook(title, authors, isbn, genre, format, valueDouble, publisher, pagesInt, copiesInt))){
                return new Response("Printed Book with that isbn already exits", Status.BAD_REQUEST);
            }
            return new Response("Printed Book was created successfully", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    private static boolean EsIsbnValido(String isbn) {
        String isbnPatron = "^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$";
        return isbn.matches(isbnPatron);
    }
}
