import java.io.Serializable;

/**
 * Класс "упражнение"
 * Минимальная ячейка- конкретное упражнение с набором неизменных параметров:
 * -название упражнения
 * -описание упражнения
 * -группа мышц
 * -требуемые тренажеры
 *
 */
public class Ex implements Serializable {

    private String nameEx; //Название упражнения
    private String descriptionEx; // Описание упражнения
    private int checkExs; //Переменная для проверки, было ли выполнено данное упражнение пользователем ранее, чтобы выводить прошлый вес и прошлый результат
    private String[] musclesEx; //группа мышц
    private String[] toolsEx; //требуемые тренажеры

    public Ex(String nameEx, String descriptionEx, String[] musclesEx, String[] toolsEx) {

        this.nameEx = nameEx;
        this.descriptionEx = descriptionEx;
        this.musclesEx = musclesEx;
        this.toolsEx = toolsEx;
        this.checkExs = 0;
    }

    public Ex() {
        String emptyArrayString[] = {""};

        this.nameEx = " ";
        this.descriptionEx = " ";
        this.musclesEx = emptyArrayString;
        this.toolsEx = emptyArrayString;
        this.checkExs = 0;
    }


    public void setCheсkExs(int cheсkExs) {
        this.checkExs = cheсkExs;
    }

    public String getNameEx() {
        return nameEx;
    }

    public String getDescriptionEx() {
        return descriptionEx;
    }

    public int getChekExs() {
        return checkExs;
    }

    public String[] getMusclesEx() {
        return musclesEx;
    }

    public String[] getToolsEx() {
        return toolsEx;
    }
}
