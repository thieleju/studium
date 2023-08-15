import java.util.ArrayList;

/**
 * Diese Klasse erzeugt alle Konten und stellt sie in einer ArrayList zur
 * Verfügung.
 * 
 * @author DanH957
 */
public class BankDatabase {
   static ArrayList<Account> accounts = new ArrayList<Account>();

   /**
    * BankDatabase Initialisiert alle Accounts
    */
   public BankDatabase() {

      Account accounts1 = new Account("Customer1", 12345, 11111, 1000.0, 1200.0, 0);
      Account accounts2 = new Account("Customer2", 98765, 22222, 200.0, 200.0, 0);
      Account accounts3 = new Account("Customer3", 19234, 33333, 200.0, 200.0, 0);
      Account accounts4 = new Account("Manager1", 99999, 00000, 0, 0, 1);
      accounts.add(accounts1);
      accounts.add(accounts2);
      accounts.add(accounts3);
      accounts.add(accounts4);
   }

   /**
    * Gibt einen Account anhand seiner Accountnummer zurück
    * 
    * @param accountnumber
    * @return Account
    */
   public Account getAccount(int accountnumber) {

      for (Account currentAccount : accounts) {

         if (currentAccount.getAccountNumber() == accountnumber)
            return currentAccount;
      }

      return null;
   }

   /**
    * Gibt einen Account anhand seiner PIN zurück
    * 
    * @param PIN
    * @return Account
    */
   private Account getAccountpin(int PIN) {

      for (Account currentAccount : accounts) {

         if (currentAccount.GetPin() == PIN)
            return currentAccount;
      }

      return null;
   }

   /**
    * Stellt fest, ob eine Pin zu einem Usernamen gehört
    * 
    * @param userPIN
    * @return boolean
    */
   public boolean authenticateUser(int userPIN) {

      Account userAccount = getAccountpin(userPIN);

      if (userAccount != null)
         return userAccount.validatePIN(userPIN);
      else
         return false;
   }

   /* Getter und Setter für Eigenschaften der Accounts */

   public double getAvailableBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getAvailableBalance();
   }

   public double getTotalBalance(int userAccountNumber) {
      return getAccount(userAccountNumber).getTotalBalance();
   }

   public void credit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).credit(amount);
   }

   public void debit(int userAccountNumber, double amount) {
      getAccount(userAccountNumber).debit(amount);
   }

   public int getadmin(int userAccountNumber) {
      return getAccountpin(userAccountNumber).getISadmin();
   }

   /**
    * Erzeugt ein Iterator-Objekt
    * 
    * @return Iterator-Objekt
    */
   public static Iterator createIterator() {
      return new AccountIterator(accounts);
   }

   /**
    * Funktion funktioniert nicht, da nur 1 Durchgang durch die Liste stattfindet
    * 
    * @param PIN
    * @return int
    */
   public int getaccpin(int PIN) {
      for (Account currentAccount : accounts) {

         if (currentAccount.GetPin() == PIN)
            return currentAccount.getAccountNumber();
         else
            return 1;
      }
      return PIN;
   }

   /**
    * Erstellt einen Neuen Benutzer
    */
   public static void Adduser() {
      String name = Screen.Inputfield1.getText();
      int accountnumber = Integer.parseInt(Screen.Inputfield2.getText());
      int pin = Integer.parseInt(Screen.Inputfield4.getText());
      int balance = Integer.parseInt(Screen.Inputfield3.getText());

      Account newaccount = new Account(name, accountnumber, pin, balance, balance, 0);
      accounts.add(newaccount);

      Screen.Inputfield1.setText("");
      Screen.Inputfield2.setText("");
      Screen.Inputfield3.setText("");
      Screen.Inputfield4.setText("");
   }

   /**
    * Löscht User der aktuellen Position im Admin Menü
    * 
    * @param position
    */
   public static void Deleteuser(int position) {
      accounts.remove(position);

   }

}

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and *
 * Pearson Education, Inc. All Rights Reserved. *
 * *
 * DISCLAIMER: The authors and publisher of this book have used their *
 * best efforts in preparing the book. These efforts include the *
 * development, research, and testing of the theories and programs *
 * to determine their effectiveness. The authors and publisher make *
 * no warranty of any kind, expressed or implied, with regard to these *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or *
 * consequential damages in connection with, or arising out of, the *
 * furnishing, performance, or use of these programs. *
 *************************************************************************/