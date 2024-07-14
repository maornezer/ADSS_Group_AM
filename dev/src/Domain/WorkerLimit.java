package Domain;

import java.time.DayOfWeek;

public class WorkerLimit{
    private int id;

    private int branchId;
    private int[][] Limitations;

    public WorkerLimit(int id, int branchId) {
        this.id = id;
        this.branchId = branchId;
        this.Limitations = new int[7][2];
        DefaultNextWeek();
    }

    public int getId() {
        return id;
    }

    public int[][] getLimitations() {
        return Limitations;
    }

    public void setLimitations(int[][] limitations) { // limitations contains default values from next week
        if(Chain.getTodayValue() <= Chain.getDeadLineValue(branchId)){
            this.Limitations = limitations.clone();
        }
    }

    public void DefaultNextWeek(){
        this.Limitations = Chain.getSystemLimit(branchId).getNextWeekLimits().clone();
    }

    public boolean ifCanWork(DayOfWeek day, int shiftType){
        return this.Limitations[Chain.getDayValue(day)-1][shiftType-1] == 1;
    }

}