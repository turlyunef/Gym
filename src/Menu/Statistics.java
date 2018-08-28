package Menu;
import Data.*;
import Gym.*;

import java.io.IOException;

public class Statistics {
    public static void menu_watch_History() {
        Days days = (Days) Days.getDataFromFile(); //подгрузить из файла
        if (days.getChekDays() == 1) { //Проверяем есть ли статистика занятий

            ExecutedExercise[] exsTempForPrintStatistics; //Создали вспомогательный массив объектов exs

            for (int j = 0; j < days.getDayLength(); j++) { //Перебираем дни
                //Получаем массив exs из объекта day:
                System.out.print("Тренировка от ");
                System.out.printf("%02d", days.getDay()[j].getDd());
                System.out.print(".");
                System.out.printf("%02d", days.getDay()[j].getMm());
                System.out.print(".");
                System.out.printf("%02d", days.getDay()[j].getYy());
                System.out.println(": ");
                for (int k = 0; k < days.getDay()[j].getExsLength(); k++) { //Перебираем все выполненныые упражнения конкретного дня
                    System.out.print((int) (k + 1));
                    System.out.println(". Название: " + days.day[j].exs[k].getNameEx());
                    Other.printArrayOfString(days.day[j].exs[k].getMusclesEx(), "Мышцы");
                    Other.printArrayOfString(days.day[j].exs[k].getToolsEx(), "Тренажеры");
                    System.out.print("   Выполнено ");
                    days.printWeightAndAmount(j, k); //Метод вывода на экран веса и количества подходов, где j - конкретный день, k - выполненное упражнение в этот день
                }
                System.out.println();
            }

        } else System.out.println("История тренировок пустая");
    }

    public static void menu_deleteHistory() throws IOException {
        for (; ; ) {
            System.out.println("Введите:\n0 - Выход в предыдущее меню;\n1 - Очистить файл всех тренировок;\n2 - Всех шаблонов упражнений\n" +
                    "3 - Статистику занятий\n4 - Удалить все данные приложения");
            char entrance = (char) System.in.read(); //вводим с клавиатуры управляющий номер
            Other.clear(); //Очищаем буфер ввода
            switch (entrance) {
                case (char) '0':
                    break; //Выход в предыдущее меню

                case (char) '4': //удалить все файлы (выполнит case 1,2,3 ниже)
                case (char) '1': { //очистить файл всех шаблонов тренировок
                    ExerciseSet exExampleEmpty = new ExerciseSet(new Ex()); //Пустой шаблон тренировок
                    TrainingPlan trainingPlan = new TrainingPlan(exExampleEmpty, ""); //Пустой массив тренировок
                    DataPreserving.Save(trainingPlan, TrainingPlan.getFileName()); // Запись пустышки в файл
                }
                if (entrance == (char) '1') break;


                case (char) '2': { //очистить файл всех шаблонов упражнений
                    DataPreserving.Save(new ExerciseSet(new Ex()), ExerciseSet.getFileName()); // Запись пустышки в файл
                }
                if (entrance == (char) '2') break;


                case (char) '3': { //очистить файл статистики занятий
                    Day day = new Day(new ExecutedExercise());//Заносим результат в пустой день
                    DataPreserving.Save(new Days(day), Days.fileName); // Запись пустышки в файл
                }
                break;
                default: {
                    System.out.println("Некорректный ввод. Попробуйте снова!");
                }
            }


            if ((entrance == (char) '0') | (entrance == (char) '4'))
                break; // Выход из бесконечного цикла for
        }
    }
}
