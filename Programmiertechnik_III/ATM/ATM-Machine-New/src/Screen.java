package src;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import interfaces.KeypadListener;

/**
 * Bildet die Präsentationsebene des ATM.
 * Besitzt das Keypad und das Sidepanel.
 * 
 * @author Die Panzerknacker
 */
public class Screen extends JFrame implements KeypadListener {

  private Keypad keypad;
  private SidePanel sidePanel;

  private JTextField tfTop = new JTextField(20);
  private final Font tfFont = new Font("", Font.PLAIN, 12);

  private ATM atm;
  private String baseTitle;

  /**
   * Initalisiert den Screen.
   * Das Sidepanel und das Keypad bekommen eine bestimmte Größe und
   * werden dem Frame hinzugefügt.
   * 
   * @param atm       Instanz der ATM Klasse.
   * @param baseTitle String, der den Titel des JFrames bestimmt.
   */
  public Screen(ATM atm, String baseTitle) {
    super(baseTitle);
    this.baseTitle = baseTitle;
    this.atm = atm;

    sidePanel = new SidePanel(atm, 180, 160);
    keypad = new Keypad(this, 150, 160);

    // Initialisiere Hauptbildschirm
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Einstellen der Textfelder
    tfTop.setHorizontalAlignment(JLabel.CENTER);
    tfTop.setFont(tfFont);
    tfTop.setEditable(false);

    // Hinzufügen der UI-Komponenten
    add(tfTop, BorderLayout.NORTH);
    add(keypad, BorderLayout.CENTER);
    add(sidePanel, BorderLayout.LINE_END);
    pack();
    setVisible(true);
  }

  /**
   * Implementierung der Methode aus dem KeypadListener Interface.
   * Bei Druck auf den Clear-Knopf wird das Textfeld geleert.
   * Bei Druck auf den Enter-Knopf wird die Funktion atmEnterAction() mit dem
   * Input aufgerufen.
   * Ansonsten wird der Wert des jeweiligen Knopfes dem Textfeld hinzugefügt.
   * 
   * @param value Wert des Knopfes der gedrückt wurde.
   */
  @Override
  public void buttonPressed(String value) {
    if (value == "Clear")
      sidePanel.setTfPin("");
    else if (value == "Enter")
      atm.atmEnterAction(sidePanel.getTextFieldText());
    else
      sidePanel.addTextFieldChar(value);
  }

  /**
   * Fügt dem Titel des JFrames einen Zusatztitel hinzu.
   * 
   * @param title String, welcher als Titel für das JFrame genutzt wird.
   */
  public void setAdditionalTitle(String title) {
    setTitle(baseTitle + " - " + title);
  }

  /**
   * Zeigt das Interface für den Karte einstecken Modus.
   */
  public void showCardPrompt() {
    tfTop.setText("Bitte Karte einführen");
    sidePanel.setLabelHTML("<br><br>Bitte Karte einführen!<br><br>");
    sidePanel.setElementsVisible(false, false, true);
  }

  /**
   * Zeigt das Interface für den Login-Modus.
   */
  public void showLogin() {
    tfTop.setText("Bitte geben Sie ihre PIN ein: ");
    sidePanel.setLabelHTML("");
    sidePanel.setTfPin("");
    sidePanel.setElementsVisible(true, false, false);
  }

  /**
   * Zeigt das Interface für den Menü-Modus.
   */
  public void showMenu() {
    tfTop.setText("Willkommen am ATM, " + atm.getCurrentAccount().getUsername() + "!");
    sidePanel.setLabelHTML("Bitte wähle eine Option: <br>"
        + "1 - Kontostand anzeigen<br>"
        + "2 - Geld abheben<br>"
        + "3 - Geld einzahlen<br>"
        + "4 - Abbrechen");
    sidePanel.setElementsVisible(true, false, false);
  }

  /**
   * Zeigt das Interface für Guthaben anzeigen Modus.
   */
  public void showBalance() {
    tfTop.setText("Kontostand von " + atm.getCurrentAccount().getUsername());
    sidePanel.setLabelHTML("Verfügbares Guthaben: <br>"
        + atm.getCurrentAccount().getAvailableBalance() + " €<br><br>"
        + "Gesamtes Guthaben: <br>"
        + atm.getCurrentAccount().getTotalBalance() + " €");
    sidePanel.setElementsVisible(true, true, false);
  }

  /**
   * Zeigt das Interface für den Geld abheben Modus.
   */
  public void showWithdrawal() {
    tfTop.setText("Bitte wählen Sie einen Betrag zum Abheben.");
    sidePanel.setLabelHTML("<br>"
        + "Verfügbares Guthaben: <br>"
        + atm.getCurrentAccount().getAvailableBalance() + " €<br><br>");
    sidePanel.setElementsVisible(true, true, false);
  }

  /**
   * Zeigt das Interface für den Geld einzahlen Modus.
   */
  public void showDeposit() {
    tfTop.setText("Bitte wählen sie einen Betrag zum Einzahlen.");
    sidePanel.setLabelHTML("Verfügbares Guthaben: <br>"
        + atm.getCurrentAccount().getAvailableBalance() + " €<br>"
        + "Gesamtes Guthaben: <br>"
        + atm.getCurrentAccount().getTotalBalance() + " €");
    sidePanel.setElementsVisible(true, true, false);
  }

  public SidePanel getSidePanel() {
    return sidePanel;
  }

  public String getText() {
    return tfTop.getText();
  }
}
