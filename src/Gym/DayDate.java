package Gym;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayDate {
    private int dd;
    private int mm;
    private int yy;

    public int getDd() {
        return dd;
    }

    public int getMm() {
        return mm;
    }

    public int getYy() {
        return yy;
    }

    public void setDd(int dd) {
        this.dd = dd;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }

    public DayDate() {
        Date dateNow = new Date(); //Объект с информацией о дате выполнения тренировки
        SimpleDateFormat formatDateYearNow = new SimpleDateFormat("yy"); //Объект формата для форматирования date для отделения года
        SimpleDateFormat formatDateMountNow = new SimpleDateFormat("MM"); //Объект формата для форматирования date для отделения месяца
        SimpleDateFormat formatDateDayNow = new SimpleDateFormat("dd"); //Объект формата для форматирования date для отделения дня

        this.dd = Integer.parseInt(formatDateDayNow.format(dateNow)); //день
        this.mm = Integer.parseInt(formatDateMountNow.format(dateNow)); // месяц
        this.yy = Integer.parseInt(formatDateYearNow.format(dateNow)); // год
    }
}
