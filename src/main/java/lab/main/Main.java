package lab.main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import battles.DroidBattle;
import battles.TeamBattle;
import droids.*;
import files.BinaryFiles;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rnd = new Random();

        System.out.println("\nВітаю у битві дроїдів!");
        int choose;
        ArrayList<Droid> army = new ArrayList<>();
        do {
            printMenu();
            choose = scan.nextInt();
            switch (choose) {
                case 1:
                    //create droid
                    createDroid(scan, army);
                    break;
                case 2:
                    //show all droids
                    showDroids(army);
                    break;
                case 3:
                    //fight one to one
                    DroidBattle.fightOneToOne(scan, rnd, army);
                    break;
                case 4:
                    //team fight
                    TeamBattle.teamFight(scan, rnd, army);
                    break;
                case 5:
                    //write droids in file
                    BinaryFiles.writeDroidsToFile(army, "droid_data.bin");
                    break;
                case 6:
                    //read from file all droids
                    army.addAll(BinaryFiles.readAllDroidsFromFile("droid_data.bin"));
                    break;
                case 7:
                    //write fight in file
                    DroidBattle.battleOneOnOneToFile(scan, rnd, army, "battle.txt");
                    break;
                case 8:
                    //get information about fight from file
                    DroidBattle.replayBattleFromFile("battle.txt");
                    break;
            }
        } while (choose != 0);
    }

    public static void printMenu() {
        System.out.println("\nМеню");
        System.out.println("1 - створити дроїда");
        System.out.println("2 - показати усіх дроїдів");
        System.out.println("3 - провести бій 1 на 1");
        System.out.println("4 - провести командни бій");
        System.out.println("5 - загрузити усі дроїди у файл");
        System.out.println("6 - вигрузити з файлу усіх дроїдів");
        System.out.println("7 - записати проведений бій у файл");
        System.out.println("8 - відтворити проведений бій із збереженого файлу");
        System.out.println("0 - завершити роботу програми");
        System.out.print("Введіть вибрану ваму цифру: ");
    }

    public static void createDroid(Scanner scan, ArrayList<Droid> army) {
        int choose;
        do {
            System.out.println("Типи дроїдів");
            System.out.println("1 - створити Штурмовика");
            System.out.println("2 - створити Розвідника");
            System.out.println("3 - створити Танкіста");
            System.out.println("4 - створити Снайпера");
            System.out.println("5 - повернення в меню");
            System.out.print("Виберіть дію: ");
            choose = scan.nextInt();
            scan.nextLine();
        } while (choose < 1 || 5 < choose);
        if (choose == 5) {
            System.out.println("Повернення в меню...");
        }
        else {
            System.out.print("Введіть ім'я дроїда: ");
            String name = scan.nextLine();
            switch (choose) {
                case 1:
                    army.add(new Stormtrooper(name));
                    break;
                case 2:
                    army.add(new Scout(name));
                    break;
                case 3:
                    army.add(new Tanker(name));
                    break;
                case 4:
                    army.add(new Sniper(name));
                    break;
            }
        }
    }

    public static void showDroids(ArrayList<Droid> army) {
        System.out.println("\nУсі наявні дроїди:");
        for (Droid el : army) {
            System.out.println(el);
        }
    }
}