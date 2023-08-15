import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Die Klasse Deposit erbt von Transaction und überschreibt die Execute-Funktion
 * Execute-Funktion zeigt die UI zum Geldeinzahlen an
 */
public class Deposit extends Transaction {
   private double amount;
   private Keypad keypad;
   private DepositSlot depositSlot;
   private final static int CANCELED = 0;

   public Deposit(int userAccountNumber, Screen atmScreen,
         BankDatabase atmBankDatabase, Keypad atmKeypad,
         DepositSlot atmDepositSlot) {

      super(userAccountNumber, atmScreen, atmBankDatabase);

      keypad = atmKeypad;
      depositSlot = atmDepositSlot;
   }

   @Override
   public void execute() {
      promptForDepositAmount();
   }

   /**
    * makedeposit prüft, ob wirklich eingezahlt wurde
    * 
    * @param amount Geld das eingezahlt wird
    */
   public void makedeposit(double amount) {
      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();

      if (amount != CANCELED) {

         screen.messageJLabel2.setText("\nPlease insert a deposit envelope containing " + amount);

         boolean envelopeReceived = depositSlot.isEnvelopeReceived();

         if (envelopeReceived) {
            screen.messageJLabel2.setText("\nYour envelope has been " +
                  "received.\nNOTE: The money just deposited will not ");
            screen.messageJLabel3.setText("be available until we verify the amount of any " +
                  "enclosed cash and your checks clear.");

            bankDatabase.credit(getAccountNumber(), amount);
         } else {
            screen.messageJLabel2.setText("\nYou did not insert an " +
                  "envelope, so the ATM has canceled your transaction.");
         }
      } else {
         screen.messageJLabel2.setText("\nCanceling transaction...");
      }
   }

   /**
    * promptForDepositAmount startet das GUI zum einzahlen
    * 
    */
   private void promptForDepositAmount() {
      Screen screen = getScreen();

      screen.CreateDepositGUI();
      screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
      Depositcheck check1 = new Depositcheck();
      keypad.BEnter.addActionListener(check1);
      screen.Mainframe.revalidate();

   }

   /**
    * Innere Klasse die ActionListener implementiert
    * 
    */
   private class Depositcheck implements ActionListener {
      public void actionPerformed(ActionEvent e) {

         double input = Integer.parseInt(screen.Inputfield2.getText());
         double amount = input / 100;

         makedeposit(amount);

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