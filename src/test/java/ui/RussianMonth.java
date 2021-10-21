package ui;

public class RussianMonth {
    public static String getMonth(String month) {
        switch (month) {
            case "Январь":
                return "January";
            case "Февраль":
                return "February";
            case "Март":
                return "March";
            case "Апрель":
                return "April";
            case "Май":
                return "May";
            case "Июнь":
                return "June";
            case "Июль":
                return "July";
            case "Август":
                return "August";
            case "Сентябрь":
                return "September";
            case "Октябрь":
                return "October";
            case "Ноябрь":
                return "November";
            case "Декабрь":
                return "December";
            default:
                return "Month not exist";
        }
    }
}
