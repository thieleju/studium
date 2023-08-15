package exceptions;

/**
 * Eine Exception, die geworfen wird, wenn gewisse scheine nicht mehr verfügbar
 * sind oder wenn der betragt der ausgezahlt werden soll größer ist als der
 * verfügbare Betrag ist.
 * 
 * @author Die Panzerknacker
 */
public class InvalidTransactionException extends Exception {

  public InvalidTransactionException(String message) {
    super(message);
  }

}
