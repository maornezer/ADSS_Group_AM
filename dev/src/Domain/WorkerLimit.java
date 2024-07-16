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

        for(int i =0; i<7; i++){
            for(int j = 0; j<2; j++){
                if(i != 6)
                    Limitations[i][j] = 1;
                else{
                    Limitations[i][j] = 0;
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public int[][] getLimitations() {
        return Limitations;
    }

    public void setLimitations(int[][] limitations) { // limitations contains default values from next week
        this.Limitations = limitations.clone();
    }

    public void DefaultNextWeek(int[][] nextWeekLimits){
        this.Limitations = nextWeekLimits.clone();
    }

    public boolean ifCanWork(DayOfWeek day, int shiftType){
        return this.Limitations[Chain.getDayValue(day)-1][shiftType-1] == 1;
    }

}