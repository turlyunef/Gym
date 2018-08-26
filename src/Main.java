import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in); //для ввода с клавиатуры строк

        String[] musclesAll = {"Грудные", "Передние дельты", "Средние дельты", "Задняя дельта", "Бицепс", "Трапеции", "Трицепс", "Плечелучевая",
                "Лучевой разгибатель", "Круглый пронатор", "Зубчатая", "Косые мышцы", "Прямая мышца живота", "Квадрицпс", "Икры",
                "Передняя большеберцовая", "Камболавидная", "Широчайшая мышца спины", "Ромбовидная", "Подостная", "Трапецевидная", "Большая круглая",
                "Средняя ягодичная", "Большая ягодичная", "Бицепс бедра", "Большая приводящая", "Полуперепончатая"};

        String[] toolsAll = {"Штанга", "Гантели", "Гиря", "Турник", "Брусья", "Скамья Скотта", "Тренажер бицепс-машина", "Блочный тренажер",
                "Скамья для разгибаний", "Велотренажер", "Беговая дорожка", "Степпер", "Эллиптические тренажеры",
                "Тяга", "Скамья для разгибания", "Климбер", "Райдер", "Римский стул", "Тренажер «бабочка»", "Тренажер Гаккеншмидта",
                "Тренажер-платформа", "Тренажеры для икр", "Наклонная скамья", "Гимнастический ролик", "Шведская стенка с брусьями",
                "Кроссовер", "Тренажер Хаммера", "Скамья для пресса"};


//Разные вспомогательные переменные
        char entrance; //переменная управления самым главным меню
        String nameOfTraining; //переменная для записи названий тренировок

//Переменные объектов статистики
        ExerciseSet exExample; //Объект для хранения всех шаблонов тренировки
        TrainingPlan trainingPlan; //Объект для хранения всех шаблонов тренировок
        Days days; //Объект для хранения всей статистики выполненных упражнений

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
                    for (; ; ) {

                        try {
                            trainingPlan = (TrainingPlan) TrainingPlan.getDataFromFile(); //подгрузить из файла
                        } catch (NullPointerException exc) {
                            System.out.println("Сломалось");
                            trainingPlan = new TrainingPlan();
                        }

                        if (trainingPlan.numberOfTrainings == 0) {
                            System.out.println("Тренировок еще не создавалось. Создать новую? 1 - Да, 2 - Нет");
                        } else {
                            System.out.println("Созданные тренировки:");
                            trainingPlan.trainingPlanPrint(); //Вывести список названий всех доступных тренировок
                            System.out.println(" Создать новую? 1 - Да, 2 - Нет, 3 - Удалить тренировку");
                        }

                        entrance = (char) System.in.read(); //вводим с клавиатуры управляющий номер
                        Helper.clear(); //очищаем буфер
                        if (entrance == (char) '1') { //Ветвь создания нового шаблона тренировки
                            ExerciseSet exerciseSet = (ExerciseSet) ExerciseSet.getDataFromFile();
                            if (ExerciseSet.getDataFromFile().check == 0) {
                                System.out.println("Упражнений еще не создавалось. Заполните в главном меню раздел 2");
                                break;
                            } else { //Заполняем план  первой тренировки через првый конструктор класса TrainingPlan
                                System.out.println("Введите название новой тренировки:");
                                nameOfTraining = scanner.nextLine();
                                //создаем объект trainingPlan для хранения всех планов тренировок. Через первый перегруженный конструктор
                                // Заполняем первый элемент массива exerciseSet через метод creator_trainingPlan
                                if (trainingPlan.numberOfTrainings == 0) {
                                    trainingPlan = new TrainingPlan(TrainingPlan.creator_trainingPlan(exerciseSet, nameOfTraining), nameOfTraining);
                                    trainingPlan.numberOfTrainings = 1;
                                } else
                                    trainingPlan = new TrainingPlan(new TrainingPlan(), TrainingPlan.creator_trainingPlan(exerciseSet, nameOfTraining), nameOfTraining);
                                DataPreserving.Save(trainingPlan, TrainingPlan.fileName);//Сохраняем в файл
                            }
                        }
                        if (entrance == (char) '3') { //Ветвь удаления шаблона тренировки
                            System.out.println("Введите номер тренировки, которую хотите удалить:");
                            entrance = 0; //обнуляем управляющий номер
                            int numberDel = Integer.parseInt(scanner.nextLine()) - 1;
                            trainingPlan = new TrainingPlan(trainingPlan, numberDel); //вызвать конструктор с функцией удаления
                            DataPreserving.Save(trainingPlan, TrainingPlan.fileName); //пересохранить шаблоны тренировок в файл
                        }
                        if (Helper.quit())
                            break; //вызов метода для возврата в предыдущее меню, пользователь должен нажать Q для завершения
                    }
                }
                break;

//блок списка доступных упражнений
                case (char) '2': {

                    for (; ; ) {

                        exExample = (ExerciseSet) ExerciseSet.getDataFromFile();
                        if (exExample.check == 0) System.out.println("Упражнений еще не создавалось");
                        else
                            Helper.exerciseSetPrint("Доступные упражнения", exExample.ex); //Метод вывода на экран доступных шаблнных упражнений

                        System.out.println("Создать новое упражнение? 1 - Да, 2 - Нет");
                        entrance = (char) System.in.read(); //вводим с клавиатуры управляющий номер
                        Helper.clear();//Очищаем буфер ввода

                        if (entrance == (char) '1') { //Ветвь создания нового упражнения
                            if (exExample.check == 0) { //Если упражнение первое, то используется перегруженный конструктор с аргументом вносимого упражнения

                                exExample = new ExerciseSet(exExample.creator_ex(musclesAll, toolsAll));//пользователь заполняет свободный шаблон упражнений
                                System.out.println("Создано новое упражнение под названием " + exExample.getNameEx((exExample.ex.length - 1)));
                                exExample.check = 1;

                            } else { //Если были созданы уже шаблонные упражнения, то используется конструктор с перезаписью массива
                                exExample = new ExerciseSet(exExample, exExample.creator_ex(musclesAll, toolsAll));//пользователь заполняет свободный шаблон упражнений
                                System.out.println("Создано новое упражнение под названием " + exExample.getNameEx((exExample.ex.length - 1)));
                            }
                            DataPreserving.Save(exExample, ExerciseSet.fileName);
                        }

                        if (Helper.quit())
                            break; //вызов метода для возврата в предыдущее меню, пользователь должен нажать Q
                    }

                }
                break;
//Начать тренировку
                case (char) '3': {
                    trainingPlan = (TrainingPlan) TrainingPlan.getDataFromFile(); //подгрузить из файла
                    Day dayNow = new Day(new ExecutedExercise()); //временный объект дня, в который будет записываться статистика выполнения тренировки
                    days = (Days) Days.getDataFromFile();
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

                            //Записываем статистику (выполненное упражнение exsNow) во временный объект класса Day, который содержит массив выполненных упражнений
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
                break;
//Посмотреть статистику
                case (char) '4': {
                    days = (Days) Days.getDataFromFile(); //подгрузить из файла
                    if (days.chekDays == 1) { //Проверяем есть ли статистика занятий
                        //Day[] dayTempForPrintStatistics = new Day[days.getDayLength()]; //Создали вспомогательный массив объектов day

                        ExecutedExercise[] exsTempForPrintStatistics; //Создали вспомогательный массив объектов exs

                        for (int j = 0; j < days.getDayLength(); j++) { //Перебираем дни
                            //Получаем массив exs из объекта day:
                            System.out.print("Тренировка от ");
                            System.out.printf("%02d", days.getDay()[j].dd);
                            System.out.print(".");
                            System.out.printf("%02d", days.getDay()[j].mm);
                            System.out.print(".");
                            System.out.printf("%02d", days.getDay()[j].yy);
                            System.out.println(": ");
                            for (int k = 0; k < days.getDay()[j].getExsLength(); k++) { //Перебираем все выполненныые упражнения конкретного дня
                                System.out.print((int) (k + 1));
                                System.out.println(". Название: " + days.day[j].exs[k].getNameEx());
                                Helper.printArrayOfString(days.day[j].exs[k].getMusclesEx(), "Мышцы");
                                Helper.printArrayOfString(days.day[j].exs[k].getToolsEx(), "Тренажеры");
                                System.out.print("   Выполнено ");
                                days.printWeightAndAmount(j, k); //Метод вывода на экран веса и количества подходов, где j - конкретный день, k - выполненное упражнение в этот день
                            }
                            System.out.println();
                        }

                    } else System.out.println("Статистика пустая");
                }
                break;


//Удалить данные

                case (char) '5': {
                    for (; ; ) {
                        System.out.println("Введите:\n0 - Выход в предыдущее меню;\n1 - Очистить файл всех тренировок;\n2 - Всех шаблонов упражнений\n" +
                                "3 - Статистику занятий\n4 - Удалить все данные приложения");
                        entrance = (char) System.in.read(); //вводим с клавиатуры управляющий номер
                        Helper.clear(); //Очищаем буфер ввода
                        switch (entrance) {
                            case (char) '0':
                                break; //Выход в предыдущее меню

                            case (char) '4': //удалить все файлы (выполнит case 1,2,3 ниже)
                            case (char) '1': { //очистить файл всех шаблонов тренировок
                                ExerciseSet exExampleEmpty = new ExerciseSet(new Ex()); //Пустой шаблон тренировок
                                trainingPlan = new TrainingPlan(exExampleEmpty, ""); //Пустой массив тренировок
                                DataPreserving.Save(trainingPlan, TrainingPlan.fileName); // Запись пустышки в файл
                            }
                            if (entrance == (char) '1') break;


                            case (char) '2': { //очистить файл всех шаблонов упражнений
                                exExample = new ExerciseSet(new Ex()); //пустышка
                                //MassiveTestName = new String[1]; //Пересоздаем массив названий упражнений, используемый для проверки от ввода такого же упражнения
                                DataPreserving.Save(exExample, ExerciseSet.fileName); // Запись пустышки в файл
                            }
                            if (entrance == (char) '2') break;


                            case (char) '3': { //очистить файл статистики занятий
                                Day day = new Day(new ExecutedExercise());//Заносим результат в пустой день
                                days = new Days(day); //Создаем первый массив дней, состоящий из одного дня. Заносим результат создания конкретного дня в массив дней
                                DataPreserving.Save(days, Days.fileName); // Запись пустышки в файл
                            }
                            break;
                            default: {
                                System.out.println("Некорректный ввод. Попробуйте снова!");
                            }
                        }


                        if ((entrance == (char) '0') | (entrance == (char) '4'))
                            break; // Выход из бесконечного цикла for в case 5
                    }


                }
                break;
//Конец case 5

//Выход из программы
                case (char) 'Q':
                case (char) 'q': {
                    System.out.println("До свидания.");
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

