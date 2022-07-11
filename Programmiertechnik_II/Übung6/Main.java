import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

  public static void main(String[] args) {

    // 1
    JFrame myFrame = new JFrame();
    myFrame.setSize(350, 350);
    myFrame.setTitle("Mein erstes Swing-Fenster!");
    // Diese Anweisung zentriert die Anzeige des Fensters
    myFrame.setLocationRelativeTo(null);
    // Diese Anweisung schliesst das Fenster, wenn Exit betätigt wird
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton myButton = new JButton("Button");
    myButton.setToolTipText("OK");

    myFrame.add(myButton);
    myFrame.setVisible(true);

    // 2
    // Welche Listener-Schnittstelle wird hier implementiert?
    // ActionListener?

    // In welcher Zeile wird der EventHandler implementiert?
    // 6

    // Welche Eventquelle wird abgehört? Woran sieht man das im Code?
    // myButtonClickMich

    new EinfacheGui();

    // *Letzte Aufgabe*

  }
}
