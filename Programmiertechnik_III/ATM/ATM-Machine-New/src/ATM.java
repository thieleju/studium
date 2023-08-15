package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import exceptions.InvalidModeException;
import exceptions.InvalidTransactionException;
import exceptions.LoginFailedException;
import interfaces.ATMListener;

/**
 * Haupt-ATM-Klasse.
 * Hier werden Funktionen wie Moduswechel, Enter-Taste gedrückt oder Ein- und
 * Auszahlung behandelt.
 * 
 * @author Die Panzerknacker
 */
public class ATM implements ATMListener {

  private static ATM uniqueinstance;
  private boolean debugMode = false;

  private final static String pathToJSONDefault = "\\ATM-Machine-New\\assets\\accounts.json";
  private final String title = "ATM Machine";

  // Komponenten der Hauptklasse
  private Screen screen;
  private BankDatabase bankDatabase;
  private CashDispenser cashDispenser;
  private Account currentAccount;
  private ATM_Mode currentMode;

  /**
   * Konstruktor für die Hauptklasse. Hier werden der Screen und die BankDatabase
   * initialisiert.
   * 
   * @param debug      Debugmodus
   * @param pathToJSON Pfad zu der JSON-Datei
   * @throws FileNotFoundException
   * @throws IOException
   */
  public ATM(boolean debug, String pathToJSON) throws FileNotFoundException, IOException {
    debugMode = debug;
    screen = new Screen(this, title);
    bankDatabase = new BankDatabase(pathToJSON);
    cashDispenser = new CashDispenser(debug);
  }

  /**
   * Funktion um die Instanz der Hauptklasse zu erhalten oder eine neue
   * einzigartige Instanz zu erstellen.
   * 
   * @param debugMode  Debugmodus
   * @param pathToJSON Pfad zu der JSON-Datei
   * @return einzigartige Instanz der Hauptklasse
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static ATM getInstance(boolean debugMode, String pathToJSON) throws FileNotFoundException, IOException {
    if (pathToJSON == null)
      pathToJSON = pathToJSONDefault;

    if (uniqueinstance == null)
      uniqueinstance = new ATM(debugMode, pathToJSON);

    return uniqueinstance;
  }

  /**
   * Funktion um das ATM zu starten, wechselt direkt in den Karte-Modus.
   */
  public void start() {
    this.atmSwitchModeAction(ATM_Mode.CARD_REQ);
  }

  /**
   * Hier wird eine Funktion aus dem ATMListener-Interface überschrieben. Diese
   * Funktion wird aufgerufen, sobald die Enter-Taste betätigt wird.
   * 
   * @param input Eingabe des Benutzers
   */
  @Override
  public void atmEnterAction(String input) {
    if (debugMode)
      System.out.println("Enter action in mode: " + currentMode + " with input: " + input);

    // Lösche Eingabe im Textfeld
    screen.getSidePanel().setTfPin("");

    try {
      switch (currentMode) {
        case LOGIN:
          currentAccount = bankDatabase.validateAccount(input);
          if (currentAccount.getAdmin())
            this.atmSwitchModeAction(ATM_Mode.ADMIN);
          else
            this.atmSwitchModeAction(ATM_Mode.MENU);
          break;
        case MENU:
          this.atmSwitchModeAction(this.getModeFromString(input));
          break;
        case BALANCE:
          throw new InvalidModeException("Operation im Modus BALANCE nicht erlaubt!");
        case WITHDRAWAL:
          this.withdrawTransaction(input);
          break;
        case DEPOSIT:
          this.depositTransaction(input);
          break;
        case ADMIN:
          throw new InvalidModeException("Die Admin-Ansicht muss zuerst geschlossen werden!");
        case CARD_REQ:
          this.atmSwitchModeAction(ATM_Mode.LOGIN);
          break;
      }
    } catch (NumberFormatException nfe) {
      JOptionPane.showMessageDialog(screen, nfe.getMessage(), "Formatierungsfehler", JOptionPane.ERROR_MESSAGE);

    } catch (InvalidTransactionException ite) {
      JOptionPane.showMessageDialog(screen, ite.getMessage(), "Transaktionsfehler", JOptionPane.ERROR_MESSAGE);

    } catch (LoginFailedException lfe) {
      JOptionPane.showMessageDialog(screen, lfe.getMessage(), "Anmeldefehler", JOptionPane.ERROR_MESSAGE);

    } catch (InvalidModeException ime) {
      System.out.println(ime.getMessage());

    } catch (IOException ioe) {
      JOptionPane.showMessageDialog(screen, ioe.getMessage(), "Speichern fehlgeschlagen", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Hier wird eine Funktion aus dem ATMListener-Interface überschrieben. Diese
   * Funktion wird aufgerufen, sobald sich ein Modus ändert. Dann wird der Screen
   * aktualisiert.
   * 
   * @param newMode Neuer Modus
   */
  @Override
  public void atmSwitchModeAction(ATM_Mode newMode) {
    if (debugMode) {
      System.out.println("Switched mode from: " + currentMode + " to: " + newMode);
      screen.setAdditionalTitle(newMode.toString());
    }

    currentMode = newMode;

    switch (newMode) {
      case CARD_REQ:
        screen.showCardPrompt();
        break;
      case LOGIN:
        screen.showLogin();
        break;
      case MENU:
        screen.showMenu();
        break;
      case BALANCE:
        screen.showBalance();
        break;
      case WITHDRAWAL:
        screen.showWithdrawal();
        break;
      case DEPOSIT:
        screen.showDeposit();
        break;
      case ADMIN:
        new AdminView(this, "Admin-Ansicht");
        break;
    }
  }

  /**
   * Mit dieser Funktion kann der Benutzer Geld von seinem Konto abbuchen.
   * Das Geld wird aus dem CashDispenser genommen, vom Account abgezogen und in
   * der Datenbank abgespeichert.
   * 
   * @param input Geldbetrag
   * @throws NumberFormatException
   * @throws InvalidTransactionException
   * @throws IOException
   */
  public void withdrawTransaction(String input) throws NumberFormatException, InvalidTransactionException, IOException {

    if (input.length() == 0)
      throw new InvalidTransactionException("Bitte geben Sie einen Betrag ein!");

    double amount = Double.parseDouble(input);

    if (amount < 5)
      throw new InvalidTransactionException("Sie müssen einen minimalen Betrag von 5€ abheben!");

    if (amount % 5 != 0)
      throw new InvalidTransactionException("Sie müssen einen Betrag in 5€-Schritten abheben!");

    if (amount > 1000)
      throw new InvalidTransactionException("Sie können maximal 1000€ auf einmal abheben!");

    if (amount > currentAccount.getAvailableBalance())
      throw new InvalidTransactionException("Der Betrag überschreitet Ihr verfügbares Guthaben!");

    int reply = JOptionPane.showConfirmDialog(screen,
        "Wollen Sie " + input + "€ von Ihrem Konto abbuchen?",
        "Geld auszahlen", JOptionPane.YES_NO_OPTION);

    if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION)
      return;

    cashDispenser.withdrawAmount(amount);

    currentAccount.setAvailableBalance(currentAccount.getAvailableBalance() - amount);
    currentAccount.setTotalBalance(currentAccount.getTotalBalance() - amount);

    bankDatabase.saveAccount(currentAccount);

    this.atmSwitchModeAction(ATM_Mode.MENU);

    JOptionPane.showMessageDialog(screen, "Erfolgreich " + input + "€ abgehoben!", "Transaktion erfolgreich",
        JOptionPane.INFORMATION_MESSAGE);
  }

  /**
   * Mit dieser Funktion kann der Benutzer Geld auf seinem Konto einzahlen.
   * Das Geld wird in den CashDispenser gelegt, in den Account eingezahlt und in
   * der Datenbank abgespeichert.
   * 
   * @param input Geldbetrag
   * @throws NumberFormatException
   * @throws InvalidTransactionException
   * @throws IOException
   */
  public void depositTransaction(String input) throws NumberFormatException, InvalidTransactionException, IOException {

    if (input.length() == 0)
      throw new InvalidTransactionException("Bitte geben Sie einen Betrag ein!");

    double amount = Double.parseDouble(input);

    if (amount < 5)
      throw new InvalidTransactionException("Sie müssen einen minimalen Betrag von 5€ einzahlen!");

    if (amount % 5 != 0)
      throw new InvalidTransactionException("Sie müssen einen Betrag in 5€-Schritten einzahlen!");

    if (amount > 5000)
      throw new InvalidTransactionException("Sie können maximal 5000€ auf einmal einzahlen!");

    int reply = JOptionPane.showConfirmDialog(screen,
        "Wollen Sie " + input + "€ auf Ihr Konto einzahlen?",
        "Geld einzahlen", JOptionPane.YES_NO_OPTION);

    if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION)
      return;

    cashDispenser.depositAmount(amount);

    currentAccount.setAvailableBalance(currentAccount.getAvailableBalance() + amount);
    currentAccount.setTotalBalance(currentAccount.getTotalBalance() + amount);

    bankDatabase.saveAccount(currentAccount);

    this.atmSwitchModeAction(ATM_Mode.MENU);

    JOptionPane.showMessageDialog(screen, "Erfolgreich " + input + "€ eingezahlt!", "Transaktion erfolgreich",
        JOptionPane.INFORMATION_MESSAGE);
  }

  public Account getCurrentAccount() {
    return currentAccount;
  }

  public BankDatabase getBankDatabase() {
    return bankDatabase;
  }

  public Screen getScreen() {
    return screen;
  }

  public ATM_Mode getCurrentMode() {
    return currentMode;
  }
}