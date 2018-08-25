import java.io.Serializable;

/**
 * Класс "Упражнение конкретного дня". Состоит из
 * -переменные класса Ex
 * -вес
 * -массив подходов
 */
public class ExecutedExercise extends Ex implements Serializable {
    int weight;
    int[] amount;
    


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
}
