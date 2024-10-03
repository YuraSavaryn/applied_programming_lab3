package droids;

import static utilities.Color.*;

public class Sniper extends Droid {
    private static final long serialVersionUID = 1L;

    public Sniper(String name) {
        super(name, 50, 30, 0, 0.8);
    }

    @Override
    public String toString() {
        return "дроїд Снайпер - " + ANSI_CYAN + this.getName() + ANSI_RESET +
                "\nхп: " + ANSI_RED + this.getHp() + ANSI_RESET +
                "  урон: " + ANSI_YELLOW + this.getDamage() + ANSI_RESET +
                "  броня: " + ANSI_BLUE + this.getArmor() + ANSI_RESET +
                "  точність: " + ANSI_GREEN + this.getAccuracy() + ANSI_RESET +"\n";
    }

    @Override
    public String getType() {
        return "Снайпер - " + ANSI_CYAN + this.getName() + ANSI_RESET;
    }

    @Override
    public void resetBase() {
        this.setHp(50);
        this.setAccuracy(0.8);
    }

    @Override
    public void setBoost(int arena) {
        if (arena == 4)
            setAccuracy(getAccuracy() * 1.2);
    }
}
