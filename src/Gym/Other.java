package Gym;

import Data.Ex;

import java.io.IOException;
import java.util.Scanner;

public class Other {

    //Функция очистки консольного ввода с клавиатуры в буфере
    public static void clear() throws IOException {
        char ic;
        do {
            ic = (char) System.in.read();
        } while (ic != '\n');

    }

    //Функция ввода с клавиатуры и записи в динамический массив целых чисел
    public static int[] amountInput(int[] A, String Phrase) throws IOException {
        Scanner scanner = new Scanner(System.in); //для ввода с клавиатуры строк
        System.out.println(Phrase); //Выводим ключевую фразу
        int a = Integer.parseInt(scanner.nextLine());
        if (a != 0) {
            int[] B = new int[A.length]; //Вспомогательный массив прошлых введеных чисел
            for (int i = 0; i < A.length; i++) {
                B[i] = A[i];
            }
            A = new int[A.length + 1]; //пересоздаем исходный массив и увеличиваем количество элементов на 1
            for (int i = 0; i < A.length - 1; i++) { //записываем прошлые введеные значения из вспомогательного массива
                A[i] = B[i];
            }
            A[A.length - 1] = a; //присваиваем последнему элементу введенное значение
            A = amountInput(A, Phrase); //отправляем в рекурсию
        }
        return A;
    }
    //Метод для вывода на экран доступных шаблонов упражнений без проверкой на имя
    public static void exerciseSetPrint(String Phrase, Ex[] ex) {
        System.out.println(Phrase+":");
        for (int i = 0; i < ex.length; i++) {
            System.out.println((i+1) + ". " + ex[i].getNameEx()); //выводим все имена записанных в память шаблонных упражнений

            printArrayOfString(ex[i].getMusclesEx(), "Мышцы");
            printArrayOfString(ex[i].getToolsEx(), "Тренажеры");
        }
        System.out.println();
    }


    //Функция ввода с клавиатуры и записи в динамический массив целых чисел
    public static String[] stringInput(String Phrase, String[] listOfKey) throws IOException {
        Scanner scanner = new Scanner(System.in); //для ввода с клавиатуры строк
        String[] stringOut = {"Не заполнено"};
        System.out.println(Phrase); //Выводим ключевую фразу

        //Вывод на экран вариантов ключей из listOfKey:
        for (int j = 0; j < listOfKey.length; j++) {
            System.out.print((int) (j + 1) + ". " + listOfKey[j] + " ");
            if ((j + 1) % 10 == 0) System.out.println();
        }
        System.out.println();
        for (; ; ) {
            int a = Integer.parseInt(scanner.nextLine()); //Ввод с клавиатуры номера коллекции

            if (a != 0) {
                if (stringOut[0].equals("Не заполнено")) stringOut[0] = new String(listOfKey[a - 1]);
                else stringOut = addElementToMassiveString(stringOut, listOfKey[a - 1]);
            } else return stringOut;
        }
    }

    //Функция для добавления нового элемента в массив строк
    public static String[] addElementToMassiveString(String[] inputeStringMassive, String addElement) {

        String[] newStringMassive = new String[(inputeStringMassive.length) + 1];
        for (int j = 0; j < inputeStringMassive.length; j++) {
            newStringMassive[j] = inputeStringMassive[j]; //Перезаписываем все элементы из введенного через параметры массива строк в только что созданный
        }
        newStringMassive[inputeStringMassive.length] = addElement; //Вносим новую строку, введенную через
        // параметры в последний только что созданный элемент массива строк
        return newStringMassive;
    }

    public static void printArrayOfString(String[] arrayEx, String nameArrayEx) {
        System.out.print("   " + nameArrayEx + ": ");

        for (int i = 0; i < arrayEx.length; i++) {
            if ((i == (arrayEx.length - 1)) | (arrayEx.length == 1)) System.out.println(arrayEx[i] + ".");
            else
                System.out.print(arrayEx[i] + ", ");
        }
    }

    //Функция выхода в предыдущее меню нажатием клавиши Q
    public static boolean quit() throws IOException {
        System.out.println("\n1- Продолжить, 0 - Выход в предыдущее меню:");
        char entrance = (char) System.in.read(); //вводим с клавиатуры управляющий номер
        boolean a = false;
        clear();

        if (entrance == (char) '0') {
            a = true;
        }
        return a;
    }
}
