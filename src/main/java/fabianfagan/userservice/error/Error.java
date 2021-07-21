package fabianfagan.userservice.error;


/**
 * A generic Error to be thrown when any request requirement is not met
 * or a User is not found. 
 * @author Fabian Fagan
 */
public class Error extends RuntimeException{
    public Error() {
        super("An unexpected error occured");
      }
}
