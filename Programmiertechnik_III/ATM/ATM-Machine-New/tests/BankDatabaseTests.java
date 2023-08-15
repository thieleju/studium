package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import exceptions.LoginFailedException;
import src.*;

public class BankDatabaseTests {

  private ATM atm;
  private BankDatabase bankDatabase;
  private String pathToJSON = "\\bin\\Assets\\accounts.json";

  private String username = "test2";
  private String accountNumber = "78910";
  private String pin = "8989";
  private double availableBalance = 100.00;
  private double totalBalance = 200.00;
  private boolean isAdmin = false;

  private Account account;

  @Before
  public void setUp() throws FileNotFoundException, IOException {

    atm = new ATM(false, pathToJSON);
    bankDatabase = atm.getBankDatabase();
    account = new Account(username, accountNumber, pin, availableBalance, totalBalance, isAdmin);

    ArrayList<Account> accounts = bankDatabase.getAccounts();
    accounts.add(account);
    bankDatabase.saveAccountsToFile(accounts);

    atm.start();
  }

  @Test
  public void loginAccount() throws LoginFailedException {
    Account a = bankDatabase.validateAccount(pin);

    assertEquals(a.getAccountNumber(), account.getAccountNumber());
  }

  @Test
  public void negativeLoginAccount() {
    assertThrows(LoginFailedException.class, () -> {
      bankDatabase.validateAccount("wrongPin");
    });
    assertThrows(LoginFailedException.class, () -> {
      bankDatabase.validateAccount("12345");
    });
    assertThrows(LoginFailedException.class, () -> {
      bankDatabase.validateAccount("123");
    });
  }

}