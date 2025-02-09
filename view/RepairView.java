package view;

import javax.swing.*;
import java.awt.*;

public class RepairView extends JFrame {
    private JButton repairButton;

    public RepairView() {
        setTitle("Repair Suit");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        JLabel messageLabel = new JLabel("Durability is low. Do you want to repair?", SwingConstants.CENTER);
        repairButton = new JButton("Repair (+25 Durability)");

        add(messageLabel);
        add(repairButton);
    }

    public JButton getRepairButton() {
        return repairButton;
    }
}