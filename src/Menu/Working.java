package Menu;

import Data.Day;
import Data.Days;
import Data.ExecutedExercise;
import Data.TrainingPlan;
import Gym.DataPreserving;
import Gym.Other;

import java.io.IOException;
import java.util.Scanner;

public class Working {

    public static void doTheWorkout() throws IOException {
        Scanner scanner = new Scanner(System.in); //для ввода с клавиатуры строк

        TrainingPlan trainingPlan = (TrainingPlan) TrainingPlan.getDataFromFile(); //подгрузить из файла
        Day dayNow = new Day(new ExecutedExercise()); //временный объект дня, в который будет записываться статистика выполнения тренировки
        Days days = (Days) Days.getDataFromFile();

        if (trainingPlan.getNumberOfTrainings() == 0) {
            System.out.println("Тренировок еще не создавалось. Заполните в главном меню раздел 1 ");

        } else {
            System.out.println("Выберете тренировку из списка ниже и введите ее номер для начала занятия:");
            Training.trainingPlanPrint(trainingPlan); //Выводим список названий всех доступных тренировок
            int numberTrainingNow = Integer.parseInt(scanner.nextLine()) - 1; //Ввод выбранной тренировки пользователем

            for (int i = 0; i < trainingPlan.getExerciseSet()[numberTrainingNow].ex.length; i++) { //Перебираем и попутно выполняем все упражнения выбранной тренировки
                ExecutedExercise exsNow = new ExecutedExercise(trainingPlan.getExerciseSet()[numberTrainingNow].ex[i]); //временный объект упражнения, для отправки его потом в объект дня dayNow, заполнена информация о самом упражнении, без веса и подходов


                System.out.println("Выполните упражнение № " + ((int) (i + 1)) + " \"" + trainingPlan.getExerciseSet()[numberTrainingNow].getNameEx(i) + "\"");
                Other.printArrayOfString(trainingPlan.getExerciseSet()[numberTrainingNow].ex[i].getMusclesEx(), "Мышцы");
                Other.printArrayOfString(trainingPlan.getExerciseSet()[numberTrainingNow].ex[i].getToolsEx(), "Тренажеры");
                System.out.println("   " + trainingPlan.getExerciseSet()[numberTrainingNow].getDescriptionEx(i));


                if (trainingPlan.getExerciseSet()[numberTrainingNow].ex[i].getChekExs() == 1) { //Если упражнение раньше уже выполнялось, выведем прошлый результат (вес и подходы)
                    for (int j = days.day.length - 1; j >= 0; j--) { //Цикл перебора всех дней, начиная с последнего
                        for (int k = days.day[j].exs.length - 1; k >= 0; k--) { //Цикл перебора всех упражнений дня, начиная с последнего выполненного упражнения
                            if (trainingPlan.getExerciseSet()[numberTrainingNow].ex[i].getNameEx().equals(days.day[j].exs[k].getNameEx()) == true) { //Нашлось совпадение выполняемого упражнения и выполненного ранее такого же по названию

                                System.out.print("В прошлый раз Вы делали это упражнение ");

                                days.printWeightAndAmount(j, k); //Метод вывода на экран веса и количества подходов, где j - конкретный день, k - выполненное упражнение в этот день
                                j = -1; //для завершения внешнего поиска на уровне перебора дней
                                break;//Выход из поиска на уровне перебора всех упражнений дня
                            }
                        }
                    }
                } //Конец блока вывода веса и подходов выполненных в последний раз

                System.out.println("Введите вес, с которым выполнили упражнение:");
                exsNow.setWeight(Integer.parseInt(scanner.nextLine())); //Пользователь вводит вес, с которым сделал упражнение
                exsNow.setAmount(Other.amountInput(exsNow.getAmount(), "Введите сколько раз выполнили упражнение в текущем подходе, либо введите 0, если больше подходов нет. ")); // Рекурсивный способ ввода количества раз в подходах в динамический массив

                //После выполнения упражнения, помечаем его выполненным, чтобы в следующий раз вывести инфу о прошлых весах и подходах:
                trainingPlan.getExerciseSet()[numberTrainingNow].ex[i].setCheсkExs(1);
                DataPreserving.Save(trainingPlan, TrainingPlan.getFileName());//Пересохраняем в файл планы тренировок, чтобы сохранилась отметка о выполнении этих упражнений

                //Записываем статистику (выполненное упражнение exsNow) во временный объект класса Data.Day, который содержит массив выполненных упражнений
                if (dayNow.getChekDay() == 0) {//Если упражнение первое в тренировке, используем перегруженный конструктор без перезаписи массива упражнений
                    dayNow = new Day(exsNow);
                    dayNow.setChekDay(1);
                } else
                    dayNow = new Day(dayNow, exsNow); //Если упражнение не первое в тренировке, используем перегруженный конструктор с перезаписью массива упражнений
            }
            //Записываем статистику (объект dayNow) в элемент массива дней
            if (days.getChekDays() == 0) {//Если день первый в массиве статистики, то используем перегруженный конструктор без перезаписи массива
                days = new Days(dayNow);
                days.setChekDays(1);
            } else
                days = new Days(days, dayNow); //Если упражнение не первое в тренировке, используем перегруженный конструктор с перезаписью массива упражнений

            DataPreserving.Save(days, Days.getFileName()); //Перезаписываем в файл статистику


        }
    }
}
