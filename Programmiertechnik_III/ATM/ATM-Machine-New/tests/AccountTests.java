package tests;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidTransactionException;
import interfaces.ATMListener.ATM_Mode;
import src.ATM;
import src.Account;
import src.BankDatabase;

public class AccountTests {

  private ATM atm;
  private BankDatabase bankDatabase;
  private String pathToJSON = "\\bin\\Assets\\accounts.json";

  private String username = "test1";
  private String accountNumber = "4321";
  private String pin = "1234";
  private double availableBalance = 10.00;
  private double totalBalance = 10.00;
  private boolean isAdmin = false;

  private Account a1;

  @Before
  public void setUp() throws FileNotFoundException, IOException {

    atm = new ATM(true, pathToJSON);
    bankDatabase = atm.getBankDatabase();

    a1 = new Account(username, accountNumber, pin, availableBalance, totalBalance, isAdmin);
    bankDatabase.saveAccount(a1);

    atm.start();
    atm.atmSwitchModeAction(ATM_Mode.LOGIN);
    atm.atmEnterAction(pin);
  }

  @Test
  public void validatePIN() {
    assertTrue(a1.validatePIN(pin));
  }

  @Test
  public void checkCredit() throws InvalidTransactionException, IOException {
    atm.depositTransaction("5");
    assertTrue(a1.getTotalBalance() == totalBalance + 5);
  }

  @Test
  public void checkDebit() throws InvalidTransactionException, IOException {
    atm.withdrawTransaction("5");
    assertTrue(a1.getAvailableBalance() == availableBalance - 5);
  }
}