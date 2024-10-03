package droids;

import static utilities.Color.*;

public class Scout extends Droid {
    private static final long serialVersionUID = 1L;

    public Scout(String name) {
        super(name, 50, 25, 5, 0.6);
    }

    @Override
    public String toString() {
        return "дроїд Розвідник - " + ANSI_CYAN + this.getName() + ANSI_RESET +
                "\nхп: " + ANSI_RED + this.getHp() + ANSI_RESET +
                "  урон: " + ANSI_YELLOW + this.getDamage() + ANSI_RESET +
                "  броня: " + ANSI_BLUE + this.getArmor() + ANSI_RESET +
                "  точність: " + ANSI_GREEN + this.getAccuracy() + ANSI_RESET +"\n";
    }

    @Override
    public String getType() {
        return "Розвідник - " + ANSI_CYAN + this.getName() + ANSI_RESET;
    }

    @Override
    public void resetBase() {
        this.setHp(50);
        this.setDamage(25);
    }

    @Override
    public void setBoost(int arena) {
        if (arena == 3)
            setDamage((int)(getDamage() * 1.2));
    }
}
