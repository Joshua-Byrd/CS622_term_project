package edu.bu.model.entitities;

/**
 * PlayerStats represents the statistics of a player, including the total gold collected
 * and the total number of monsters killed.
 */
public class PlayerStats {
    private String playerName;
    private double totalGold;
    private int totalMonstersKilled;

    public PlayerStats(String playerName, double totalGold, int totalMonstersKilled) {
        this.playerName = playerName;
        this.totalGold = totalGold;
        this.totalMonstersKilled = totalMonstersKilled;
    }

    //Getters
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
