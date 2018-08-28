package Data;

import Gym.DataPreserving;

import java.io.IOException;
import java.io.Serializable;


/**
 * Класс "План тренировки". Состоит из:
 * - массива объектов класса "Data.ExerciseSet" для сохранения в объект класса Data.ExerciseSet одной тренировки, состоящей из массива Data.Ex[] запланированных упражнений
 * -статичной переменной количества тренировок в неделю
 */
public class TrainingPlan implements Serializable {
    ExerciseSet[] exerciseSet; //Выступает массивом разных планов тренировок
    int numberOfTrainings; //количество тренировок в неделю (количество созданных видов тренировок)
    static String fileName = "data/Data.TrainingPlan.out";

    public TrainingPlan() { //Для создания пустого объекта(для сериализации)
        this.exerciseSet = new ExerciseSet[1];
        this.exerciseSet[0] = new ExerciseSet();
        this.exerciseSet[0].setNameOfTraining(" ");
        this.numberOfTrainings = 0;
    }

    public TrainingPlan(ExerciseSet exerciseSet, String nameOfTraining) { //Для создания первого объекта
        this.exerciseSet = new ExerciseSet[1];
        this.exerciseSet[0] = exerciseSet;
        this.exerciseSet[0].setNameOfTraining(nameOfTraining);
        this.numberOfTrainings = 0;

    }


    public TrainingPlan(TrainingPlan trainingPlan, ExerciseSet exerciseSet, String nameOfTraining) {//Для дополнения существующего объекта новым элементом массива (создание нового плана тренировки)


        this.exerciseSet = new ExerciseSet[(trainingPlan.exerciseSet.length) + 1]; //Создаем новый массив шаблонов тренировок с количеством элементов на 1 больше,
        // чем у внесенного через параметры
        for (int j = 0; j < trainingPlan.exerciseSet.length; j++) {
            this.exerciseSet[j] = trainingPlan.exerciseSet[j]; //Перезаписываем все элементы из введенного через параметры массива шаблонов тренировок в только что созданный
            this.exerciseSet[j].setNameOfTraining(trainingPlan.exerciseSet[j].getNameOfTraining());
        }
        this.exerciseSet[trainingPlan.exerciseSet.length] = exerciseSet; //Вносим данные нового шаблона тренировок, введенного через
        // параметры в последний только что созданный элемент массива шаблонов тренировок
        this.exerciseSet[trainingPlan.exerciseSet.length].setNameOfTraining(nameOfTraining);
        this.numberOfTrainings += 1;
    }

    public TrainingPlan(TrainingPlan trainingPlan, int numberDel) {//Для удаления плана тренировки под номером numberDel


        this.exerciseSet = new ExerciseSet[(trainingPlan.exerciseSet.length) - 1]; //Создаем новый массив шаблонов тренировок с количеством элементов на 1 меньше,
        // чем у внесенного через параметры
        for (int j = 0; j < trainingPlan.exerciseSet.length; j++) {
            if (j == numberDel) continue;
            if (j < numberDel) {
                this.exerciseSet[j] = trainingPlan.exerciseSet[j]; //Перезаписываем все элементы из введенного через параметры массива шаблонов тренировок в только что созданный
                this.exerciseSet[j].setNameOfTraining(trainingPlan.exerciseSet[j].getNameOfTraining()); //аналогично с именами тренировок
            } else {
                this.exerciseSet[j - 1] = trainingPlan.exerciseSet[j]; //Перезаписываем все элементы из введенного через параметры массива шаблонов тренировок в только что созданный со смещением индекса
                this.exerciseSet[j - 1].setNameOfTraining(trainingPlan.exerciseSet[j].getNameOfTraining()); //аналогично с именами тренировок
            }
        }

        this.numberOfTrainings -= 1;
    }

    //Прочитать объект из файла, либо создать пустышку
    public static TrainingPlan getDataFromFile() {
        try {
            System.out.println();
            TrainingPlan value = (TrainingPlan) DataPreserving.Read(fileName);
            return value;
        } catch (ClassNotFoundException | IOException exc) {
            System.out.println("Error in the class Data.TrainingPlan, getDataFromFile() catch exception, data is Resetting");
            DataPreserving.DataReset();
        }
        return new TrainingPlan();
    }

    public ExerciseSet[] getExerciseSet() {
        return exerciseSet;
    }

    public void setExerciseSet(ExerciseSet[] exerciseSet) {
        this.exerciseSet = exerciseSet;
    }

    public int getNumberOfTrainings() {
        return numberOfTrainings;
    }

    public void setNumberOfTrainings(int numberOfTrainings) {
        this.numberOfTrainings = numberOfTrainings;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        TrainingPlan.fileName = fileName;
    }
}
