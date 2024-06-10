package edu.bu.model.entitities;

public class PlayerStats {
    private String playerName;
    private double totalGold;
    private int totalMonstersKilled;

    public PlayerStats(String playerName, double totalGold, int totalMonstersKilled) {
        this.playerName = playerName;
        this.totalGold = totalGold;
        this.totalMonstersKilled = totalMonstersKilled;
    }

    public String getPlayerName() {
        return playerName;
    }

    public double getTotalGold() {
        return totalGold;
    }

    public int getTotalMonstersKilled() {
        return totalMonstersKilled;
    }
}
