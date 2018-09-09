package ru.turlyunef.Gym;

import ru.turlyunef.core.MenuSection;
import ru.turlyunef.menu.Exercises;
import ru.turlyunef.menu.Statistics;
import ru.turlyunef.menu.Training;


public class App 
{
    public static void main( String[] args )
    {
    	char entrance; //переменная управления самым главным меню
    	MenuSection menuSection;
    	
        System.out.println("Добро пожаловать в программу тренировки \"Hot Workout\"\n");
        for (; ; ) {
            System.out.println("Введите цифру в зависимости от цели:\n\n" +
                    "1 - Раздел тренировок\n" + 
                    "2 - Раздел статистики\n" + 
                    "3 - Раздел упражнений\n" +
                    "Q - Выход из программы");

            entrance = (char) IOFunctions.inputNumber();
            switch (entrance) {

                case (char) '1': {
                    menuSection = new Training();
                }
                break;

                case (char) '2':
                	menuSection = new Statistics();
                    break;

                case (char) '3':
                	menuSection = new Exercises();
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
