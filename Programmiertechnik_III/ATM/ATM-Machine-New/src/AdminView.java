package src;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import interfaces.ATMListener.ATM_Mode;
import utilities.SpringUtilities;

/**
 * AdminView ist ein JFrame-Fenster, das sich öffnet, sobald sich ein
 * Administrator einloggt. Hier kann der Administrator neue Benutzer erstellen,
 * löschen oder bearbeiten.
 * 
 * @author Die Panzerknacker
 */
public class AdminView extends JFrame {

  private ATM atm;

  private JTextField tfTop = new JTextField(20);
  private final Font tfFont = new Font("", Font.PLAIN, 12);

  private final int width = 400;
  private final int height = 250;
  private final int listWidth = 115;
  private final int rightPanelWidth = 250;

  private JLabel lAccountNumber = new JLabel("Account-Nummer", JLabel.TRAILING);
  private JLabel lUsername = new JLabel("Benutzername", JLabel.TRAILING);
  private JLabel lAvailableBalance = new JLabel("Verfügbares Guthaben", JLabel.TRAILING);
  private JLabel lTotalBalance = new JLabel("Gesamtes Guthaben", JLabel.TRAILING);
  private JLabel lPin = new JLabel("Pin", JLabel.TRAILING);

  private JTextField tfAccountNumber = new JTextField(30);
  private JTextField tfUsername = new JTextField(30);
  private JTextField tfAvailableBalance = new JTextField(30);
  private JTextField tfTotalBalance = new JTextField(30);
  private JTextField tfPin = new JTextField(30);

  private JButton btnSave = new JButton("Speichern");
  private JButton btnCreateNew = new JButton("Neuer Account");
  private JButton btnDeleteAcc = new JButton("Account löschen");

  private JPanel rightPanel = new JPanel();

  private JList<String> list;
  private ArrayList<Account> accounts;

  /**
   * Konstruktor für AdminView. Initialisiert die UI-Elemente und setzt die
   * Listener der Buttons.
   * 
   * @param atm   Die AtM-Instanz zu der die AdminView gehört.
   * @param title Der Titel des Fensters.
   */
  public AdminView(ATM atm, String title) {
    super(title);
    this.atm = atm;
    this.accounts = atm.getBankDatabase().getAccounts();

    // Initialisierung des Top-Textfeldes und des Bottom-Textfeldes
    tfTop.setHorizontalAlignment(JTextField.CENTER);
    tfTop.setFont(tfFont);
    tfTop.setEditable(false);
    tfTop.setText("Willkommen, " + atm.getCurrentAccount().getUsername() + "!");

    // Initialisierung der Liste mit Hilfe eines DefaultListModels
    list = new JList<String>(getDefaultListModelFromAccounts(accounts));
    list.addListSelectionListener(e -> updateRightPanelWithAccount(list.getSelectedIndex()));
    list.setSelectedIndex(0);
    list.setFixedCellWidth(listWidth);

    // Initialisierung des rechten Panels
    rightPanel.setPreferredSize(new Dimension(rightPanelWidth, height));
    rightPanel.setLayout(new SpringLayout());
    lAccountNumber.setLabelFor(tfAccountNumber);
    lUsername.setLabelFor(tfUsername);
    lAvailableBalance.setLabelFor(tfAvailableBalance);
    lTotalBalance.setLabelFor(tfTotalBalance);
    lPin.setLabelFor(tfPin);

    rightPanel.add(lAccountNumber);
    rightPanel.add(tfAccountNumber);
    rightPanel.add(lUsername);
    rightPanel.add(tfUsername);
    rightPanel.add(lAvailableBalance);
    rightPanel.add(tfAvailableBalance);
    rightPanel.add(lTotalBalance);
    rightPanel.add(tfTotalBalance);
    rightPanel.add(lPin);
    rightPanel.add(tfPin);

    btnSave.setFocusable(false);
    btnSave.setHorizontalAlignment(JTextField.CENTER);
    btnSave.addActionListener(e -> btnSave());

    btnCreateNew.setFocusable(false);
    btnCreateNew.setHorizontalAlignment(JTextField.CENTER);
    btnCreateNew.addActionListener(e -> btnCreateNew());

    btnDeleteAcc.setFocusable(false);
    btnDeleteAcc.setHorizontalAlignment(JTextField.CENTER);
    btnDeleteAcc.addActionListener(e -> btnDeleteAcc());

    SpringUtilities.makeCompactGrid(rightPanel, 5, 2, 6, 6, 6, 6);

    JPanel bottomMenu = new JPanel();
    bottomMenu.setLayout(new FlowLayout());
    bottomMenu.add(btnCreateNew);
    bottomMenu.add(btnDeleteAcc);
    bottomMenu.add(btnSave);

    add(tfTop, BorderLayout.NORTH);
    add(new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.WEST);
    add(rightPanel, BorderLayout.EAST);
    add(bottomMenu, BorderLayout.SOUTH);

    // Wechsle zurück in den CARD_REQ Modus, sobald die Admin-View geschlossen wird
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        atm.atmSwitchModeAction(ATM_Mode.CARD_REQ);
      }
    });

    setLocation(atm.getScreen().getLocation());
    setPreferredSize(new Dimension(width, height));
    setResizable(false);
    pack();
    setVisible(true);
  }

  /**
   * Die Funktion wird aufgerufen, sobald Löschen betätigt wird und löscht den
   * ausgewählten Account.
   */
  public void btnDeleteAcc() {
    Account selectedAccount = accounts.get(list.getSelectedIndex());

    int reply = JOptionPane.showConfirmDialog(this,
        "Wollen Sie den Account " + selectedAccount.getUsername() + " löschen?",
        "Account löschen", JOptionPane.YES_NO_OPTION);

    if (reply == JOptionPane.YES_OPTION) {
      accounts.remove(selectedAccount);
      list.setModel(getDefaultListModelFromAccounts(accounts));

      try {
        atm.getBankDatabase().saveAccountsToFile(accounts);
        list.setSelectedIndex(accounts.size() - 1);

        JOptionPane.showMessageDialog(this, "Account wurde gelöscht!");

      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Speichern Fehlgeschlagen!");
        e.printStackTrace();
      }
    }
  }

  /**
   * Die Funktion wird aufgerufen, wenn Neu betätigt wird und erstellt einen neuen
   * Account.
   */
  public void btnCreateNew() {
    Account newAcc = new Account("Neuer Benutzer", "", "", 0, 0, false);
    accounts.add(newAcc);

    list.setModel(getDefaultListModelFromAccounts(accounts));
    list.setSelectedIndex(accounts.size() - 1);
  }

  /**
   * Die Funktion wird aufgerufen, wenn Speichern betätigt wird und speichert die
   * Änderungen des ausgewählten Accounts.
   */
  public void btnSave() {
    Account selected = accounts.get(list.getSelectedIndex());

    try {
      String username = tfUsername.getText();
      String accountNumber = tfAccountNumber.getText();
      String pin = tfPin.getText();

      if (username.equals("") || accountNumber.equals(""))
        throw new NumberFormatException("Kontonummer und Benutzername dürfen nicht leer sein!");

      if (username.length() > 15 || accountNumber.length() > 15)
        throw new NumberFormatException("Kontonummer und Benutzername dürfen nicht länger als 15 Zeichen sein!");

      if (!pin.matches("\\d{4}"))
        throw new NumberFormatException("Die Pin muss 4 Ziffern lang sein!");

      double aBalance = Double.parseDouble(tfAvailableBalance.getText());
      double tBalance = Double.parseDouble(tfTotalBalance.getText());

      if (aBalance < 0 || tBalance < 0)
        throw new NumberFormatException("Guthaben darf nicht negativ sein!");

      if (aBalance > tBalance)
        throw new NumberFormatException("Verfügbares Guthaben darf nicht größer als das gesamte Guthaben sein!");

      selected.setAccountNumber(accountNumber);
      selected.setUsername(username);
      selected.setAvailableBalance(aBalance);
      selected.setTotalBalance(tBalance);
      selected.setPin(pin);

      accounts.set(list.getSelectedIndex(), selected);
      atm.getBankDatabase().saveAccount(selected);

      JOptionPane.showMessageDialog(this, "Account wurde erfolgreich gespeichert!");

    } catch (NumberFormatException nfe) {
      JOptionPane.showMessageDialog(this, "Ungültige Eingabe!\n" + nfe.getMessage(), "Fehler",
          JOptionPane.ERROR_MESSAGE);

    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
  }

  /**
   * Die Funktion wird aufgerufen, wenn ein Account ausgewählt wird und zeigt die
   * Informationen des Accounts im rechten Fenster an.
   * 
   * @param index Index des Accounts
   */
  public void updateRightPanelWithAccount(int index) {
    // Herausfiltern von ungültigen Index-Werten von den Listen-Events
    if (index < 0 || index >= accounts.size())
      return;

    Account acc = accounts.get(index);
    tfAccountNumber.setText(acc.getAccountNumber());
    tfUsername.setText(acc.getUsername());
    tfAvailableBalance.setText(String.valueOf(acc.getAvailableBalance()));
    tfTotalBalance.setText(String.valueOf(acc.getTotalBalance()));
    tfPin.setText(acc.getPin());
  }

  /**
   * Mit dieser FUnktion wird ein DefaultListModel für die Liste erstellt.
   * 
   * @param a ArrayList mit Accounts für das Model erstellt werden soll.
   * @return DefaultListModel
   */
  public DefaultListModel<String> getDefaultListModelFromAccounts(ArrayList<Account> a) {
    DefaultListModel<String> model = new DefaultListModel<>();
    for (int i = 0; i < a.size(); i++) {
      model.add(i, " " + a.get(i).getUsername());
    }
    return model;
  }

  public ArrayList<Account> getAccounts() {
    return accounts;
  }
}