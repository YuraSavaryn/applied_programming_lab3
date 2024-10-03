package battles;

import droids.Droid;
import static utilities.Color.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DroidBattle {
    public static void fightOneToOne(Scanner scan, Random rnd, ArrayList<Droid> army) {
        Droid[] droids = new Droid[2];
        chooseDroids(scan, army, droids);

        chooseArenas(scan, droids);

        System.out.println("\n" + droids[0].getType() + " готовий до бою!");
        System.out.println(droids[1].getType() + " готовий до бою!");
        System.out.println("Початок бою!!!");
        int move = rnd.nextInt(2) - 1;
        while (0 < droids[0].getHp() && 0 < droids[1].getHp()) {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            if (move == 0) {
                attack(droids, 0, 1);
                move = 1;
            }
            else {
                attack(droids, 1, 0);
                move = 0;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {}
        }
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        System.out.println("Результат бою: ");
        if (0 < droids[0].getHp()) {
            System.out.println(droids[0].getType() + " переміг!!!");
        }
        else {
            System.out.println(droids[1].getType() + " переміг!!!");
        }

        droids[0].resetBase();
        droids[1].resetBase();

        for (Droid el : droids)
            el.resetBase();
    }

    public static void chooseDroids(Scanner scan, ArrayList<Droid> army, Droid[] droids) {
        System.out.println("Наявні дроїди:");
        for (int i = 0; i < army.size(); i++)
            System.out.println("index " + i + ": " + army.get(i).getType());

        System.out.print("Вкажіть індекс для першого дроїда: ");
        droids[0] = army.get(scan.nextInt());
        System.out.print("Вкажіть індекс для другого дроїда: ");
        droids[1] = army.get(scan.nextInt());
    }

    public static void chooseArenas(Scanner scan, Droid[] droids) {
        System.out.println("\n1 - Метро (ні в кого немає переваги)");
        System.out.println("2 - Місто (штурмовик +20% до хп)");
        System.out.println("3 - Ліс (розвідник +20% до урону)");
        System.out.println("4 - Поле (танкіст +20% до броні, снайпер +20% до точності)");
        System.out.print("Вкажіть місце проведення бою: ");

        int arena = scan.nextInt();

        droids[0].setBoost(arena);
        droids[1].setBoost(arena);
    }

    public static void attack(Droid[] droids, int attacker, int defender) {
        if (droids[defender].getAttack(droids[attacker].getDamage())) {
            System.out.println(droids[attacker].getType() + " атакує --> " + droids[defender].getType() +
                    "  Нанесений урон: " + ANSI_YELLOW +
                    droids[attacker].imitationAttack(droids[defender].getArmor()) + ANSI_RESET +
                    "  Хп " + droids[defender].getName() + ": " + ANSI_RED + droids[defender].getHp() + ANSI_RESET);
        }
        else {
            System.out.println(droids[attacker].getType() + " промахується");
        }
    }


    public static void battleOneOnOneToFile(Scanner scan, Random rnd, ArrayList<Droid> army, String logFilename) {
        Droid[] droids = new Droid[2];
        chooseDroids(scan, army, droids);

        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilename))) {

            int move = rnd.nextInt(2) - 1;  // Виправлено, щоб move був або 0, або 1
            writer.println("\n" + droids[0].getType() + " готовий до бою!");
            writer.println(droids[1].getType() + " готовий до бою!");
            writer.println("Початок бою!!!");
            while (0 < droids[0].getHp() && 0 < droids[1].getHp()) {
                writer.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                if (move == 0) {
                    attackWithFile(writer, droids, 0, 1);
                    move = 1;
                } else {
                    attackWithFile(writer, droids, 1, 0);
                    move = 0;
                }
            }
            writer.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

            writer.println("Результат бою: ");
            if (0 < droids[0].getHp())
                writer.println(droids[0].getType() + " переміг!!!");
            else
                writer.println(droids[1].getType() + " переміг!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void attackWithFile(PrintWriter writer, Droid[] droids, int attacker, int defender) {
        if (droids[defender].getAttack(droids[attacker].getDamage())) {
            writer.println(droids[attacker].getType() + " атакує --> " + droids[defender].getType() +
                    "  Нанесений урон: " + ANSI_YELLOW +
                    droids[attacker].imitationAttack(droids[defender].getArmor()) + ANSI_RESET +
                    "  Хп " + droids[defender].getName() + ": " + ANSI_RED + droids[defender].getHp() + ANSI_RESET);
        }
        else {
            writer.println(droids[attacker].getType() + " промахується");
        }
    }

    public static void replayBattleFromFile(String logFilename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}