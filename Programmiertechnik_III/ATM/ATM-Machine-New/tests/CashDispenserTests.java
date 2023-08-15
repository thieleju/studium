package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidTransactionException;
import src.CashDispenser;

public class CashDispenserTests {

  private CashDispenser cashDispenser;

  @Before
  public void setUp() {
    cashDispenser = new CashDispenser(true);
  }

  @Test
  public void checkMoneyInventory() {
    HashMap<String, Integer> inventory = cashDispenser.getMoneyInventory();

    assertTrue(inventory.get("5€") == 100);
    assertTrue(inventory.get("10€") == 100);
    assertTrue(inventory.get("20€") == 100);
    assertTrue(inventory.get("50€") == 100);
    assertTrue(inventory.get("100€") == 100);
  }

  @Test
  public void checkDepositAmount() throws InvalidTransactionException {
    cashDispenser.depositAmount(875);

    HashMap<String, Integer> inventory = cashDispenser.getMoneyInventory();

    assertTrue(inventory.get("100€") == 108);
    assertTrue(inventory.get("50€") == 101);
    assertTrue(inventory.get("20€") == 101);
    assertTrue(inventory.get("10€") == 100);
    assertTrue(inventory.get("5€") == 101);
  }

  @Test
  public void checkWithdrawAmount() throws InvalidTransactionException {
    cashDispenser.withdrawAmount(875);

    HashMap<String, Integer> inventory = cashDispenser.getMoneyInventory();

    assertTrue(inventory.get("100€") == 92);
    assertTrue(inventory.get("50€") == 99);
    assertTrue(inventory.get("20€") == 99);
    assertTrue(inventory.get("10€") == 100);
    assertTrue(inventory.get("5€") == 99);
  }

  @Test
  public void checkInvalidWithdraw() throws InvalidTransactionException {

    assertThrows(InvalidTransactionException.class, () -> {
      cashDispenser.withdrawAmount(-5);
    });
    assertThrows(InvalidTransactionException.class, () -> {
      cashDispenser.withdrawAmount(7);
    });
  }

  @Test
  public void checkInvalidDeposit() throws InvalidTransactionException {

    assertThrows(InvalidTransactionException.class, () -> {
      cashDispenser.depositAmount(-4);
    });
    assertThrows(InvalidTransactionException.class, () -> {
      cashDispenser.withdrawAmount(7);
    });
  }

}
