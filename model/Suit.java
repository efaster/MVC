package model;

public class Suit {
    private String id;
    private String type;
    private int durability;

    public Suit(String id, String type, int durability) {
        this.id = id;
        this.type = type;
        this.durability = durability;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getType() { return type; }
    public int getDurability() { return durability; }
    public void setDurability(int durability) { this.durability = durability; }
}