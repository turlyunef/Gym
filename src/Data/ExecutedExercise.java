package Data;

import java.io.Serializable;

/**
 * Класс "Упражнение конкретного дня". Состоит из
 * -переменные класса Data.Ex
 * -вес
 * -массив подходов
 */
public class ExecutedExercise extends Ex implements Serializable {
    private int weight;
    private int[] amount;

    public ExecutedExercise() {
        int emptyArrayInt[] = {0}; // Пустышка массив количеств подходов
        this.weight = 0;
        this.amount = emptyArrayInt;
    }

    public ExecutedExercise(Ex ex) {
        super(ex.getNameEx(), ex.getDescriptionEx(), ex.getMusclesEx(), ex.getToolsEx());
        this.weight = 0;
        this.amount = new int[0];

    }

    public ExecutedExercise(int weight, int[] amount, Ex ex) {
        super(ex.getNameEx(), ex.getDescriptionEx(), ex.getMusclesEx(), ex.getToolsEx());
        this.weight = weight;
        this.amount = amount;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int[] getAmount() {
        return amount;
    }

    public void setAmount(int[] amount) {
        this.amount = amount;
    }
}
