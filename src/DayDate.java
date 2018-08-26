import java.text.SimpleDateFormat;
import java.util.Date;

public class DayDate {
    int dd;
    int mm;
    int yy;

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
