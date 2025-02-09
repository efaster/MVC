package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SuitDatabase {
    private List<Suit> suits;
    private Map<String, Integer> repairCounts;  // เพิ่มตัวแปรเก็บจำนวนการซ่อมแซมในแต่ละประเภท

    public SuitDatabase() {
        suits = new ArrayList<>();
        repairCounts = new HashMap<>();  // เริ่มต้น map นี้
        generateSampleData();
    }

    private void generateSampleData() {
        // สร้างข้อมูลตัวอย่าง 50 ชุด
        for (int i = 100000; i <= 100050; i++) {
            String id = String.format("%06d", i);
            String type = i % 3 == 0 ? "Power" : (i % 3 == 1 ? "Stealth" : "Disguise");
            int durability = (int) (Math.random() * 101);
            suits.add(new Suit(id, type, durability));
        }

        // บันทึกลงไฟล์ CSV
        saveSuitsToCSV();
    }

    public Suit findSuitById(String id) {
        return suits.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
    }

    public void repairSuit(Suit suit) {
        suit.setDurability(Math.min(suit.getDurability() + 25, 100));
        saveSuitsToCSV();  // บันทึกการซ่อมแซมลงในไฟล์ CSV

        // อัปเดตจำนวนการซ่อมแซมในประเภทชุด
        repairCounts.put(suit.getType(), repairCounts.getOrDefault(suit.getType(), 0) + 1);
    }

    public Map<String, Integer> countRepairsByType() {
        // แสดงจำนวนการซ่อมแซมที่เก็บไว้ใน repairCounts
        return new HashMap<>(repairCounts);  // ส่งคืนข้อมูลที่เก็บไว้
    }

    public void saveSuitsToCSV() {
        // ฟังก์ชันนี้จะบันทึกข้อมูลชุดลงในไฟล์ CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("suits.csv"))) {
            writer.write("ID,Type,Durability\n");
            for (Suit suit : suits) {
                writer.write(suit.getId() + "," + suit.getType() + "," + suit.getDurability() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Suit> getSuits() {
        return suits;
    }
}
