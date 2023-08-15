package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import interfaces.ATMListener.ATM_Mode;
import src.*;

public class AdminViewTests {

  private ATM atm;
  private BankDatabase bankDatabase;
  private String pathToJSON = "\\bin\\Assets\\accounts.json";

  private String username = "admin1";
  private String accountNumber = "88888";
  private String pin = "9876";
  private double availableBalance = 0;
  private double totalBalance = 0;
  private boolean isAdmin = true;

  private Account a1;

  private AdminView adminView;

  @Before
  public void setUp() throws FileNotFoundException, IOException {

    atm = new ATM(false, pathToJSON);
    bankDatabase = atm.getBankDatabase();
    a1 = new Account(username, accountNumber, pin, availableBalance, totalBalance, isAdmin);

    ArrayList<Account> accounts = bankDatabase.getAccounts();
    accounts.add(a1);
    bankDatabase.saveAccountsToFile(accounts);

    atm.start();

    atm.atmSwitchModeAction(ATM_Mode.LOGIN);

    atm.atmEnterAction(pin);

    assertEquals(atm.getCurrentMode(), ATM_Mode.ADMIN);

    adminView = new AdminView(atm, "Test");
  }

  @Test
  public void checkBtnCreateNew() {

    int oldAccountsSize = adminView.getAccounts().size();

    adminView.btnCreateNew();

    ArrayList<Account> newAccounts = adminView.getAccounts();
    int newAccountsSize = newAccounts.size();
    assertEquals(oldAccountsSize + 1, newAccountsSize);

    Account lastAcc = newAccounts.get(newAccountsSize - 1);
    assertEquals(lastAcc.getUsername(), "Neuer Benutzer");
  }

  @Test
  public void checkBtnSave() {

  }
}
