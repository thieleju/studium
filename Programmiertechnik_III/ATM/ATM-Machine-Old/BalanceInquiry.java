import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Diese Klasse stellt eine Subklasse einer Transaktion dar und wird verwendet,
 * um den Kontostand auf dem Bildschirm anzuzeigen
 * 
 * @author DanH957
 */
public class BalanceInquiry extends Transaction {

   public BalanceInquiry(int userAccountNumber, Screen atmScreen,
         BankDatabase atmBankDatabase) {
      super(userAccountNumber, atmScreen, atmBankDatabase);
   }

   /**
    * Diese Methode wird aufgerufen, wenn der Benutzer den Kontostand abruft
    */
   @Override
   public void execute() {

      BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();

      double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());

      double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());

      screen.creatBalanceGUI();
      screen.messageJLabel2.setText("Avaliable Balance: " + availableBalance);
      screen.messageJLabel3.setText("Total Balance: " + totalBalance);
      screen.Mainframe.revalidate();

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