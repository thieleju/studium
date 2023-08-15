/**
 * Diese Klasse ist für die Geldausgabe zuständig und enthält den Geldbetrag des
 * Geldautomaten
 * 
 * @author DanH957
 */
public class CashDispenser {

   private final static int INITIAL_COUNT = 500;
   private int count;

   public CashDispenser() {
      count = INITIAL_COUNT;
   }

   /**
    * Diese Methode gibt Geld in 10$ Scheinen aus.
    * 
    * @param amount Menge an Geld
    */
   public void dispenseCash(int amount) {
      int billsRequired = amount / 20;
      count -= billsRequired;
   }

   /**
    * Diese Methode stellt fest, ob noch genügend Geld für eine Auszahlung
    * vorhanden ist
    * 
    * @param amount Menge an Geld für die Auszahlung
    * @return boolean
    */
   public boolean isSufficientCashAvailable(int amount) {
      int billsRequired = amount / 20;

      if (count >= billsRequired)
         return true;
      else
         return false;
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