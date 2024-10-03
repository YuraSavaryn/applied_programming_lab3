package battles;

import droids.Droid;
import static utilities.Color.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TeamBattle {
    public static void teamFight(Scanner scan, Random rnd, ArrayList<Droid> army) {
        ArrayList<Droid> team1 = new ArrayList<>();
        ArrayList<Droid> team2 = new ArrayList<>();
        chooseTeams(scan, army, team1, team2);

        chooseArenas(scan, team1, team2);

        int move = rnd.nextInt(2) - 1;
        do {
            Droid droid1, droid2;
            do {
                droid1 = team1.get(rnd.nextInt(team1.size()));
                droid2 = team2.get(rnd.nextInt(team2.size()));
            } while (droid1.getHp() <= 0 || droid2.getHp() <= 0);

            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            if (move == 0) {
                attack(droid1, droid2);
                move = 1;
            }
            else {
                attack(droid2, droid1);
                move = 0;
            }
            try {
                Thread.sleep(900);
            } catch(InterruptedException ex) {}
        } while (checkTeam(team1) && checkTeam(team2));
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        System.out.println("Результат бою: ");
        if (checkTeam(team1)) {
            System.out.println("Команда 1 перемогла!");
            printTeam(team1);
        }
        else {
            System.out.println("Команда 2 перемогла!");
            printTeam(team2);
        }

        regenerateTeam(team1);
        regenerateTeam(team2);
    }

    public static void chooseTeams(Scanner scan, ArrayList<Droid> army,
                                   ArrayList<Droid> team1, ArrayList<Droid> team2) {
        System.out.println("Наявні дроїди:");
        for (int i = 0; i < army.size(); i++) {
            System.out.println("index " + i + ": " + army.get(i).getType());
        }
        System.out.println("Щоб завершити ввід дроїдів для команди введіть -1!");
        chooseDroids(scan, army, team1, 1);
        chooseDroids(scan, army, team2, 2);

        System.out.println("Команда 1: ");
        printTeam(team1);
        System.out.println("Команда 2: ");
        printTeam(team2);
    }

    public static void chooseDroids(Scanner scan, ArrayList<Droid> army, ArrayList<Droid> team, int number) {
        while (true) {
            System.out.print("Вкажіть дроїд для команди " + number + ": ");
            int index = scan.nextInt();
            if (index == -1)
                break;
            team.add(army.get(index));
        }
    }

    public static void printTeam(ArrayList<Droid> team) {
        for (Droid el : team)
            System.out.println(el.getType() + ", хп: " + el.getHp());
    }

    public static void chooseArenas(Scanner scan, ArrayList<Droid> team1, ArrayList<Droid> team2) {
        System.out.println("\n1 - Метро (ні в кого немає переваги)");
        System.out.println("2 - Місто (штурмовик +20% до хп)");
        System.out.println("3 - Ліс (розвідник +20% до урону)");
        System.out.println("4 - Поле (танкіст +20% до броні, снайпер +20% до точності)");
        System.out.print("Вкажіть місце проведення бою: ");

        int arena = scan.nextInt();

        for (Droid obj : team1) obj.setBoost(arena);
        for (Droid obj : team2) obj.setBoost(arena);
    }

    public static void attack(Droid attacker, Droid defender) {
        if (defender.getAttack(attacker.getDamage())) {
            System.out.println(attacker.getType() + " атакує --> " + defender.getType() +
                    "  Нанесений урон: " + ANSI_YELLOW +
                    attacker.imitationAttack(defender.getArmor()) + ANSI_RESET +
                    "  Хп " + defender.getName() + ": " + ANSI_RED + defender.getHp() + ANSI_RESET);
        }
        else {
            System.out.println(attacker.getType() + " промахується");
        }
    }

    public static boolean checkTeam(ArrayList<Droid> team) {
        for (Droid el : team) {
            if (0 < el.getHp())
                return true;
        }
        return false;
    }

    public static void regenerateTeam(ArrayList<Droid> team) {
        for (Droid el : team) {
            el.resetBase();
        }
    }
}