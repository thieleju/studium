package exceptions;

/**
 * Eine Exception, die ausgelöst wird, wenn ein ungültiger Modus im Menü
 * erreicht wird.
 * 
 * @author Die Panzerknacker
 */
public class InvalidModeException extends Exception {

  public InvalidModeException(String message) {
    super(message);
  }
}
