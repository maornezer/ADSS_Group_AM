package Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Branch {
    private int branchNum;
    private String address;
    private SystemLimitations systemLimitations;
    private String phoneNumber;
    private String contactName;
    private String zone;


    private Scheduling scheduleNextWeek;

    private String scheduleThisWeek;

    private int weekCounter;

//    private List<String> branchHistory;

    public Scheduling getScheduleNextWeek() {
        return scheduleNextWeek;
    }

    public Branch(int branchId, String address,String zone ,String contactName, String phoneNumber) {
        this.branchNum = branchId;
        this.address = address;
//        this.workers = new ArrayList<Worker>();
//        this.firedWorkers = new ArrayList<Worker>();
        this.systemLimitations = new SystemLimitations(branchId);
        this.scheduleNextWeek = new Scheduling(branchId, this.systemLimitations);
        this.scheduleThisWeek = "No shifts yet";
        this.weekCounter = 0;
//        this.branchHistory = new ArrayList<>();
    }

    public Branch(Dictionary<String,String> data) {
        this(Integer.parseInt(data.get("branchNum")),  data.get("address"),data.get("zone"),data.get("contactName"),data.get("phoneNumber"));
        this.systemLimitations.setDeadLine(DayOfWeek.valueOf(data.get("deadline")));
    }

    public List<Worker> getWorkers() {
        return Chain.getWorkersRepository().getWorkersOfBranch(branchNum);
    }

    public int getBranchNum() {
        return branchNum;
    }

    public String getAddress() {
        return address;
    }

    public Worker addWorker(Dictionary<String, String> data){
        return Chain.getWorkersRepository().createWorker(data);
    }

    public LocalDate removeWorker(Dictionary<String, String> data){
        LocalDate date = null;

        Worker worker = Chain.getWorkersRepository().getWorker(Integer.parseInt(data.get("id")));
        worker.setEmploymentEnd(Chain.getNextWeekDates()[0]);

        Chain.fireWorker(Integer.parseInt(data.get("id")));

        date = Chain.getNextWeekDates()[0];

        return date;
    }

    public SystemLimitations getSystemLimitations() {
        return systemLimitations;
    }

    public DayOfWeek getDeadLine() {
        return this.systemLimitations.getDeadLine();
    }

    public void setDeadLine(DayOfWeek deadLine) {
        this.systemLimitations.setDeadLine(deadLine);
    }

    public List<genShift> getDaysOffConst() {
        return this.systemLimitations.getShiftsOffConst();
    }

    public void addShiftOffConst(genShift shift) {
        this.systemLimitations.addShiftOffConst(shift);
        Shift[][] shifts = this.scheduleNextWeek.getSchedule();
        shifts[Chain.getDayValue(shift.getDay())-1][shift.getShiftType()-1] = null;
    }

    public List<genShift> getDaysOffTemp() {
        return this.systemLimitations.getShiftsOffTemp();
    }

    public void creatNextWeek(){

        this.weekCounter++;

        addToHistory();

        this.scheduleNextWeek = new Scheduling(this.branchNum);
        this.scheduleNextWeek.creatSchedule();

        List<Worker> workers = getWorkers();

        for(Worker worker : workers){
            worker.DefaultNextWeek();
        }
    }

    public void addToHistory(){
        Shift[][] shifts = this.scheduleNextWeek.getSchedule();

        Dictionary<String, String> data = new Hashtable<>();
        data.put("branchNum", Integer.toString(branchNum));
        data.put("weekCounter", Integer.toString(this.weekCounter));


        for(int i = 0; i < 7; i++){
            for (int j = 0; j< 2; j++){
                Shift shift = shifts[i][j];
                if(shift != null) {
                    data.put("shiftString" + Integer.toString(i), shift.toString());
                }
            }
        }

        Chain.getShiftHistoryRepository().create(data);
    }

    public String getScheduleThisWeek() {
        return scheduleThisWeek;
    }


    public void addShiftOffTemp(genShift shift) {
        this.systemLimitations.addShiftOffTemp(shift);
        Shift[][] shifts = this.scheduleNextWeek.getSchedule();
        shifts[Chain.getDayValue(shift.getDay()) - 1][shift.getShiftType() - 1] = null;
    }

    public void removeShiftOffTemp(genShift shift){
        this.systemLimitations.removeShiftOffTemp(shift);
        Shift[][] shifts = this.scheduleNextWeek.getSchedule();
        LocalDate date = Chain.getNextWeekDates()[Chain.getDayValue(shift.getDay())-1];
        shifts[Chain.getDayValue(shift.getDay())-1][shift.getShiftType()-1] = new Shift(date, shift.getShiftType());
    }

    public Shift getShiftNextWeek(DayOfWeek day, int shiftType){
        return this.scheduleNextWeek.getShift(day,shiftType);
    }

    public void ChangeAmountTypeOfWorkersShift(Dictionary<String,String> data){
        DayOfWeek dayOfWeek = Chain.getDayOfWeek(Integer.parseInt(data.get("day")));
        int shift = Integer.parseInt(data.get("shift"));
        Shift temp = getShiftNextWeek(dayOfWeek , shift);
        if(temp == null)
            return;
        temp.setRoles(data);
    }

    public boolean changeStartAndEndTime(Dictionary<String, String> data){
        DayOfWeek dayOfWeek =Chain.getDayOfWeek(Integer.parseInt(data.get("day")));
        int shift = Integer.parseInt(data.get("shift"));
        int start = Integer.parseInt(data.get("start"));
        int end = Integer.parseInt(data.get("end"));
        Shift temp = getShiftNextWeek(dayOfWeek , shift);
        if(temp == null)
            return false;
        temp.setStartTime(LocalTime.of(start, 0));
        temp.setEndTime(LocalTime.of(end,0));
        return true;
    }

    public String getShiftHistory(){
        List<String> history = Chain.shiftHistoryRepository.getHistory(branchNum, weekCounter);

        StringBuilder res = new StringBuilder("Branch number: " + this.branchNum + "\n");
        for (String schedule: history){
            res.append(schedule);
        }
        return res.toString();
    }

    public boolean AddOrRemoveDaysOffWork(Dictionary<String, String> data) {
        int action = Integer.parseInt(data.get("action"));
        int day = Integer.parseInt(data.get("day"));
        int shiftType = Integer.parseInt(data.get("shift"));
        DayOfWeek dayOfWeek = Chain.getNextWeekDates()[day -1].getDayOfWeek();
        genShift temp = new genShift(dayOfWeek, shiftType);
        if(action == 1){
            addShiftOffTemp(temp);
        }
        else {
            removeShiftOffTemp(temp);
        }
        return true;
    }

    public boolean ChangingDeadline(Dictionary<String, String> data){
        int day = Integer.parseInt(data.get("day"));
        DayOfWeek dayOfWeek = Chain.getNextWeekDates()[day -1].getDayOfWeek();
        this.systemLimitations.setDeadLine(dayOfWeek);
        return true;
    }

    public boolean checkDeadLine(){
        return Chain.getTodayValue() <= Chain.getDeadLineValue(this.branchNum);
    }
    public void makeASchedule(){
        this.scheduleNextWeek.makeASchedule();
        this.scheduleThisWeek = this.scheduleNextWeek.toString();
    }

    public boolean checkBranchDeadLine(){
        return Chain.getDayValue(Chain.getToday().getDayOfWeek()) <= Chain.getDayValue(getDeadLine());
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("branch number: " + this.branchNum + "\nbranch location: " + this.address + "\nworkers in branch are:\n");

        List<Worker> workers = getWorkers();

        for(Worker worker : workers){
            res.append(worker.toString());
            res.append("\n");
        }
        return res.toString();
    }

    public boolean updateBranch(Dictionary<String, String> data){
        String updateTo = data.get("update");
        boolean flag = true;
        switch (updateTo) {
            case "deadline":
                flag = this.ChangingDeadline(data);
                break;
            case "changeTime":
                flag = this.changeStartAndEndTime(data);
                break;
            case "daysOff":
                flag = this.AddOrRemoveDaysOffWork(data);
                break;
            case "changeAmount":
                this.ChangeAmountTypeOfWorkersShift(data);
                break;
            default:
                break;
        }
        return flag;
    }

    public boolean checkBranchDeadLinePassed() {
        return !checkBranchDeadLine();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public String getZone() {
        return zone;
    }

}