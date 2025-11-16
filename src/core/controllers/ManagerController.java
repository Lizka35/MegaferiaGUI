
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Manager;
import core.models.storage.StorageManager;

public class ManagerController {
    public static Response createManager(String id, String firstname, String lastname){
        try {
            long idLong;
            try {
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Id must be not negative", Status.BAD_REQUEST);
                }
                if (id.length() > 15) {
                    return new Response("Id must have less than 15 digits", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException e) {
                if (id.equals("")) {
                    return new Response("Id must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            StorageManager storageManager = StorageManager.getInstance();
            if(!storageManager.addManager(new Manager(idLong, firstname, lastname) )){
                return new Response("Manager with that id already exits", Status.BAD_REQUEST);
            }
            return new Response("Manager was created succesfully", Status.CREATED);
            
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
