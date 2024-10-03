package droids;

import java.io.Serializable;

abstract public class Droid implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int hp;
    private int damage;
    private int armor;
    private double accuracy;

    public Droid(String name, int hp, int damage, int armor, double accuracy) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.armor = armor;
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getArmor() {
        return armor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean getAttack(int damage) {
        if (Math.random() <= getAccuracy()) {
            setHp(getHp() - damage + getArmor());
            return true;
        }
        return false;
    }

    public int imitationAttack(int armor) {
        return getDamage() - armor;
    }

    public abstract String getType();

    public abstract void resetBase();
    public abstract void setBoost(int arena);
}
