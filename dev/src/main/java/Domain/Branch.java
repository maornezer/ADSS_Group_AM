package Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

public class Branch {
    private int branchId;
    private String address;
    private List<Worker> workers;
    private SystemLimitations systemLimitations;

    private List<Worker> firedWorkers;

    private Scheduling scheduleNextWeek;

    private String scheduleThisWeek;

    private List<String> branchHistory;

    public Scheduling getScheduleNextWeek() {
        return scheduleNextWeek;
    }

    public Branch(int branchId, String address) {
        this.branchId = branchId;
        this.address = address;
        this.workers = new ArrayList<Worker>();
        this.firedWorkers = new ArrayList<Worker>();
        this.systemLimitations = new SystemLimitations(branchId);
        this.scheduleNextWeek = new Scheduling(branchId, this.systemLimitations);
        this.scheduleThisWeek = "No shifts yet";
        this.branchHistory = new ArrayList<>();
    }

    public List<Worker> getWorkers() {
        return workers;
    } // change this!! kuku

    public int getBranchId() {
        return branchId;
    }

    public String getAddress() {
        return address;
    }

    public void addWorker(Worker worker){
        this.workers.add(worker);
    } // change this!! kuku

//    public void addWorker(int id, String firstName, String lastName, int bankInfo) {
//        Worker temp = new Worker(id, firstName,lastName, bankInfo, this.branchId);
//        this.workers.add(temp);
//    }

    public void addWorker(Dictionary<String, String> data){ // change this!! kuku
        Worker newWorker = new Worker(data);
        this.workers.add(newWorker);
    }

    public LocalDate removeWorker(Worker worker){ // change this!! kuku
        LocalDate date = null;
        if(this.workers.contains(worker)) {
            this.firedWorkers.add(worker);
            worker.setEmploymentEnd(Chain.getNextWeekDates()[0]);
            this.workers.remove(worker);
            date = Chain.getNextWeekDates()[0];
        }
        return date;
    }

    public SystemLimitations getSystemLimitations() {
        return systemLimitations;
    }

//    public void setSystemLimitations(SystemLimitations systemLimitations) {
//        this.systemLimitations = systemLimitations;
//    }

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
        this.branchHistory.add(this.scheduleNextWeek.toString());
        this.scheduleNextWeek = new Scheduling(this.branchId);
        this.scheduleNextWeek.creatSchedule();
        for(Worker worker : this.workers){
            worker.DefaultNextWeek();
        }
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

    public List<Worker> getFiredWorkers() {
        return firedWorkers;
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
        StringBuilder res = new StringBuilder("Branch number: " + this.branchId + "\n");
        for (String schedule: this.branchHistory){
            res.append(schedule.toString());
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
        return Chain.getTodayValue() <= Chain.getDeadLineValue(this.branchId);
    }
    public void makeASchedule(){
        this.scheduleNextWeek.makeASchedule();
        this.scheduleThisWeek = this.scheduleNextWeek.toString();
    }

//    public void creatSchedule(){this.scheduleNextWeek.creatSchedule();}

    public boolean checkBranchDeadLine(){
        return Chain.getDayValue(Chain.getToday().getDayOfWeek()) <= Chain.getDayValue(getDeadLine());
    }


    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("branch number: " + this.branchId + "\nbranch location: " + this.address + "\nworkers in branch are:\n");
        for(Worker worker : this.workers){
            res.append(worker.toString());
            res.append("\n");
        }
        return res.toString();
    }

    public boolean checkBranchDeadLinePassed() {
        return !checkBranchDeadLine();
    }
}
