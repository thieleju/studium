package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaces.ATMListener.ATM_Mode;

/**
 * Beinhaltet einen Ok-Knopf und einen Zurück-Knopf.
 * Besitzt ein Textfeld in welchem die Eingaben des Benutzers angezeigt werden.
 * Erbt von der Klasse JPanel.
 * 
 * @author Die Panzerknacker
 */
public class SidePanel extends JPanel {

  private final Color backgroundColor = Color.lightGray;
  private final Font tfPinFont = new Font("", Font.BOLD, 20);

  private JTextField tfPin = new JTextField(3);

  private JLabel tl = new JLabel();
  private JButton backButton = new JButton("Abbrechen");
  private JButton okButton = new JButton("OK");

  /**
   * Intialisiert das Sidepanel mit einer mitgebenen Größe.
   * 
   * @param atm    Instanz der Klasse ATM.
   * @param width  Breite des Sidepanels.
   * @param height Höhe des Sidepanels.
   */
  public SidePanel(ATM atm, int width, int height) {

    setPreferredSize(new Dimension(width, height));
    setBackground(backgroundColor);
    setLayout(new FlowLayout());

    tfPin.setEditable(false);
    tfPin.setFont(tfPinFont);
    tfPin.setHorizontalAlignment(JTextField.CENTER);

    backButton.setVisible(false);
    backButton.setFocusable(false);
    backButton.addActionListener(e -> {
      atm.atmSwitchModeAction(ATM_Mode.MENU);
      tfPin.setText("");
    });

    okButton.setVisible(false);
    okButton.setFocusable(false);
    okButton.addActionListener(e -> atm.atmSwitchModeAction(ATM_Mode.LOGIN));

    add(tfPin);
    add(tl);
    add(backButton);
    add(okButton);
  }

  /**
   * Ändert die Sichtbarkeit der Elemente des Sidepanels.
   * 
   * @param tfVisible         Sichtbarkeit des Textfeldes.
   * @param backButtonVisible Sichtbarkeit des Zurück-Knopfs.
   * @param okButtonVisible   SIchtbarkeit des Ok-Knopfs.
   */
  public void setElementsVisible(boolean tfVisible, boolean backButtonVisible, boolean okButtonVisible) {
    tfPin.setVisible(tfVisible);
    backButton.setVisible(backButtonVisible);
    okButton.setVisible(okButtonVisible);
  }

  /**
   * Fügt dem Textfeld ein Zeichen hinzu.
   * 
   * @param s Hinzugefügtes Zeichen.
   */
  public void addTextFieldChar(String s) {
    tfPin.setText(tfPin.getText() + s);
  }

  /**
   * Setzt den Inhalt des Textfeldes
   * 
   * @param text String, welcher dem Textfeld hinzugefügt wird.
   */
  public void setTfPin(String text) {
    tfPin.setText(text);
  }

  /**
   * Setzt den Text der rechten Seite.
   * 
   * @param html Mitgebener Text.
   */
  public void setLabelHTML(String html) {
    tl.setText("<html>" + html + "</html>");
  }

  public String getTextFieldText() {
    return tfPin.getText();
  }

  public String getLabel() {
    return tl.getText();
  }

}
