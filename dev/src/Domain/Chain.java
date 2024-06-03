package Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;

public class Chain {
    private static Dictionary<Integer, SystemLimitations> branchesLimits;
    private static LocalDate today;
    private static List<Branch> branches;

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

    public static HRManager getHrManager(){
        return hrManager;
    }

    public static List<Worker> getWorkers(int branchId){
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
    }

    public static SystemLimitations getSystemLimit(int branchId){
        return branchesLimits.get(branchId);
    }

//    public void addBranch(int branchId, String address){
//        Branch nBranch = new Branch(branchId, address);
//        branches.add(nBranch);
//        branchesLimits.put(branchId, nBranch.getSystemLimitations());
//    }

    public void addBranch(Dictionary<String,String> data){
        int branchId = Integer.parseInt(data.get("branchNum"));
        String address = data.get("address");
        Branch nBranch = new Branch(branchId, address);
        String day = data.get("deadLine");
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day);
        nBranch.setDeadLine(dayOfWeek);
        branches.add(nBranch);
        branchesLimits.put(branchId, nBranch.getSystemLimitations());
    }

    public static List<Branch> getBranches() {
        return branches;
    }

    public static Branch getBranch(int branchId){
        for (Branch branch : branches ){
            if(branch.getBranchId() == branchId)
                return branch;
        }
        return null;
    }

}
