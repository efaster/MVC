package view;

import javax.swing.*;
import java.awt.*;

public class ResultView extends JFrame {
    public ResultView(String result) {
        setTitle("Result");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 1));

        JLabel resultLabel = new JLabel(result, SwingConstants.CENTER);
        add(resultLabel);
    }
}