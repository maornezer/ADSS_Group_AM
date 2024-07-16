package Domain;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static java.time.DayOfWeek.*;

public class SystemLimitations {

    protected int branchId;
    protected DayOfWeek deadLine;
    protected List<genShift> shiftsOffConst;
    protected List<genShift> shiftsOffTemp;
    protected int[][] nextWeekLimits;

    public SystemLimitations(int branchId) {
        this.branchId = branchId;
        this.deadLine = THURSDAY;
        shiftsOffConst = new ArrayList<>();
        shiftsOffTemp = new ArrayList<>();
        nextWeekLimits = new int[7][2];
        this.shiftsOffConst.add(new genShift(SATURDAY,1));
        this.shiftsOffConst.add(new genShift(SATURDAY, 2));
        creatNextWeek();
    }

    public DayOfWeek getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(DayOfWeek deadLine) {
        this.deadLine = deadLine;
    }

    public int getBranchId() {
        return branchId;
    }

    public List<genShift> getShiftsOffConst() {
        return shiftsOffConst;
    }

    public List<genShift> getShiftsOffTemp() {
        return shiftsOffTemp;
    }

    public void setShiftsOffTemp(List<genShift> shiftsOffTemp) {
        this.shiftsOffTemp = shiftsOffTemp;
    }

    public void addShiftOffConst(genShift shift){
        shiftsOffConst.add(shift);
        int[] temp = shift.getShiftValue();
        nextWeekLimits[temp[0]-1][temp[1]-1] = -1;
    }

    public void addShiftOffTemp(genShift shift){
        shiftsOffTemp.add(shift);
        int[] temp = shift.getShiftValue();
        nextWeekLimits[temp[0]-1][temp[1]-1] = -1;
    }

    public void removeShiftOffConst(genShift shift){
        int index = -1;
        for(genShift genShift : this.shiftsOffConst){
            if(genShift.getDay() == shift.getDay() & genShift.getShiftType() == shift.getShiftType()) {
                shiftsOffConst.remove(genShift);
                break;
            }
        }
        int[] temp = shift.getShiftValue();
        nextWeekLimits[temp[0]-1][temp[1]-1] = 1;
    }

    public void removeShiftOffTemp(genShift shift){
        int index = -1;
        for(genShift genShift : this.shiftsOffTemp){
            if(genShift.getDay() == shift.getDay() & genShift.getShiftType() == shift.getShiftType()) {
                shiftsOffTemp.remove(genShift);
                break;
            }
        }
        int[] temp = shift.getShiftValue();
        nextWeekLimits[temp[0]-1][temp[1]-1] = 1;
    }


    public int[][] getNextWeekLimits() {
        return nextWeekLimits.clone();
    }

    public void setNextWeekLimits(int[][] nextWeekLimits) {
        this.nextWeekLimits = nextWeekLimits;
    }

    public void creatNextWeek(){
        this.shiftsOffTemp.clear();

        for (int i = 0; i< 7; i++){
            for (int j = 0; j<2; j++){
                nextWeekLimits[i][j] = 1;
            }
        }
        for (genShift shift: shiftsOffConst) {
            int[] temp = shift.getShiftValue();
//            nextWeekLimits[temp[0]-1][temp[1]-1] = -1;
            nextWeekLimits[temp[0]-1][temp[1]-1] = -1;
        }
    }

}