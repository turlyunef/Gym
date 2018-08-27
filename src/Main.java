import java.io.IOException;
import Menu.Exercise;
import Menu.Statistics;
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
            Helper.clear(); //Очищаем буфер ввода
            switch (entrance) {
//блок плана тренировки
                case (char) '1': {
                    TrainingPlan.menu_TrainingPlanRedaction();
                }
                break;

//блок списка редактирования шаблонов упражнений
                case (char) '2':
                    Exercise.menu_exercise_Redaction();
                    break;

                case (char) '3':
                    Days.menu_working(); //Начать тренировку
                    break;

                case (char) '4':
                    Statistics.menu_watch_History(); //Посмотреть статистику
                    break;

                case (char) '5':
                    Statistics.menu_deleteHistory(); //Удалить данные
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

