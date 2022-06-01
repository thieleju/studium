package Programmiertechnik_II.Workbook6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JFrame {

  private Square s = new Square();

  private int aWidth = 400;
  private int aHeight = 400;
  private int sWidth = 150;
  private int sHeight = 150;

  public Animation() {

    setSize(new Dimension(aWidth, aHeight));
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(null);
    add(s);
    setVisible(true);
  }

  public void startAnimation(int delay) {
    try {
      while (s.getX() < aWidth) {
        changePositionByValue(1, 1);
        Thread.sleep(delay);
      }
      System.exit(0);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void changePositionByValue(int x, int y) {
    s.setBounds(s.getX() + x, s.getY() + y, sWidth, sHeight);
    s.repaint();
  }

  class Square extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.orange);
      g.fillRect(0, 0, sWidth, sHeight);
    }
  }
}
