import java.io.Serializable;

/**
 * Класс "Статистика дней тренировок"
 * Состоит из массива объектов класса "Дней"
 */
public class Days implements Serializable {
    Day[] day;
    int chekDays; //Переменная для проверки, был ли записан первый день в статистику.
    String fileName = "data/Days.out";

    public Days(Day day) { //Конструктор создания первого элемента массива дней
        this.day = new Day[1];
        this.day[0] = day; //Присваиваем единственному элементу массива дней объект выполненного дня, введенного через параметры конструктора
        this.chekDays = 0;

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

    public int getDayLength() { //Возвращает количество элементов массива с объектами класса Day объекта класса Days
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

}
