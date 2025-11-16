
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Publisher;

double value, Publisher publisher
public class BookController {
    public static Response createBook(String title, String isbn, String genre, String format, String value, String publisherNIT){
        try {
            double valueDouble;
            
            if (isbn == null || isbn.trim().equals("")) {
                return new Response("ISBN must be not empty", Status.BAD_REQUEST);
            }
            if (!EsIsbnValido(isbn)) {
                return new Response("ISBN must be in format XXX-X-XX-XXXXXX-X", Status.BAD_REQUEST);
            }
            
            if(managerID == null || managerID.isEmpty()){
                return new Response("Manager must be selected", Status.BAD_REQUEST);
            }
            
            if (title.equals("")) {
                return new Response("Title must be not empty", Status.BAD_REQUEST);
            }
            if (isbn.equals("")) {
                return new Response("ISBN must be not empty", Status.BAD_REQUEST);
            }
            if (genre == null || genre.trim().equals("")) {
                return new Response("Genre must be not empty", Status.BAD_REQUEST);
            }
            
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    private static boolean EsIsbnValido(String isbn) {
        String isbnPatron = "^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$";
        return isbn.matches(isbnPatron);
    }
}
