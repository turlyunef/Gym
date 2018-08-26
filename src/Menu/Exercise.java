package Menu;

import java.io.IOException;


public class Exercise {
    //Редактировать упражнения
    public static void menu_exercise_Redaction() throws IOException {

        ExerciseSet exExample = (ExerciseSet) getDataFromFile();
        for (; ; ) {


            if (exExample.check == 0) System.out.println("Упражнений еще не создавалось");
            else
                Helper.exerciseSetPrint("Доступные упражнения", exExample.ex); //Метод вывода на экран доступных шаблнных упражнений

            System.out.println("Создать новое упражнение? 1 - Да, 2 - Нет");
            char entrance = (char) System.in.read(); //вводим с клавиатуры управляющий номер
            Helper.clear();//Очищаем буфер ввода

            if (entrance == (char) '1') { //Ветвь создания нового упражнения
                if (exExample.check == 0) { //Если упражнение первое, то используется перегруженный конструктор с аргументом вносимого упражнения

                    exExample = new ExerciseSet(exExample.create_ex(musclesAll, toolsAll));//пользователь заполняет свободный шаблон упражнений
                    System.out.println("Создано новое упражнение под названием " + exExample.getNameEx((exExample.ex.length - 1)));
                    exExample.check = 1;

                } else { //Если были созданы уже шаблонные упражнения, то используется конструктор с перезаписью массива
                    exExample = new ExerciseSet(exExample, exExample.create_ex(musclesAll, toolsAll));//пользователь заполняет свободный шаблон упражнений
                    System.out.println("Создано новое упражнение под названием " + exExample.getNameEx((exExample.ex.length - 1)));
                }
                DataPreserving.Save(exExample, ExerciseSet.fileName);
            }

            if (Helper.quit())
                break; //вызов метода для возврата в предыдущее меню, пользователь должен нажать Q
        }
    }

}
