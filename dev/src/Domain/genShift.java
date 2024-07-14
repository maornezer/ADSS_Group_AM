package Domain;

import java.time.DayOfWeek;

public class genShift {
    private DayOfWeek day;
    private int shiftType;

    public genShift(DayOfWeek day, int shiftType) {
        this.day = day;
        this.shiftType = shiftType;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public int getShiftType() {
        return shiftType;
    }

    public void setShiftType(int shiftType) {
        this.shiftType = shiftType;
    }

    public int[] getShiftValue(){
        int[] temp = {Chain.getDayValue(this.day), this.shiftType};
        return temp;
    }
}