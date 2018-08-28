import java.io.IOException;

import Gym.*;

public class Main {

    public static void main(String[] args) throws IOException {

        char entrance; //переменная управления самым главным меню

//Меню навигации по программе
        System.out.println("Добро пожаловать в программу тренировки \"Hot Workout\"\n");
        for (; ; ) {
            System.out.println("Введите цифру в зависимости от цели:\n\n" +
                    "1 - Редактирование тренировок\n" + //Добавить в это меню план на сегодня и план на интересующий день
                    "2 - Редактирование упражнений\n" + //В этом меню вставить создание и редактирование упражнения после вывода текущих
                    "3 - Начать тренировку\n" +
                    "4 - Посмотреть статистику\n" + //Добавить в это меню вариации статистики
                    "5 - Удалить данные\n" +
                    "Q - Выход из программы");

            entrance = (char) System.in.read(); //вводим с клавиатуры управляющий номер
            Other.clear(); //Очищаем буфер ввода
            switch (entrance) {
//блок плана тренировки
                case (char) '1': {
                    Menu.Training.TrainingRedaction();
                }
                break;

                case (char) '2':
                    Menu.Exercise.ExerciseRedaction(); //редактировать шаблоны упражнений
                    break;

                case (char) '3':
                    Menu.Working.doTheWorkout(); //Начать тренировку
                    break;

                case (char) '4':
                    Menu.Statistics.watch_History(); //Посмотреть статистику
                    break;

                case (char) '5':
                    Menu.Statistics.deleteHistory(); //Удалить данные
                    break;

//Выход из программы
                case (char) 'Q':
                case (char) 'q': {
                    System.out.println("Завершение работы с программой.");
                }
                break;

                default: {
                    System.out.println("Некорректный ввод. Попробуйте снова!");
                }
            }
            if ((entrance == 'Q') | (entrance == 'q')) break;
        }
    }

}

