package Programmiertechnik_II.Workbook4;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EinfacheGui implements ActionListener, KeyListener {
  private JFrame myFrame;
  private Container myContentPane;
  private JButton myButtonClickMich;

  private int height = 350;
  private int width = 350;
  private double factor = 0.3;

  public EinfacheGui() {
    myFrame = new JFrame("Einfache Gui");
    myFrame.setSize(width, height);
    myFrame.setLocationRelativeTo(null);
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    myButtonClickMich = new JButton("Button");
    myButtonClickMich.addActionListener(this);
    myButtonClickMich.addKeyListener(this);

    myFrame.add(myButtonClickMich);
    myFrame.setVisible(true);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    double w = myFrame.getWidth() - myFrame.getWidth() * factor;
    double h = myFrame.getHeight() - myFrame.getHeight() * factor;

    if ((w + h) < 150)
      myFrame.setSize(width, height);
    else
      myFrame.setSize((int) w, (int) h);

    myFrame.setLocationRelativeTo(null);
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
      JOptionPane.showMessageDialog(myFrame, "Geschafft");
    }
    System.out.println(e.getExtendedKeyCode() + " pressed");
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

}
