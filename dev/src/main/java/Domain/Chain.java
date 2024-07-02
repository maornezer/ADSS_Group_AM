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
    protected static Dictionary<Integer, SystemLimitations> branchesLimits;
    protected static LocalDate today;
    protected static List<Branch> branches;

    protected static LocalDate[] nextWeekDates;

    protected static HRManager hrManager;

    public Chain(int id, String firstName, String lastName,int bankInfo, LocalDate date ) {
        today = date;
        branches = new ArrayList<>();
        branchesLimits = new Hashtable<>();
        nextWeekDates = new LocalDate[7];
        hrManager = new HRManager(id,firstName,lastName, bankInfo, 0 );
        for(int i =0; i< 7; i++){
            nextWeekDates[i] = Chain.getToday().with(next(SUNDAY)).plusDays(i);
        }
    }

    public Chain(Dictionary<String,String> data){
        this(Integer.parseInt(data.get("id")), data.get("firstName"),data.get("lastName"),
                Integer.parseInt(data.get("bankDetails")),LocalDate.of(Integer.parseInt(data.get("year")),Integer.parseInt(data.get("month")),
                        Integer.parseInt(data.get("day"))));
    }

    public static HRManager getHrManager(){
        return hrManager;
    }

    public static List<Worker> getWorkers(int branchId){// change this!! kuku
        for (Branch b : branches){
            if(b.getBranchId() == branchId)
                return b.getWorkers();
        }
        return null;
    }

    public static void creatNextWeek(){
        for(int i = 0; i< nextWeekDates.length; i++){
            nextWeekDates[i] = nextWeekDates[i].plusDays(7);
        }
        for(Branch branch : branches){
            branch.creatNextWeek();
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

    public static int getDeadLineValue(int branchId){
        DayOfWeek deadline = branchesLimits.get(branchId).getDeadLine();
        int deadLineVal = (deadline.getValue()+1)%7;
        return deadLineVal;
    }

    public static int getDayValue(DayOfWeek day){
        int dayVal = (day.getValue()%7) + 1;
        return dayVal;
    }

    public static void tomorrow(){
        today = today.plusDays(1);
        if(today.getDayOfWeek().equals(SUNDAY)){
            creatNextWeek();
        }
    }

    public static SystemLimitations getSystemLimit(int branchId){
        return branchesLimits.get(branchId);
    } // change this!! kuku

//    public void addBranch(int branchId, String address){
//        Branch nBranch = new Branch(branchId, address);
//        branches.add(nBranch);
//        branchesLimits.put(branchId, nBranch.getSystemLimitations());
//    }

    public static void addBranch(Dictionary<String,String> data){ // change this!! kuku
        int branchId = Integer.parseInt(data.get("branchNum"));
        String address = data.get("address");
        Branch nBranch = new Branch(branchId, address);
        String day = data.get("deadLine");
        if(day != null) {
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(day);
            nBranch.setDeadLine(dayOfWeek);
        }
        branches.add(nBranch);
        branchesLimits.put(branchId, nBranch.getSystemLimitations());
    }

    public static List<Branch> getBranches() { // change this!! kuku
        return branches;
    }

    public static Branch getBranch(int branchId){ // change this!! kuku
        for (Branch branch : branches ){
            if(branch.getBranchId() == branchId)
                return branch;
        }
        return null;
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


    public static void creatScheduleForConfig(){ // change this!! kuku
        for (Branch branch:branches) {
            branch.creatNextWeek();
        }
    }



}

