package interfaces;

/**
 * Eine Schnittstelle, die für den Austausch der Button-Events zwischen dem
 * Keypad und dem Screen benutzt wird.
 * 
 * @author Die Panzerknacker
 */
public interface KeypadListener {
  void buttonPressed(String value);
}