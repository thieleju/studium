package src;

/**
 * Die Klasse Account ist eine Klasse, die ein Konto repräsentiert.
 * 
 * @author Die Panzerknacker
 */
public class Account {

  private String username;
  private String accountNumber;
  private String pin;
  private double availableBalance;
  private double totalBalance;
  private boolean isAdmin;

  /**
   * Konstruktor der Klasse Account.
   * 
   * @param username         Der Benutzername des Accounts.
   * @param accountNumber    Das Kontonummer des Accounts.
   * @param pin              Das PIN-Code des Accounts.
   * @param availableBalance Der verfügbare Kontostand.
   * @param totalBalance     Der gesamte Kontostand.
   * @param isAdmin          Ob das Konto ein Admin-Konto ist.
   */

  public Account(String username, String accountNumber, String pin, double availableBalance, double totalBalance,
      boolean isAdmin) {
    this.username = username;
    this.accountNumber = accountNumber;
    this.pin = pin;
    this.availableBalance = availableBalance;
    this.totalBalance = totalBalance;
    this.isAdmin = isAdmin;
  }

  /**
   * Funktion überprüft den eingegebenen PIN-Code.
   * 
   * @param userPIN Der eingegebene PIN-Code.
   * @return true, falls der PIN-Code korrekt ist, false falls nicht.
   */

  public boolean validatePIN(String userPIN) {
    if (pin.equals(userPIN))
      return true;
    else
      return false;
  }

  public double getAvailableBalance() {
    return availableBalance;
  }

  public double getTotalBalance() {
    return totalBalance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getPin() {
    return pin;
  }

  public String getUsername() {
    return username;
  }

  public boolean getAdmin() {
    return isAdmin;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public void setAvailableBalance(double availableBalance) {
    this.availableBalance = availableBalance;
  }

  public void setTotalBalance(double totalBalance) {
    this.totalBalance = totalBalance;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  /**
   * Funktion gibt Informationen zu einem Account aus.
   */
  public void printInfo() {
    System.out.println("Username: " + username);
    System.out.println("Account Number: " + accountNumber);
    System.out.println("PIN: " + pin);
    System.out.println("Available Balance: " + availableBalance);
    System.out.println("Total Balance: " + totalBalance);
    System.out.println("Admin: " + isAdmin);
  }
}