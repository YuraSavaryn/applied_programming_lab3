package droids;

import static utilities.Color.*;

public class Stormtrooper extends Droid {
    private static final long serialVersionUID = 1L;

    public Stormtrooper(String name) {
        super(name, 70, 20, 5, 0.6);
    }

    @Override
    public String toString() {
        return "дроїд Штурмовик - " + ANSI_CYAN + this.getName() + ANSI_RESET +
                "\nхп: " + ANSI_RED + this.getHp() + ANSI_RESET +
                "  урон: " + ANSI_YELLOW + this.getDamage() + ANSI_RESET +
                "  броня: " + ANSI_BLUE + this.getArmor() + ANSI_RESET +
                "  точність: " + ANSI_GREEN + this.getAccuracy() + ANSI_RESET +"\n";
    }

    @Override
    public String getType() {
        return "Штурмовик - " + ANSI_CYAN + this.getName() + ANSI_RESET;
    }

    @Override
    public void resetBase() {
        this.setHp(70);
    }

    @Override
    public void setBoost(int arena) {
        if (arena == 2)
            setHp((int)(getHp() * 1.2));
    }
}
