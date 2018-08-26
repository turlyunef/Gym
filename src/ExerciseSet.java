import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Класс шаблона упражнений одной из тренировки
 */
public class ExerciseSet implements Serializable {
    Ex[] ex;
    String nameOfTraining; //имя шаблона тренировки, используется для класса TrainingPlan
    int check; //переменная для проверки были ли созданы шаблонные упражнения
    static String fileName = "data/ExerciseSet.out";

    public ExerciseSet() {
        this.ex[0] = new Ex();
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
     Ex creator_ex(String[] musclesAll, String[] toolsAll) throws IOException {
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

        muscles = Helper.stringInput("Введите номер мышцы из списка ниже, либо введите 0, если закончили заполнение. ", musclesAll);
        tools = Helper.stringInput("Введите номе требуемых тренажеров или снарядов из списка, либо введите 0, если закончили заполнение. ", toolsAll);

        Ex exTemp = new Ex(name, description, muscles, tools); //временный объект класса упражнений для возврата из метода.
        return exTemp;
    }

    //Прочитать объект из файла, либо создать пустышку
    public static ExerciseSet getDataFromFile() {
        try {
            ExerciseSet value = (ExerciseSet) DataPreserving.Read(fileName);
            return value;
        } catch (ClassNotFoundException | IOException exc) {
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

}
