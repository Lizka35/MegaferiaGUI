
package core.controllers.utils;

public abstract class Status {
    //Successful response
    public static int OK = 200;
    public static int CREATED = 201;
    public static int NO_CONTENT = 204;
    
    //Client error response
    public static int BAD_REQUEST = 400;
    public static int NOT_FOUND = 404;
    
    //Server error response
    public static int INTERNAL_SERVER_ERROR = 500;
    public static int NOT_IMPLEMENTED = 501;
}
