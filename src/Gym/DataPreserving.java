package Gym; /**
 * Вспомогательный класс для сохранения и чтения данных в файл методом сериализации.
 * Save - статичный метод сохранения соответствующих данных в файл
 * Read - статичный метод для чтения файла
 */

import Data.Days;
import Data.ExerciseSet;
import Data.TrainingPlan;

import java.io.*;

public class DataPreserving {


    //Cохраняет данные в файлы
    public static void Save(Object data, String fileName) {

        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName); ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
        } catch (IOException exc) {
            System.out.println("Ошибка Save " + exc.toString());
        }
    }

    //Читает данные из файлов
    public static Object Read(String fileName) throws ClassNotFoundException, IOException {

        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return objectInputStream.readObject();
    }

    //Перезаписывает все файлы хранения данных пустыми данными(первый запуск программы)
    public static void DataReset() {
        System.out.println("История и шаблоны недоступны");
        Save(new ExerciseSet(), ExerciseSet.getFileName()); // Запись пустого инстанса Data.ExerciseSet в файл
        Save(new TrainingPlan(), TrainingPlan.getFileName()); // Запись пустого инстанса Data.TrainingPlan в файл
        Save(new Days(), Days.getFileName()); // Запись пустого инстанса Data.Days в файл

    }
}
