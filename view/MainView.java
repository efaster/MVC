package view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private JTextField suitIdField;
    private JButton checkButton;

    public MainView() {
        setTitle("Superhero Suit Checker");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JLabel titleLabel = new JLabel("Enter Suit ID:", SwingConstants.CENTER);
        suitIdField = new JTextField();
        checkButton = new JButton("Check Durability");

        add(titleLabel);
        add(suitIdField);
        add(checkButton);
    }

    public String getSuitId() {
        return suitIdField.getText();
    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}