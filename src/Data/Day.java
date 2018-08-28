package Data;

import Gym.DayDate;

import java.io.Serializable;

/**
 * Класс "День". Состоит из:
 * - массива объектов класса "Упражнение конкретного дня"
 * -переменных даты
 */
public class Day extends DayDate implements Serializable {
    public ExecutedExercise[] exs;
    int chekDay; //Переменная для проверки, было ли выполнено упражнение пользователем в текущий день, чтобы заполнить первый элемент

    public Day() {
        this.exs = new ExecutedExercise[1];
        this.exs[0] = new ExecutedExercise();
        this.chekDay = 0;
    }

    public Day(ExecutedExercise exs) {
        this.exs = new ExecutedExercise[1];
        this.exs[0] = exs;
        this.chekDay = 0;
    }

    public Day(Day day, ExecutedExercise exs) {
        this.setDd(day.getDd());
        this.setMm(day.getMm());
        this.setYy(day.getYy());
        this.chekDay = 1;

        this.exs = new ExecutedExercise[(day.exs.length) + 1]; //Создаем новый массив упражнений конкретного дня с количеством элементов на 1 больше, чем у внесенного через параметры
        for (int j = 0; j < day.exs.length; j++) {
            this.exs[j] = day.exs[j]; //Перезаписываем все элементы из введенного через параметры массива упражнений конкретного дня в только что созданный
        }
        this.exs[day.exs.length] = exs;
    }



    public int getExsLength() { //Возвращает количество элементов массива с объектами класса Exs объекта класса Data.Day
        return this.exs.length;
    }


}
