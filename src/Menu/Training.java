package Menu;

import Data.ExerciseSet;
import Data.TrainingPlan;
import Gym.DataPreserving;
import Gym.Other;

import java.io.IOException;
import java.util.Scanner;

public class Training {

    //блок редактирования плана тренировки

    public static void menu_TrainingPlanRedaction() throws IOException {
        TrainingPlan trainingPlan;
        try {
            trainingPlan = (TrainingPlan) TrainingPlan.getDataFromFile(); //подгрузить из файла
        } catch (NullPointerException exc) {
            System.out.println("Error in the class TrainigPlan in method menu_TrainingPlan, exception NullPointerException catch " + exc.toString());
            trainingPlan = new TrainingPlan();
        }
        Scanner scanner = new Scanner(System.in); //для ввода с клавиатуры строк

        for (; ; ) {


            if (trainingPlan.getNumberOfTrainings() == 0) {
                System.out.println("Тренировок еще не создавалось. Создать новую? 1 - Да, 2 - Нет");
            } else {
                trainingPlanPrint(trainingPlan); //Вывести список названий всех доступных тренировок
                System.out.println(" Создать новую? 1 - Да, 2 - Нет, 3 - Удалить тренировку");
            }

            char entrance = (char) System.in.read(); //вводим с клавиатуры управляющий номер
            Other.clear(); //очищаем буфер
            if (entrance == (char) '1') { //Ветвь создания нового шаблона тренировки
                ExerciseSet exerciseSet = (ExerciseSet) ExerciseSet.getDataFromFile();
                if (ExerciseSet.getDataFromFile().getCheck() == 0) Exercise.menu_exercise_Redaction();
                //Заполняем план  первой тренировки через првый конструктор класса Data.TrainingPlan
                System.out.println("Введите название новой тренировки:");
                String nameOfTraining = scanner.nextLine();
                //создаем объект trainingPlan для хранения всех планов тренировок. Через первый перегруженный конструктор
                // Заполняем первый элемент массива exerciseSet через метод creator_trainingPlan
                if (trainingPlan.getNumberOfTrainings() == 0) {
                    trainingPlan = new TrainingPlan(createExerciseSet(exerciseSet, nameOfTraining), nameOfTraining);
                    trainingPlan.setNumberOfTrainings(1);
                } else
                    trainingPlan = new TrainingPlan(new TrainingPlan(), createExerciseSet(exerciseSet, nameOfTraining), nameOfTraining);
                DataPreserving.Save(trainingPlan, TrainingPlan.getFileName());//Сохраняем в файл

            }
            if (entrance == (char) '3') { //Ветвь удаления шаблона тренировки
                System.out.println("Введите номер тренировки, которую хотите удалить:");
                int numberDel = Integer.parseInt(scanner.nextLine()) - 1;
                trainingPlan = new TrainingPlan(trainingPlan, numberDel); //вызвать конструктор с функцией удаления
                DataPreserving.Save(trainingPlan, TrainingPlan.getFileName()); //пересохранить шаблоны тренировок в файл
            }
            if (Other.quit())
                break; //вызов метода для возврата в предыдущее меню, пользователь должен нажать Q для завершения
        }
    }

    //Метод для создания шаблона тренировки пользователем
    static ExerciseSet createExerciseSet(ExerciseSet exExample, String name1) throws IOException {
        Scanner scanner = new Scanner(System.in); //для ввода с клавиатуры строк
        String name2;

        Other.exerciseSetPrint("Доступные упражнения", exExample.ex);
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
    public static void trainingPlanPrint(TrainingPlan value) {
        System.out.println("Текущие созданные тренировки:");
        for (int i = 1; i <= value.getExerciseSet().length; i++) {
            System.out.println((i) + ". " + value.getExerciseSet()[i - 1].getNameOfTraining()); //выводим все имена записанных в память шаблонных упражнений
        }
        System.out.println();
    }
}
