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

//    protected static BranchesRepository branchesRepository;

//    protected static ShiftHistoryRepository shiftHistoryRepository;

    protected static List<Integer> firedWorkers;

//    protected static WorkersRepository workersRepository;

    protected static List<Integer> branchesNums;

    protected static LocalDate[] nextWeekDates;

    protected static HRManager hrManager;

    public Chain(int id, String firstName, String lastName,int bankInfo, LocalDate date ) {
        today = date;
//        branches = new ArrayList<>();
//        branchesLimits = new Hashtable<>();
//        branchesRepository = new BranchesRepository();

//        workersRepository = new WorkersRepository();
//        shiftHistoryRepository = new ShiftHistoryRepository();

//        branchesNums = new ArrayList<>();
//        branchesNums.addAll(branchesRepository.getBranchesNums());

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

//    public static int getDeadLineValue(int branchId){
//        DayOfWeek deadline = getSystemLimit(branchId).getDeadLine();
//        int deadLineVal = (deadline.getValue()+1)%7;
//        return deadLineVal;
//    }

    public static int getDayValue(DayOfWeek day){
        int dayVal = (day.getValue()%7 ) +1 ;
        return dayVal;
    }

//    public static ShiftHistoryRepository getShiftHistoryRepository() {
//        return shiftHistoryRepository;
//    }

    public static boolean tomorrow(){
        today = today.plusDays(1);
        if(today.getDayOfWeek().equals(SUNDAY)){
            creatNextWeek();
            return true;
        }
        return false;
    }

//    public static SystemLimitations getSystemLimit(int branchId){
//        return branchesRepository.getBranch(branchId).getSystemLimitations();
//    }

//    public void addBranch(int branchId, String address){
//        Dictionary<String, String> data = new Hashtable<>();
//        data.put("branchId", Integer.toString(branchId));
//        data.put("address", address);
//        data.put("deadline", "THURSDAY");
//        branchesNums.add(branchId);
//
//        branchesRepository.createBranch(data);
//    }

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

//    public static boolean ShiftsAssignment(TransportRepository transportRepository){
//
//        boolean flag = true;
//
//        for (int branchNum : branchesNums) {
//            flag = flag & branchesRepository.getBranch(branchNum).checkBranchDeadLinePassed();
//        }
//
//        if(!flag)
//            return false;
//
//        for(int worker: firedWorkers){
//            workersRepository.deleteWorker(worker);
//        }
//        firedWorkers.clear();
//
//        Dictionary<LocalDate, List<String[]>> transports = transportRepository.getTransportDetails();
//
//        for(LocalDate date : nextWeekDates){
//            List<String[]> temp = transports.get(date);
//            for(String[] transport : temp){
//                String driverName = transport[1];
//                int branchNum = Integer.parseInt(transport[2]);
//                int shiftNum = Integer.parseInt(transport[6]);
//                Shift shift = branchesRepository.getBranch(branchNum).getShiftNextWeek(date.getDayOfWeek(), shiftNum);
//                shift.transport(driverName);
//            }
//        }
//        for (int branchNum : branchesNums)
//            branchesRepository.getBranch(branchNum).makeASchedule();
//
//        return true;
//    }

//    public static BranchesRepository getBranchesRepository() {
//        return branchesRepository;
//    }

//    public static WorkersRepository getWorkersRepository() {
//        return workersRepository;
//    }

//    public static void updateWorker(Dictionary<String, String> data){
//        workersRepository.updateWorker(data);
//    }

//    public static boolean updateBranch(Dictionary<String, String> data){
//        return branchesRepository.updateBranch(data);
//    }
//
//
//    public static void fireWorker(int id){
//
//    }
}