package exceptions;

/**
 * Eine Exception, die ausgelöst wird, wenn sich ein Benutzer mit einer falschen
 * PIN angemeldet.
 * 
 * @author Die Panzerknacker
 */
public class LoginFailedException extends Exception {

  public LoginFailedException(String message) {
    super(message);
  }
}
