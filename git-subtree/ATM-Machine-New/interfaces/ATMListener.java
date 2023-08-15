package interfaces;

import exceptions.InvalidModeException;

/**
 * Eine Schnittstelle, mit der Klassen an das ATM kommunizieren können, ob enter
 * gedrückt wurde oder sich der Modus gewechselt hat.
 * 
 * @author Die Panzerknacker
 */
public interface ATMListener {

  /**
   * Stellt Modi zur Verfügung, die das ATM verwendet um zu entscheiden, wie auf
   * eine Eingabe reagiert werden soll.
   */
  enum ATM_Mode {
    CARD_REQ, LOGIN, MENU, BALANCE, WITHDRAWAL, DEPOSIT, ADMIN
  };

  /**
   * Wird aufgerufen, wenn der Benutzer auf Enter gedrückt hat.
   * 
   * @param input Eingabe des Benutzers
   */
  void atmEnterAction(String input);

  /**
   * Wird aufgerufen, wenn der Benutzer den Modus gewechselt hat.
   * 
   * @param mode Ein Modus von {@link ATM_Mode}
   */
  void atmSwitchModeAction(ATM_Mode mode);

  /**
   * Gibt den aktuellen Modus anhand der Nutzereingabe zurück.
   * 
   * @param input Nutzereingabe
   * @return Modus
   * @throws InvalidModeException
   */
  default ATM_Mode getModeFromString(String input) throws InvalidModeException {
    switch (input) {
      case "1":
        return ATM_Mode.BALANCE;
      case "2":
        return ATM_Mode.WITHDRAWAL;
      case "3":
        return ATM_Mode.DEPOSIT;
      case "4":
        return ATM_Mode.CARD_REQ;
      default:
        throw new InvalidModeException("Ungültiger Menu-Modus: " + input);
    }

  }
}
