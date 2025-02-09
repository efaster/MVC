package controller;

import model.*;
import view.*;

import javax.swing.*;
import java.util.Map;

public class SuitController {
    private SuitDatabase database;
    private MainView mainView;
    private RepairView repairView;
    private ResultView resultView;

    public SuitController(SuitDatabase database, MainView mainView) {
        this.database = database;
        this.mainView = mainView;

        mainView.getCheckButton().addActionListener(e -> checkDurability());
    }

    private void checkDurability() {
        String suitId = mainView.getSuitId();
        Suit suit = database.findSuitById(suitId);

        if (suit == null) {
            mainView.showError("Invalid Suit ID or Suit not found!");
            return;
        }

        boolean isValid = switch (suit.getType()) {
            case "Power" -> PowerSuitValidator.isValid(suit.getDurability());
            case "Stealth" -> StealthSuitValidator.isValid(suit.getDurability());
            case "Disguise" -> DisguiseSuitValidator.isValid(suit.getDurability());
            default -> false;
        };

        if (!isValid) {
            repairView = new RepairView();
            repairView.setVisible(true);
            repairView.getRepairButton().addActionListener(e -> repairSuit(suit));
        } else {
            resultView = new ResultView("Durability is valid!");
            resultView.setVisible(true);
        }
    }

    private void repairSuit(Suit suit) {
        // ซ่อมชุด
        database.repairSuit(suit);
        repairView.dispose();

        // อัปเดตหน้าต่างผลลัพธ์
        resultView = new ResultView("Suit repaired! New Durability: " + suit.getDurability());
        resultView.setVisible(true);

        // นับจำนวนชุดที่ซ่อมแซมในแต่ละประเภท
        Map<String, Integer> repairCounts = database.countRepairsByType();

        // แสดงข้อความผลรวมของการซ่อมชุดในแต่ละประเภท
        StringBuilder repairSummary = new StringBuilder("Repair Summary:\n");
        repairSummary.append("Power suits repaired: ").append(repairCounts.getOrDefault("Power", 0)).append("\n");
        repairSummary.append("Stealth suits repaired: ").append(repairCounts.getOrDefault("Stealth", 0)).append("\n");
        repairSummary.append("Disguise suits repaired: ").append(repairCounts.getOrDefault("Disguise", 0)).append("\n");

        // แสดงข้อความป๊อปอัป
        JOptionPane.showMessageDialog(null, repairSummary.toString(), "Repair Summary", JOptionPane.INFORMATION_MESSAGE);
    }
}
