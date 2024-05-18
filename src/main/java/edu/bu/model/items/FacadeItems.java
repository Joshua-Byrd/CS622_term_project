package edu.bu.model.items;

/**
 * Facade for the items package, providing a singleton access point to the item functionalities.
 */
public class FacadeItems {

    private static FacadeItems instance;

    // Private constructor to prevent instantiation
    private FacadeItems() {
    }

    /**
     * INTENT: To provide a global point of access to the FacadeItems singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeItems.
     *
     * @return The singleton instance of FacadeItems.
     */
    public static synchronized FacadeItems getTheInstance() {
        if (instance == null) {
            instance = new FacadeItems();
        }
        return instance;
    }

    // Armor Methods
    public Armor createArmor(String name, String description, double weight, int defenseRating) {
        return new Armor(name, description, weight, defenseRating);
    }

    public int getArmorDefenseRating(Armor armor) {
        return armor.getDefenseRating();
    }

    public void setArmorDefenseRating(Armor armor, int defenseRating) {
        armor.setDefenseRating(defenseRating);
    }

    // Weapon Methods
    public Weapon createWeapon(String name, String description, double weight, int attackRating) {
        return new Weapon(name, description, weight, attackRating);
    }

    public int getWeaponAttackRating(Weapon weapon) {
        return weapon.getAttackRating();
    }

    public void setWeaponAttackRating(Weapon weapon, int attackRating) {
        weapon.setAttackRating(attackRating);
    }
}
