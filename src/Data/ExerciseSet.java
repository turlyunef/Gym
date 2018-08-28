package Data;

import Gym.DataPreserving;
import Gym.Other;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Класс шаблона упражнений одной из тренировки
 */
public class ExerciseSet implements Serializable {
    public Ex[] ex;
    private String nameOfTraining; //имя шаблона тренировки, используется для класса Data.TrainingPlan
    private int check; //переменная для проверки были ли созданы шаблонные упражнения
    private static String fileName = "data/Data.ExerciseSet.out";

    private static String[] musclesAll = {"Грудные", "Передние дельты", "Средние дельты", "Задняя дельта", "Бицепс", "Трапеции", "Трицепс", "Плечелучевая",
            "Лучевой разгибатель", "Круглый пронатор", "Зубчатая", "Косые мышцы", "Прямая мышца живота", "Квадрицпс", "Икры",
            "Передняя большеберцовая", "Камболавидная", "Широчайшая мышца спины", "Ромбовидная", "Подостная", "Трапецевидная", "Большая круглая",
            "Средняя ягодичная", "Большая ягодичная", "Бицепс бедра", "Большая приводящая", "Полуперепончатая"};

    private static String[] toolsAll = {"Штанга", "Гантели", "Гиря", "Турник", "Брусья", "Скамья Скотта", "Тренажер бицепс-машина", "Блочный тренажер",
            "Скамья для разгибаний", "Велотренажер", "Беговая дорожка", "Степпер", "Эллиптические тренажеры",
            "Тяга", "Скамья для разгибания", "Климбер", "Райдер", "Римский стул", "Тренажер «бабочка»", "Тренажер Гаккеншмидта",
            "Тренажер-платформа", "Тренажеры для икр", "Наклонная скамья", "Гимнастический ролик", "Шведская стенка с брусьями",
            "Кроссовер", "Тренажер Хаммера", "Скамья для пресса"};

    public ExerciseSet() {
        this.ex = new Ex[1];
        this.ex[0] = new Ex();
        this.check = 0;
    }

    public ExerciseSet(Ex ex) {
        this.ex = new Ex[1];
        this.ex[0] = ex;
        this.check = 0;
    }

    public ExerciseSet(ExerciseSet exerciseSet, Ex ex) {
        this.check = 1;

        this.ex = new Ex[(exerciseSet.ex.length) + 1];
        for (int j = 0; j < exerciseSet.ex.length; j++) {
            this.ex[j] = exerciseSet.ex[j]; //Перезаписываем все элементы из введенного через параметры массива шаблонных упражнений в только что созданный
        }
        this.ex[exerciseSet.ex.length] = ex; //Вносим данные шаблонного упражнения, введенного через
        // параметры в последний только что созданный элемент массива шаблонных упражнений
    }



    //Метод для создания шаблона упражнения пользователем
    public Ex create_ex() throws IOException {
        Scanner scanner = new Scanner(System.in); //для ввода с клавиатуры строк
        System.out.println("Введите название нового упражнения:");

        String name, description;
        String muscles[];
        String tools[];
        int a = 0;


        //Проверка на повтор названия упражнения с предыдущими, если были созданы
        do {
            name = scanner.nextLine();
            if (ex.length > 0) {

                for (int i = 0; i < ex.length; i++) {
                    if (!name.equals(ex[i].getNameEx())) a = 1;

                    else {
                        System.out.println("!!!Введите название заново, такое уже существует!!!");
                        a = 0;
                        break;
                    }

                }
            } else a = 1;
        } while (a == 0);
        //Конец проверки на повтор

        System.out.println("Введите описание нового упражнения:");
        description = scanner.nextLine();

        muscles = Other.stringInput("Введите номер мышцы из списка ниже, либо введите 0, если закончили заполнение. ", musclesAll);
        tools = Other.stringInput("Введите номе требуемых тренажеров или снарядов из списка, либо введите 0, если закончили заполнение. ", toolsAll);

        Ex exTemp = new Ex(name, description, muscles, tools); //временный объект класса упражнений для возврата из метода.
        return exTemp;
    }

    //Прочитать объект из файла, либо создать пустышку
    public static ExerciseSet getDataFromFile() {
        try {
            ExerciseSet value = (ExerciseSet) DataPreserving.Read(fileName);
            return value;
        } catch (ClassNotFoundException | IOException exc) {
            System.out.println("Error in the class Data.ExerciseSet, getDataFromFile() catch exception, data is Resetting");
            DataPreserving.DataReset();
        }
        return new ExerciseSet();
    }

    public String getNameEx(int index) {
        return ex[index].getNameEx();
    }

    public String getDescriptionEx(int index) {
        return ex[index].getDescriptionEx();
    }

    public String getNameOfTraining() {
        return nameOfTraining;
    }

    public void setNameOfTraining(String nameOfTraining) {
        this.nameOfTraining = nameOfTraining;
    }

    public static String getFileName() {
        return fileName;
    }

    public Ex[] getEx() {
        return ex;
    }

    public void setEx(Ex[] ex) {
        this.ex = ex;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public static void setFileName(String fileName) {
        ExerciseSet.fileName = fileName;
    }

    public static String[] getMusclesAll() {
        return musclesAll;
    }

    public static void setMusclesAll(String[] musclesAll) {
        ExerciseSet.musclesAll = musclesAll;
    }

    public static String[] getToolsAll() {
        return toolsAll;
    }

    public static void setToolsAll(String[] toolsAll) {
        ExerciseSet.toolsAll = toolsAll;
    }
}
