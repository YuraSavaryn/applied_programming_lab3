package files;

import droids.Droid;

import java.io.*;
import java.util.ArrayList;

public class BinaryFiles {

    public static void writeDroidsToFile(ArrayList<Droid> droids, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (Droid el : droids) {
                oos.writeObject(el);
            }
            System.out.println("Дані дроїда успішно записані у файл " + filename);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Droid> readAllDroidsFromFile(String filename) {
        ArrayList<Droid> droids = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Droid droid = (Droid) ois.readObject();  // Читаємо об'єкт дроїда
                    droids.add(droid);  // Додаємо дроїда до списку
                } catch (EOFException e) {
                    // Досягнуто кінця файлу
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return droids;  // Повертаємо список дроїдів
    }
}
