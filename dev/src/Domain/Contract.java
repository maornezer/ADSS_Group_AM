package Domain;

import netscape.javascript.JSObject;

import java.time.LocalDate;
import java.util.Date;

public class Contract {
    private int id;
    private int hourRate;
    private int vacationDays;
    private int usedVacationDays;
    private String jobType;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;

    public Contract(int id){
        this.id = id;
        this.employmentStart = Chain.getToday();
        this.usedVacationDays = 0;
    }

    public boolean stillEmployed(LocalDate now){
        return this.employmentEnd.isAfter(now);
    }


    public int getId() {
        return id;
    }

    public int getHourRate() {
        return hourRate;
    }

    public void setHourRate(int hourRate) {
        this.hourRate = hourRate;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public int getUsedVacationDays() {
        return usedVacationDays;
    }

    public void setUsedVacationDays(int usedVacationDays) {
        this.usedVacationDays = usedVacationDays;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public LocalDate getEmploymentStart() {
        return employmentStart;
    }

    public void setEmploymentStart(LocalDate employmentStart) {
        this.employmentStart = employmentStart;
    }

    public LocalDate getEmploymentEnd() {
        return employmentEnd;
    }

    public LocalDate setEmploymentEnd(LocalDate employmentEnd) {
        if(employmentEnd.isBefore(Chain.getNextWeekDates()[0]))
            this.employmentEnd = Chain.getNextWeekDates()[0];
        else
            this.employmentEnd = employmentEnd;
        return this.employmentEnd;
    }
}
