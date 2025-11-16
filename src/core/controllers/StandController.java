
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Stand;
import core.models.storage.StorageStand;

public class StandController {
    public static Response createStand(String id, String price){
        try {
            long idLong;
            double priceDouble;
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
            
            try {
                priceDouble = Double.parseDouble(price);
                if(priceDouble < 0){
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (price.equals("")) {
                    return new Response("Price must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Price must be numeric", Status.BAD_REQUEST);
            }
            
            StorageStand storageStand = StorageStand.getInstance();
            if(!storageStand.addStand(new Stand(idLong, priceDouble))){
                return new Response("Stand with that id already exits", Status.BAD_REQUEST);
            }
            return new Response("Stand was created succesfully", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
