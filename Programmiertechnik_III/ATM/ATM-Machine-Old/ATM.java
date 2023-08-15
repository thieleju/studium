import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Stellt die Hauptklasse des ATMs dar
 * Sobald Enter betätigt wird, wird die PIN überprüft (login)
 * Wenn man eingeloggt ist, wird das Menü angezeigt,
 * wenn man als Admin eingeloggt ist, wird das Admin-Menü angezeigt
 * 
 * @author DanH957
 */
public class ATM {
	private boolean userAuthenticated;
	private int currentAccountNumber;
	private Screen screen;
	private Keypad keypad;
	private CashDispenser cashDispenser;
	private DepositSlot depositSlot;
	private BankDatabase bankDatabase;
	private int AdminCheck;
	private String userinput = "";
	private int position = 0;
	private static ATM uniqueinstance;
	Iterator Users = BankDatabase.createIterator();

	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;

	/**
	 * Initialisiert die UI mit Keypad, CashDispenser, DepositSlot und Bankdatabase
	 */
	public ATM() {
		userAuthenticated = false;
		currentAccountNumber = 0;
		screen = new Screen();

		keypad = new Keypad();

		cashDispenser = new CashDispenser();
		depositSlot = new DepositSlot();
		bankDatabase = new BankDatabase();
	}

	/**
	 * Startet den ATM und zeigt UI-Elemente an
	 * 
	 */
	public void run() {
		startlogin();
	}

	/**
	 * Zeigt den Login-Bildschirm an und initilaisiert den A
	 */
	void startlogin() {
		position = 0;
		screen.createlogin();
		userinput = "";

		authenticate check = new authenticate();
		screen.Mainframe.revalidate();
		screen.Inputfield2.setText("");
		keypad.setbuttons();
		addkeypadlisteners();

		screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);

		screen.Mainframe.revalidate();
		keypad.BEnter.addActionListener(check);
		screen.Mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screen.Mainframe.setSize(400, 280);
		screen.Mainframe.setVisible(true);
		screen.Mainframe.revalidate();
	}

	/**
	 * Überprüft, ob die eingegebene Pin einem Nutzer gehört
	 * Wenn ein Nutzer gefunden wurde, wird das Menü angezeigt
	 * Falls der Nutzer ein Administrator ist, wird das Admin-Menü angezeigt
	 * 
	 * @param pin Eingegebene Pin des Nutzers
	 */
	public void authenticateuser(int pin) {
		userAuthenticated = bankDatabase.authenticateUser(pin);

		if (userAuthenticated) {
			int accountNumber = bankDatabase.getaccpin(pin);
			AdminCheck = bankDatabase.getadmin(pin);
			if (AdminCheck == 0) {
				currentAccountNumber = accountNumber;
				screen.Mainframe.getContentPane().removeAll();
				screen.Mainframe.revalidate();
				createmenu();
				screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
				screen.Mainframe.revalidate();
			}
			/**
			 * Im else zweig wird nur die createAdminGUI() funktion aufgerufen, wegen eins
			 * Klammerfehlers
			 * 
			 */
			else

				createAdminGUI();
			Iterator UserIterator = BankDatabase.createIterator();
			Addcheck check = new Addcheck();
			Deletecheck check2 = new Deletecheck();
			screen.button2.addActionListener(check);
			screen.button3.addActionListener(check2);

		} else
			screen.messageJLabel3.setText(
					"Invalid account number or PIN. Please try again.");
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Liest die Nutzerpin ein und ruft die Funktion "authenticate()" auf
	 */
	private class authenticate implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			int PIN = Integer.parseInt(screen.Inputfield2.getText());

			authenticateuser(PIN);
		}
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Event fügt einen Nutzer hinzu
	 */
	private class Addcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			BankDatabase.Adduser();

		}
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Löscht User der akutellen Position
	 */
	private class Deletecheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			BankDatabase.Deleteuser(position);
			position = position - 1;

		}
	}

	/**
	 * GUI für das Menü wird angezeigt
	 */
	public void createmenu() {
		screen.setSize(300, 150);
		balancecheck check1 = new balancecheck();
		Depositcheck check2 = new Depositcheck();
		Withdrawcheck check3 = new Withdrawcheck();
		Exitcheck check4 = new Exitcheck();
		screen.Mainframe.getContentPane().removeAll();
		screen.Mainframe.revalidate();

		screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
		screen.createmenu();
		Account Account1 = bankDatabase.getAccount(currentAccountNumber);
		screen.messageJLabel.setText("Welcome " + Account1.getUsername()
				+ "                                                                                   ");

		keypad.B1.addActionListener(check1);
		keypad.B2.addActionListener(check3);
		keypad.B3.addActionListener(check2);
		keypad.B4.addActionListener(check4);
		screen.Mainframe.revalidate();
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Führt Transaktion 1 aus
	 */
	private class balancecheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userinput = "";
			performTransactions(1);
		}
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Führt Transaktion 3 aus
	 */
	private class Depositcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userinput = "";
			performTransactions(3);
		}
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Führt Transaktion 2 aus
	 */
	private class Withdrawcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userinput = "";
			performTransactions(2);
		}
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Führt Login aus
	 */
	private class Exitcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			startlogin();
		}
	}

	/**
	 * Ausführen einer Transaktion
	 * 
	 * @param a Transaktions-Typ
	 */
	private void performTransactions(int a) {

		Transaction currentTransaction = null;

		currentTransaction = createTransaction(a);
		keypad.setbuttons();
		addkeypadlisteners();

		userinput = "";
		screen.Inputfield2.setText(userinput);
		currentTransaction.execute();

		Backcheck Back = new Backcheck();
		screen.Exit.addActionListener(Back);
		screen.Mainframe.revalidate();

	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Zeigt das Menu an
	 */
	public class Backcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			createmenu();
			screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
			screen.Mainframe.revalidate();
			userinput = "";
			screen.Inputfield2.setText(userinput);
		}
	}

	/**
	 * Erstellt eine Transaktion BalanceInquiry, WIthdrawal oder Deposit
	 * 
	 * @param type Transaktions-Typ
	 * @return Transaction Gibt erstellte Transaktion zurück
	 */
	private Transaction createTransaction(int type) {
		Transaction temp = null;
		screen.getContentPane().removeAll();
		screen.revalidate();

		if (type == 1)
			temp = new BalanceInquiry(
					currentAccountNumber, screen, bankDatabase);
		else if (type == 2)
			temp = new Withdrawal(currentAccountNumber, screen,
					bankDatabase, keypad, cashDispenser);
		else if (type == 3) {
			screen.setSize(400, 250);
			temp = new Deposit(currentAccountNumber, screen,
					bankDatabase, keypad, depositSlot);
		}

		return temp;
	}

	/**
	 * Admin GUI wird angezeigt
	 * 
	 */
	public void createAdminGUI() {

		screen.Mainframe.getContentPane().removeAll();
		Nextcheck Ncheck = new Nextcheck();
		Prevcheck Pcheck = new Prevcheck();
		Exitcheck check4 = new Exitcheck();
		screen.Mainframe.revalidate();
		screen.createAdminpage();
		screen.button1.addActionListener(Ncheck);
		screen.button4.addActionListener(Pcheck);
		screen.Exit.addActionListener(check4);
		screen.Mainframe.revalidate();

	}

	/**
	 * Weißt jedem Button einen Event-Handler zu
	 */
	public void addkeypadlisteners() {
		BCheck BC = new BCheck();
		BClear BC1 = new BClear();
		keypad.B1.addActionListener(BC);
		keypad.B2.addActionListener(BC);
		keypad.B3.addActionListener(BC);
		keypad.B4.addActionListener(BC);
		keypad.B5.addActionListener(BC);
		keypad.B6.addActionListener(BC);
		keypad.B7.addActionListener(BC);
		keypad.B8.addActionListener(BC);
		keypad.B9.addActionListener(BC);
		keypad.B0.addActionListener(BC);
		keypad.BClear.addActionListener(BC1);
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Sobald eine Zahl betätigt wird, wird der userinput verändert
	 */
	public class BCheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			String label = b.getLabel();
			userinput = userinput + label;

			screen.Inputfield2.setText(userinput);

		}
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Cleart den Userinput
	 * 
	 */
	public class BClear implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			userinput = "";
			screen.Inputfield2.setText(userinput);
		}
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Erstellt ein Iterator-Objekt aus der BankDatabase, mit dem man die Accounts
	 * wechseln kann
	 */
	public class Nextcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			IterateUser(BankDatabase.createIterator());
		}
	}

	/**
	 * Innere Klasse, die einen ActionListeners implementiert
	 * Erstellt ein Iterator-Objekt aus der BankDatabase, mit dem man die Accounts
	 * wechseln kann
	 */
	public class Prevcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			prevIterateUser(BankDatabase.createIterator());
		}
	}

	/**
	 * Zeigt die Balance Info für den nächsten User an
	 * 
	 * @param Iterator Iterator-Objekt
	 */
	public void IterateUser(Iterator Iterator) {
		if (Iterator.hasNext(position) == true) {
			position = position + 1;

			Account AccountItem = (Account) Iterator.next(position);
			screen.messageJLabel2.setText("Username: " + AccountItem.getUsername());
			screen.messageJLabel3.setText("Avaliable Balance: " + AccountItem.getAvailableBalance());
			screen.messageJLabel4.setText("Avaliable Balance: " + AccountItem.getTotalBalance());
		}

	}

	/**
	 * Zeigt die Balance Info für den vorherigen User an
	 * 
	 * @param Iterator Iterator-Objekt
	 */
	public void prevIterateUser(Iterator Iterator) {
		if (Iterator.hasPrev(position) == true) {
			position = position - 1;
			Account AccountItem = (Account) Iterator.next(position);
			screen.messageJLabel2.setText("Username: " + AccountItem.getUsername());
			screen.messageJLabel3.setText("Avaliable Balance: " + AccountItem.getAvailableBalance());
			screen.messageJLabel4.setText("Avaliable Balance: " + AccountItem.getTotalBalance());

		}

	}

	/**
	 * Erstellt eine Instanz, falls noch keine vorhanden
	 * Falls eine Instanz vorhanden ist, gebe diese zurück
	 * 
	 * @return ATM
	 */
	public static ATM getinstance() {
		if (uniqueinstance == null) {
			uniqueinstance = new ATM();
		}
		return uniqueinstance;
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