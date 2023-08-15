
package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import interfaces.KeypadListener;

/**
 * Die Klasse Keypad ist eine Klasse, die ein Keypad initalisiert.
 * 
 * @author Die Panzerknacker
 */
public class Keypad extends JPanel {

  private final String[] buttonValues = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Clear", "Enter" };
  private final Color backgroundColor = Color.gray;

  /**
   * Der Konstruktor der Klasse Keypad.
   * 
   * @param bl     Der KeypadListener.
   * @param width  Die Breite des Keypads.
   * @param height Die Höhe des Keypads.
   */
  public Keypad(KeypadListener bl, int width, int height) {

    setPreferredSize(new Dimension(width, height));
    setBackground(backgroundColor);
    setLayout(new FlowLayout());

    // Erstellen und Hinzufügen der Buttons und Zuweisen des Eventhandlers
    for (String value : buttonValues) {
      JButton button = new JButton(value);
      button.setFocusable(false);
      button.addActionListener(e -> bl.buttonPressed(button.getText()));
      add(button);
    }
  }
}
