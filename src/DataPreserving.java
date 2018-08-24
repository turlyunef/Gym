/**
 * Вспомогательный класс для сохранения и чтения данных в файл методом сериализации.
 * Save - статичный метод сохранения соответствующих данных в файл
 * Read - статичный метод для чтения файла
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.io.IOException;

public class DataPreserving {
    static private FileInputStream fileInputStreamDay, fileInputStreamExerciseSet, fileInputStreamTrainingPlan;
    static private FileOutputStream fileOutputStreamDay, fileOutputStreamExerciseSet, fileOutputStreamTrainingPlan;
    static private ObjectInputStream objectInputStreamDay, objectInputStreamExerciseSet, objectInputStreamTrainingPlan;
    static private ObjectOutputStream objectOutputStreamDay, objectOutputStreamExerciseSet, objectOutputStreamTrainingPlan;

    //Методы для сохранения данных в файлы

    static public void Save(Days days) throws IOException {
        try {
            fileOutputStreamDay = new FileOutputStream("data/Days.out");
            objectOutputStreamDay = new ObjectOutputStream(fileOutputStreamDay);
            objectOutputStreamDay.writeObject(days);
            objectOutputStreamDay.flush();
            objectOutputStreamDay.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static public void Save(ExerciseSet exerciseSet) throws IOException {
        try {
            fileOutputStreamExerciseSet = new FileOutputStream("data/ExerciseSet.out");
            objectOutputStreamExerciseSet = new ObjectOutputStream(fileOutputStreamExerciseSet);
            objectOutputStreamExerciseSet.writeObject(exerciseSet);
            objectOutputStreamExerciseSet.flush();
            objectOutputStreamExerciseSet.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    static void Save(TrainingPlan trainingPlan) throws IOException {
        try {
            fileOutputStreamTrainingPlan = new FileOutputStream("data/TrainingPlan.out");
            objectOutputStreamTrainingPlan = new ObjectOutputStream(fileOutputStreamTrainingPlan);
            objectOutputStreamTrainingPlan.writeObject(trainingPlan);
            objectOutputStreamTrainingPlan.flush();
            objectOutputStreamTrainingPlan.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Методы для чтения данных из файлов

    static Days ReadDays() throws IOException, ClassNotFoundException {
        fileInputStreamDay = new FileInputStream("data/Days.out");
        objectInputStreamDay = new ObjectInputStream(fileInputStreamDay);
        return (Days) objectInputStreamDay.readObject();
    }

    static public ExerciseSet ReadExerciseSet() throws IOException, ClassNotFoundException {

        fileInputStreamExerciseSet = new FileInputStream("data/ExerciseSet.out");
        objectInputStreamExerciseSet = new ObjectInputStream(fileInputStreamExerciseSet);
        return ((ExerciseSet) objectInputStreamExerciseSet.readObject());
    }

    static TrainingPlan ReadTrainingPlan() throws IOException, ClassNotFoundException {
        fileInputStreamTrainingPlan = new FileInputStream("data/TrainingPlan.out");
        objectInputStreamTrainingPlan = new ObjectInputStream(fileInputStreamTrainingPlan);
        return (TrainingPlan) objectInputStreamTrainingPlan.readObject();
    }


}
