package ru.turlyunef.Gym;

import java.io.IOException;

public class IOFunctions {
	public static char inputNumber(){
		try {
            int entrance = (char) System.in.read(); //вводим с клавиатуры управляющий номер
            clear(); //Очищаем буфер ввода
            return (char) entrance;
            }catch (IOException exc) {
            	System.out.println("Error of input/output in main functions by class App: "+ exc.toString());
            	return '0';
            }
	}
	
	//Функция очистки консольного ввода с клавиатуры в буфере
    public static void clear() throws IOException {
        char ic;
        do {
            ic = (char) System.in.read();
        } while (ic != '\n');

    }
}
