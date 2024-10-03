package droids;

import static utilities.Color.*;

public class Tanker extends Droid {
    private static final long serialVersionUID = 1L;

    public Tanker(String name) {
        super(name, 50, 20, 10, 0.6);
    }

    @Override
    public String toString() {
        return "дроїд Танкіст - " +ANSI_CYAN + this.getName() + ANSI_RESET +
                "\nхп: " + ANSI_RED + this.getHp() + ANSI_RESET +
                "  урон: " + ANSI_YELLOW + this.getDamage() + ANSI_RESET +
                "  броня: " + ANSI_BLUE + this.getArmor() + ANSI_RESET +
                "  точність: " + ANSI_GREEN + this.getAccuracy() + ANSI_RESET +"\n";
    }

    @Override
    public String getType() {
        return "Танкіст - " + ANSI_CYAN + this.getName() + ANSI_RESET;
    }

    @Override
    public void resetBase() {
        this.setHp(50);
        this.setArmor(10);
    }

    @Override
    public void setBoost(int arena) {
        if (arena == 4)
            setArmor((int)(getArmor() * 1.2));
    }
}
