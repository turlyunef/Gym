package Gym;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Класс "Статистика дней тренировок"
 * Состоит из массива объектов класса "Дней"
 */
public class Days implements Serializable {
    public Day[] day; //Одна тренировка

    private int chekDays; //Переменная для проверки, был ли записан первый день в статистику.
    public static String fileName = "data/Gym.Days.out"; //Файл хранения истории выполнения тренировок

    public int getChekDays() {
        return chekDays;
    }

    public static String getFileName() {
        return fileName;
    }


    public Days() {
        this.day = new Day[1];
        this.day[0] = new Day(); //Присваиваем единственному элементу массива дней объект выполненного дня, введенного через параметры конструктора
        this.chekDays = 0;
    }

    public Days(Day day) { //Конструктор создания первого элемента массива дней
        this.day = new Day[1];
        this.day[0] = day; //Присваиваем единственному элементу массива дней объект выполненного дня, введенного через параметры конструктора
        this.chekDays = 1;

    }

    public Days(Days days, Day day) { //Конструктор для добавления в массив дней нового элемента (конкретный выполненный день)
        // и перенос его параметров из введеного объекта День через параметры конструктора
        this.chekDays = 1;
        this.day = new Day[(days.day.length) + 1]; //Создаем новый элемент массива дней
        for (int d = 0; d < days.day.length; d++) {
            this.day[d] = days.day[d]; // Перезаписываем все значения старого массива в новый
        }
        this.day[days.day.length] = day; //Вносим данные дня в последний только что созданный элемент массива дней
        // из введенного через параметры конструктора объект выполненного конкретного дня
    }

    public static void menu_working() throws IOException{
        Scanner scanner = new Scanner(System.in); //для ввода с клавиатуры строк

        TrainingPlan trainingPlan = (TrainingPlan) TrainingPlan.getDataFromFile(); //подгрузить из файла
        Day dayNow = new Day(new ExecutedExercise()); //временный объект дня, в который будет записываться статистика выполнения тренировки
        Days days = (Days) getDataFromFile();

        if (trainingPlan.numberOfTrainings == 0) {
            System.out.println("Тренировок еще не создавалось. Заполните в главном меню раздел 1 ");

        } else {
            System.out.println("Выберете тренировку из списка ниже и введите ее номер для начала занятия:");
            trainingPlan.trainingPlanPrint(); //Выводим список названий всех доступных тренировок
            int numberTrainingNow = Integer.parseInt(scanner.nextLine()) - 1; //Ввод выбранной тренировки пользователем

            for (int i = 0; i < trainingPlan.exerciseSet[numberTrainingNow].ex.length; i++) { //Перебираем и попутно выполняем все упражнения выбранной тренировки
                ExecutedExercise exsNow = new ExecutedExercise(trainingPlan.exerciseSet[numberTrainingNow].ex[i]); //временный объект упражнения, для отправки его потом в объект дня dayNow, заполнена информация о самом упражнении, без веса и подходов


                System.out.println("Выполните упражнение № " + ((int) (i + 1)) + " \"" + trainingPlan.exerciseSet[numberTrainingNow].getNameEx(i) + "\"");
                Helper.printArrayOfString(trainingPlan.exerciseSet[numberTrainingNow].ex[i].getMusclesEx(), "Мышцы");
                Helper.printArrayOfString(trainingPlan.exerciseSet[numberTrainingNow].ex[i].getToolsEx(), "Тренажеры");
                System.out.println("   " + trainingPlan.exerciseSet[numberTrainingNow].getDescriptionEx(i));


                if (trainingPlan.exerciseSet[numberTrainingNow].ex[i].getChekExs() == 1) { //Если упражнение раньше уже выполнялось, выведем прошлый результат (вес и подходы)
                    for (int j = days.day.length - 1; j >= 0; j--) { //Цикл перебора всех дней, начиная с последнего
                        for (int k = days.day[j].exs.length - 1; k >= 0; k--) { //Цикл перебора всех упражнений дня, начиная с последнего выполненного упражнения
                            if (trainingPlan.exerciseSet[numberTrainingNow].ex[i].getNameEx().equals(days.day[j].exs[k].getNameEx()) == true) { //Нашлось совпадение выполняемого упражнения и выполненного ранее такого же по названию

                                System.out.print("В прошлый раз Вы делали это упражнение ");

                                days.printWeightAndAmount(j, k); //Метод вывода на экран веса и количества подходов, где j - конкретный день, k - выполненное упражнение в этот день
                                j = -1; //для завершения внешнего поиска на уровне перебора дней
                                break;//Выход из поиска на уровне перебора всех упражнений дня
                            }
                        }
                    }
                } //Конец блока вывода веса и подходов выполненных в последний раз

                System.out.println("Введите вес, с которым выполнили упражнение:");
                exsNow.weight = Integer.parseInt(scanner.nextLine()); //Пользователь вводит вес, с которым сделал упражнение
                exsNow.amount = Helper.amountInput(exsNow.amount, "Введите сколько раз выполнили упражнение в текущем подходе, либо введите 0, если больше подходов нет. "); // Рекурсивный способ ввода количества раз в подходах в динамический массив

                //После выполнения упражнения, помечаем его выполненным, чтобы в следующий раз вывести инфу о прошлых весах и подходах:
                trainingPlan.exerciseSet[numberTrainingNow].ex[i].setCheсkExs(1);
                DataPreserving.Save(trainingPlan, TrainingPlan.fileName);//Пересохраняем в файл планы тренировок, чтобы сохранилась отметка о выполнении этих упражнений

                //Записываем статистику (выполненное упражнение exsNow) во временный объект класса Gym.Day, который содержит массив выполненных упражнений
                if (dayNow.chekDay == 0) {//Если упражнение первое в тренировке, используем перегруженный конструктор без перезаписи массива упражнений
                    dayNow = new Day(exsNow);
                    dayNow.chekDay = 1;
                } else
                    dayNow = new Day(dayNow, exsNow); //Если упражнение не первое в тренировке, используем перегруженный конструктор с перезаписью массива упражнений
            }
            //Записываем статистику (объект dayNow) в элемент массива дней
            if (days.chekDays == 0) {//Если день первый в массиве статистики, то используем перегруженный конструктор без перезаписи массива
                days = new Days(dayNow);
                days.chekDays = 1;
            } else
                days = new Days(days, dayNow); //Если упражнение не первое в тренировке, используем перегруженный конструктор с перезаписью массива упражнений

            DataPreserving.Save(days, Days.fileName); //Перезаписываем в файл статистику


        }
    }

    public int getDayLength() { //Возвращает количество элементов массива с объектами класса Gym.Day объекта класса Gym.Days
        return this.day.length;
    }

    public Day[] getDay() {
        return day;
    }

    //Функция для вывода на экран веса и количества подходов
    public void printWeightAndAmount(int j, int k) {

        if (day[j].exs[k].weight > 0)
            System.out.print("с весом " + day[j].exs[k].weight + " кг "); //Если без веса, то строчка не выводится


        if (day[j].exs[k].amount.length > 1)
            System.out.print("с подходами по "); //Если подходов в прошлый раз было несколько, вывести фразу "с подходами по "
        else System.out.print("с одним подходом по ");

        for (int l = 0; l < day[j].exs[k].amount.length; l++) { //Цикл для вывода подходов, выполненных ранее
            if (l > 0) System.out.print(", "); //Разделитель, если подходов много
            System.out.print(day[j].exs[k].amount[l]); //Вывод подхода, выполненного ранее

        }
        int check = day[j].exs[k].amount[day[j].exs[k].amount.length - 1]; //значение количества выполненных раз упражнения в последнем подходе
        // для правильного отображения окончаний предложений при выводе подходов
        if ((check == 2) | (check == 3) | (check == 4))
            System.out.println(" раза.");
        else System.out.println(" раз.");
    }

    //Прочитать объект из файла, либо создать пустышку
    public static Days getDataFromFile() {
        try {
            Days value = (Days) DataPreserving.Read(fileName);
            return value;
        } catch (ClassNotFoundException | IOException exc) {
            System.out.println("Error in the class Gym.Days, getDataFromFile() catch exception, data is Resetting");
            DataPreserving.DataReset();
        }
        return new Days();
    }

}
