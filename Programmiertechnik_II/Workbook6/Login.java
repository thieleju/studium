package Programmiertechnik_II.Workbook6;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Login implements KeyListener {
    private JFrame frame;
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameTF;
    private JPasswordField passwordField;
    private JButton okButton, resetButton;
    private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 24);
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    private Color textColor = new Color(60, 60, 60);
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";

    public Login() {
        frame = new JFrame();
        frame.setSize(466, 354);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(233, 233, 233));
        frame.setResizable(false);
        frame.getRootPane().setBorder(border);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(this);

        usernameLabel = new JLabel("Benutzername");
        usernameLabel.setBounds(20, 51, 160, 50);
        usernameLabel.setFont(font);
        usernameLabel.setForeground(textColor);

        usernameTF = new JTextField();
        usernameTF.setBounds(200, 47, 200, 50);
        usernameTF.setFont(font);
        usernameTF.setBorder(border);

        passwordLabel = new JLabel("Passwort");
        passwordLabel.setBounds(20, 130, 100, 50);
        passwordLabel.setFont(font);
        passwordLabel.setForeground(textColor);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 126, 200, 50);
        passwordField.setFont(font);
        passwordField.setBorder(border);
        passwordField.addKeyListener(this);

        okButton = new JButton("OK");
        okButton.setBounds(20, 248, 200, 52);
        okButton.setBackground(Color.WHITE);
        okButton.setFont(font);
        okButton.setForeground(textColor);
        okButton.setBorder(border);
        okButton.setFocusable(false);
        okButton.addActionListener(e -> validateLogin());

        resetButton = new JButton("Zurücksetzen");
        resetButton.setBounds(230, 248, 200, 52);
        resetButton.setBackground(Color.WHITE);
        resetButton.setFont(font);
        resetButton.setForeground(textColor);
        resetButton.setBorder(border);
        resetButton.setFocusable(false);
        resetButton.addActionListener(e -> resetInput());

        frame.add(usernameLabel);
        frame.add(usernameTF);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(okButton);
        frame.add(resetButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    public void resetInput() {
        usernameTF.setText("");
        passwordField.setText("");
    }

    public void validateLogin() {
        String tempPassword = String.valueOf(passwordField.getPassword());

        if (usernameTF.getText().equals(USERNAME) && tempPassword.equals(PASSWORD)) {
            System.out.println("Logged in!");
        } else {
            System.out.println("Wrong username or password. Try again.");
        }

        resetInput();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            validateLogin();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

