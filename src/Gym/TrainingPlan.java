package Gym;

import Menu.Exercise;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Класс "План тренировки". Состоит из:
 * - массива объектов класса "Gym.ExerciseSet" для сохранения в объект класса Gym.ExerciseSet одной тренировки, состоящей из массива Gym.Ex[] запланированных упражнений
 * -статичной переменной количества тренировок в неделю
 */
public class TrainingPlan implements Serializable {
    ExerciseSet[] exerciseSet; //Выступает массивом разных планов тренировок
    int numberOfTrainings; //количество тренировок в неделю (количество созданных видов тренировок)
    static String fileName = "data/Gym.TrainingPlan.out";

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
    //блок редактирования плана тренировки

    public static void menu_TrainingPlanRedaction() throws IOException {
        TrainingPlan trainingPlan;
        try {
            trainingPlan = (TrainingPlan) getDataFromFile(); //подгрузить из файла
        } catch (NullPointerException exc) {
            System.out.println("Error in the class TrainigPlan in method menu_TrainingPlan, exception NullPointerException catch "+exc.toString());
            trainingPlan = new TrainingPlan();
        }
        Scanner scanner = new Scanner(System.in); //для ввода с клавиатуры строк

        for (; ; ) {


            if (trainingPlan.numberOfTrainings == 0) {
                System.out.println("Тренировок еще не создавалось. Создать новую? 1 - Да, 2 - Нет");
            } else {
                System.out.println("Созданные тренировки:");
                trainingPlan.trainingPlanPrint(); //Вывести список названий всех доступных тренировок
                System.out.println(" Создать новую? 1 - Да, 2 - Нет, 3 - Удалить тренировку");
            }

            char entrance = (char) System.in.read(); //вводим с клавиатуры управляющий номер
            Helper.clear(); //очищаем буфер
            if (entrance == (char) '1') { //Ветвь создания нового шаблона тренировки
                ExerciseSet exerciseSet = (ExerciseSet) ExerciseSet.getDataFromFile();
                if (ExerciseSet.getDataFromFile().getCheck() == 0) {
                    Exercise.menu_exercise_Redaction();
                    break;
                } else { //Заполняем план  первой тренировки через првый конструктор класса Gym.TrainingPlan
                    System.out.println("Введите название новой тренировки:");
                    String nameOfTraining = scanner.nextLine();
                    //создаем объект trainingPlan для хранения всех планов тренировок. Через первый перегруженный конструктор
                    // Заполняем первый элемент массива exerciseSet через метод creator_trainingPlan
                    if (trainingPlan.numberOfTrainings == 0) {
                        trainingPlan = new TrainingPlan(createExerciseSet(exerciseSet, nameOfTraining), nameOfTraining);
                        trainingPlan.numberOfTrainings = 1;
                    } else
                        trainingPlan = new TrainingPlan(new TrainingPlan(), createExerciseSet(exerciseSet, nameOfTraining), nameOfTraining);
                    DataPreserving.Save(trainingPlan, fileName);//Сохраняем в файл
                }
            }
            if (entrance == (char) '3') { //Ветвь удаления шаблона тренировки
                System.out.println("Введите номер тренировки, которую хотите удалить:");
                entrance = 0; //обнуляем управляющий номер
                int numberDel = Integer.parseInt(scanner.nextLine()) - 1;
                trainingPlan = new TrainingPlan(trainingPlan, numberDel); //вызвать конструктор с функцией удаления
                DataPreserving.Save(trainingPlan, fileName); //пересохранить шаблоны тренировок в файл
            }
            if (Helper.quit())
                break; //вызов метода для возврата в предыдущее меню, пользователь должен нажать Q для завершения
        }
    }

    //Метод для создания шаблона тренировки пользователем
    static ExerciseSet createExerciseSet(ExerciseSet exExample, String name1) throws IOException {
        Scanner scanner = new Scanner(System.in); //для ввода с клавиатуры строк
        String name2;

        Helper.exerciseSetPrint("Доступные упражнения", exExample.ex);
        System.out.println("Введите из этого списка номер упражнения, которое хотите добавить к тренировке под названием \"" + name1 + "\" :");
        name2 = scanner.nextLine();
        int number = Integer.parseInt(name2);
        ExerciseSet exerciseSetTemp = new ExerciseSet(exExample.ex[number - 1]); //объект для возврата текущим методом/ запись упражнений для заполняемой пользователем тренировки

        for (int i = 0; ; i++) {
            System.out.println("Введите номер следующего упражнения, либо 0 для завершения создания плана тренировки под названием \"" + name1 + "\" :");
            name2 = scanner.nextLine();
            if (name2.equals("0")) {
                break;
            } else {
                number = Integer.parseInt(name2);
                exerciseSetTemp = new ExerciseSet(exerciseSetTemp, exExample.ex[(number - 1)]); //перезаписываем возвращаемый текущим методом возвращаемый
                // объект с записанными упражнениями, добавляя новое шаблонное упражнение
            }
        }
        return exerciseSetTemp;
    }


    //Метод для вывода на экран созданных тренировок (их имен)
    public void trainingPlanPrint() {
        System.out.println("Текущие созданные тренировки:");
        for (int i = 1; i <= exerciseSet.length; i++) {
            System.out.println((i) + ". " + exerciseSet[i - 1].getNameOfTraining()); //выводим все имена записанных в память шаблонных упражнений
        }
        System.out.println();
    }

    //Прочитать объект из файла, либо создать пустышку
    public static TrainingPlan getDataFromFile() {
        try {
            System.out.println();
            TrainingPlan value = (TrainingPlan) DataPreserving.Read(fileName);
            return value;
        } catch (ClassNotFoundException | IOException exc) {
            System.out.println("Error in the class Gym.TrainingPlan, getDataFromFile() catch exception, data is Resetting");
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
