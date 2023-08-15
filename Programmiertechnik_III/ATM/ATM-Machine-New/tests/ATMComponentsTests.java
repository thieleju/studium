package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidModeException;
import interfaces.ATMListener.ATM_Mode;
import src.*;

public class ATMComponentsTests {
    private ATM atm;
    private BankDatabase bankDatabase;
    private String pathToJSON = "\\bin\\Assets\\accounts.json";

    @Before
    public void setUp() throws FileNotFoundException, IOException {
        atm = new ATM(false, pathToJSON);
        bankDatabase = atm.getBankDatabase();
        atm.atmSwitchModeAction(ATM_Mode.LOGIN);
        atm.atmEnterAction("1111");
    }

    @Test
    public void checkComponents() {
        assertTrue(atm.getScreen() != null);
        assertTrue(bankDatabase != null);
    }

    @Test
    public void checkLogin() {
        assertTrue(atm.getCurrentAccount() == bankDatabase.getAccounts().get(0));
    }

    @Test
    public void checkShowBalance() {
        atm.atmEnterAction("1");

        assertTrue(atm.getCurrentMode() == ATM_Mode.BALANCE);
    }

    @Test
    public void checkWithdraw() {
        atm.atmEnterAction("2");

        assertTrue(atm.getCurrentMode() == ATM_Mode.WITHDRAWAL);
    }

    @Test
    public void checkDeposit() {
        atm.atmEnterAction("3");

        assertTrue(atm.getCurrentMode() == ATM_Mode.DEPOSIT);
    }

    @Test
    public void checkLogout() {
        atm.atmEnterAction("4");

        assertTrue(atm.getCurrentMode() == ATM_Mode.CARD_REQ);
    }

    @Test
    public void checkFalseInput() {
        atm.atmEnterAction("65343743");

        assertThrows(InvalidModeException.class, () -> {
            atm.getModeFromString("0");
        });
        assertThrows(InvalidModeException.class, () -> {
            atm.getModeFromString("komplett falscher Input!");
        });

        // check if still in same mode
        assertTrue(atm.getCurrentMode() == ATM_Mode.MENU);
    }

    @Test
    public void checkBackToMenuButton() {
        atm.atmEnterAction("1");
        // simulate "back-button" press
        atm.atmSwitchModeAction(ATM_Mode.MENU);

        atm.atmEnterAction("2");
        // simulate "back-button" press
        atm.atmSwitchModeAction(ATM_Mode.MENU);

        atm.atmEnterAction("3");
        // simulate "back-button" press
        atm.atmSwitchModeAction(ATM_Mode.MENU);

        assertTrue(atm.getCurrentMode() == ATM_Mode.MENU);
    }

    @Test
    public void checkClearButton() {
        atm.getScreen().getSidePanel().setTfPin("54325");

        atm.getScreen().buttonPressed("Clear");

        assertTrue(atm.getScreen().getSidePanel().getTextFieldText().equals(""));
    }
}
