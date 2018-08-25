/**
 * Вспомогательный класс для сохранения и чтения данных в файл методом сериализации.
 * Save - статичный метод сохранения соответствующих данных в файл
 * Read - статичный метод для чтения файла
 */

import java.io.*;
import java.util.Scanner;

public class DataPreserving {


    //Cохраняет данные в файлы
    public static void Save(Object data, String fileName) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
        } catch (IOException exc) {
            System.out.println(exc.toString());
        }
    }
    //Читает данные из файлов

    public static Object Read(String fileName) {
        try {
            try (FileInputStream fileInputStream = new FileInputStream(fileName); ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

                return objectInputStream.readObject();
            } catch (IOException exc) {
                System.out.println(exc.toString());
            }
        } catch (ClassNotFoundException exc) {
            System.out.println(exc.toString());
        }
        return null;
    }




}
