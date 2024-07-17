package Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static java.time.DayOfWeek.*;
import static java.time.temporal.TemporalAdjusters.next;

public class Chain {
    protected static LocalDate today;

    protected static List<Integer> firedWorkers;

    protected static List<Integer> branchesNums;

    protected static LocalDate[] nextWeekDates;

    protected static HRManager hrManager;

    public Chain(int id, String firstName, String lastName,int bankInfo, LocalDate date ) {

        today = date;
        firedWorkers = new ArrayList<>();
        nextWeekDates = new LocalDate[7];
        for(int i =0; i< 7; i++){
            nextWeekDates[i] = Chain.getToday().with(next(SUNDAY)).plusDays(i);
        }

        hrManager = new HRManager(id,firstName,lastName, bankInfo, 0 );
    }

    public Chain(Dictionary<String,String> data){
        this(Integer.parseInt(data.get("id")), data.get("firstName"),data.get("lastName"),
                Integer.parseInt(data.get("bankDetails")),LocalDate.of(Integer.parseInt(data.get("year")),Integer.parseInt(data.get("month")),
                        Integer.parseInt(data.get("day"))));
    }

    public static HRManager getHrManager(){
        return hrManager;
    }

    public static void creatNextWeek(){
        for(int i = 0; i< nextWeekDates.length; i++) {
            nextWeekDates[i] = nextWeekDates[i].plusDays(7);
        }
    }

    public static LocalDate[] getNextWeekDates() {
        return nextWeekDates;
    }

    public void setNextWeekDates(LocalDate[] nextWeekDates) {
        this.nextWeekDates = nextWeekDates;
    }
    public static LocalDate getToday(){
        return today;
    }

    public static int getTodayValue(){
        return (today.getDayOfWeek().getValue()%7) +1;
    }

    public static int getDayValue(DayOfWeek day){
        int dayVal = (day.getValue()%7 ) +1 ;
        return dayVal;
    }

    public static boolean tomorrow(){
        today = today.plusDays(1);
        if(today.getDayOfWeek().equals(SUNDAY)){
            creatNextWeek();
            return true;
        }
        return false;
    }


    public static DayOfWeek getDayOfWeek(int day){
        switch (day){
            case 1:
                return SUNDAY;
            case 2:
                return MONDAY;
            case 3:
                return TUESDAY;
            case 4:
                return WEDNESDAY;
            case 5:
                return THURSDAY;
            case 6:
                return FRIDAY;
            case 7:
                return SATURDAY;
            default:
                return null;
        }
    }

    public static String getDayOfWeekString(int day){
        switch (day){
            case 1:
                return "SUNDAY";
            case 2:
                return "MONDAY";
            case 3:
                return "TUESDAY";
            case 4:
                return "WEDNESDAY";
            case 5:
                return "THURSDAY";
            case 6:
                return "FRIDAY";
            case 7:
                return "SATURDAY";
            default:
                return null;
        }
    }
}