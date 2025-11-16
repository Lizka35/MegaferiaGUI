
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Manager;
import core.models.Publisher;
import core.models.storage.StorageManager;
import core.models.storage.StoragePublisher;

public class PublisherController {
    public static Response createPublisher(String nit, String name, String address, String managerID){
        try {
            if (nit == null || nit.trim().equals("")) {
                return new Response("NIT must be not empty", Status.BAD_REQUEST);
            }
            // Validar formato del NIT (XXX.XXX.XXX-X)
            if (!NITesValido(nit)) {
                return new Response("NIT must be in format XXX.XXX.XXX-X", Status.BAD_REQUEST);
            }
            
            if(name.equals("")){
                return new Response("Name must be not empty", Status.BAD_REQUEST);
            }
            
            if(address.equals("")){
                return new Response("Address must be not empty", Status.BAD_REQUEST);
            }
            
            if(managerID == null || managerID.isEmpty()){
                return new Response("Manager must be selected", Status.BAD_REQUEST);
            }
            
            long managerIDlong = Long.parseLong(managerID);
            StorageManager storageManager = StorageManager.getInstance();
            Manager manager = storageManager.getManager(managerIDlong);
            
            StoragePublisher storagePublisher = StoragePublisher.getInstance();
            if(!storagePublisher.addPublisher(new Publisher(nit, name, address, manager))){
                return new Response("Publisher with that nit already exits", Status.BAD_REQUEST);
            }
            return new Response("Publisher was created succesfully", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    private static boolean NITesValido(String nit) {
        // Expresión regular para validar el formato XXX.XXX.XXX-X
        String nitPatron = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d$";
        return nit.matches(nitPatron);
    }
}
