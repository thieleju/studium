import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

  public Frame() {

    setTitle("Kata3");
    setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton ene = new JButton("ene");
    JButton mene = new JButton("mene");
    JButton miste = new JButton("miste");
    JButton gut = new JButton("gut");

    // schlecht
    ene.addActionListener(new EneListener());
    mene.addActionListener(new MeneListener());
    miste.addActionListener(new MisteListener());

    // gut
    gut.addActionListener(e -> {
      System.out.println("gut");
    });

    JPanel panel = new JPanel();
    panel.add(ene);
    panel.add(mene);
    panel.add(miste);
    panel.add(gut);

    add(panel);

    setVisible(true);
  }

  class EneListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("ene");
    }
  }

  class MeneListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("mene");
    }
  }

  class MisteListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("miste");
    }
  }

}
