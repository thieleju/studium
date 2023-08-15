import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasse erbt von Transaction und überschreibt die Execute-Funktion
 * 
 */
public class Withdrawal extends Transaction {
   private int amount;
   private Keypad keypad;
   private CashDispenser cashDispenser;

   private final static int CANCELED = 6;

   public Withdrawal(int userAccountNumber, Screen atmScreen,
         BankDatabase atmBankDatabase, Keypad atmKeypad,
         CashDispenser atmCashDispenser) {

      super(userAccountNumber, atmScreen, atmBankDatabase);

      keypad = atmKeypad;
      cashDispenser = atmCashDispenser;
   }

   /**
    * Die Execute-Funktion zeigt die Buttons zur Scheinauswahl an
    * 
    */
   @Override
   public void execute() {

      displayMenuOfAmounts();
   }

   private void withdrawCash(int amount) {
      int billsRequired;
      int billsToDispense;

      billsRequired = cashDispenser.getCashCount() / amount;

      if (billsRequired > 0) {
         billsToDispense = bankDatabase.getAvailableBalance(getAccountNumber())
               / amount;

         if (billsRequired <= billsToDispense) {
            cashDispenser.dispenseCash(amount);
            bankDatabase.debit(getAccountNumber(), amount);
            screen.displayMessageLine("\n" + amount + " $ dispensed.");
         } else {
            screen.displayMessageLine(
                  "\nInsufficient funds in the ATM.\n\nPlease choose a smaller amount.");
            screen.displayMessageLine("\nPress Enter to continue...");
            keypad.waitForEnter();
            screen.clearMessage();
         }
      } else {
         screen.displayMessageLine(
               "\nInsufficient cash in the ATM.\n\nPlease choose a smaller amount.");
         screen.displayMessageLine("\nPress Enter to continue...");
         keypad.waitForEnter();
         screen.clearMessage();
      }
   }

   /**
    * Die Transaction-Funktion ermöglicht das abheben von Geld, wenn genügend auf
    * Bankkonto und im CashDispenser vorhanden ist
    *
    * @param amount Geld das abgehoben werden soll
    */
   public void transaction(int amount) {
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      boolean cashDispensed = false;
      double availableBalance;

      availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());

      if (amount <= availableBalance) {

         if (cashDispenser.isSufficientCashAvailable(amount)) {

            bankDatabase.debit(getAccountNumber(), amount);

            cashDispenser.dispenseCash(amount);
            cashDispensed = true;

            screen.messageJLabel7.setText("\nYour cash has been" +
                  " dispensed. Please take your cash now.");
         } else
            screen.messageJLabel7.setText(
                  "\nInsufficient cash available in the ATM." +
                        "\n\nPlease choose a smaller amount.");
      } else {
         screen.messageJLabel7.setText(
               "\nInsufficient funds in your account." +
                     "\n\nPlease choose a smaller amount.");
      }
   }

   /**
    * displayMenuOfAmounts zeigt das GUI-Menü zu scheinauswahl an
    * 
    */
   private void displayMenuOfAmounts() {

      int userChoice = 0;

      Screen screen = getScreen();
      screen.createWithdrawGUI();
      screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
      withdraw1 check1 = new withdraw1();
      withdraw2 check2 = new withdraw2();
      withdraw3 check3 = new withdraw3();
      withdraw4 check4 = new withdraw4();
      withdraw5 check5 = new withdraw5();
      Keypad.B1.addActionListener(check1);
      Keypad.B2.addActionListener(check2);
      Keypad.B3.addActionListener(check3);
      Keypad.B4.addActionListener(check4);
      Keypad.B5.addActionListener(check5);

      screen.Mainframe.revalidate();
   }

   /**
    * Innere Klasse, die einen ActionListeners implementiert
    * Wird ausgeführt wenn 20€ gewählt wurde
    */
   public class withdraw1 implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         transaction(20);
      }
   }

   /**
    * Innere Klasse, die einen ActionListeners implementiert
    * Wird ausgeführt wenn 40€ gewählt wurde
    */
   public class withdraw2 implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         transaction(40);
      }
   }

   /**
    * Innere Klasse, die einen ActionListeners implementiert
    * Wird ausgeführt wenn 60€ gewählt wurde
    */
   public class withdraw3 implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         transaction(60);
      }
   }

   /**
    * Innere Klasse, die einen ActionListeners implementiert
    * Wird ausgeführt wenn 100€ gewählt wurde
    */
   public class withdraw4 implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         transaction(100);
      }
   }

   /**
    * Innere Klasse, die einen ActionListeners implementiert
    * Wird ausgeführt wenn 200€ gewählt wurde
    */
   public class withdraw5 implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         transaction(200);
      }
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