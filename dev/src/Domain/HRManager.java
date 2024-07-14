package Domain;

import java.time.LocalDate;

public class HRManager {

    private int id;
    private String firstName;
    private String lastName;
    private int bankInfo;
    private Contract contract;

    public HRManager(int id, String firstName, String lastName, int bankInfo, int branchId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankInfo = bankInfo;
        this.contract = new Contract(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(int bankInfo) {
        this.bankInfo = bankInfo;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public void setHourRate(int hourRate) {
        this.contract.setHourRate(hourRate);
    }

    public int getHourRate() {
        return this.contract.getHourRate();
    }
    public void setVacationDays(int vacationDays) {
        this.contract.setVacationDays(vacationDays);
    }

    public int getVacationDays() {
        return this.contract.getVacationDays();
    }
    public void setUsedVacationDays(int UsedVacationDays) {
        this.contract.setUsedVacationDays(UsedVacationDays);
    }

    public int getUsedVacationDays() {
        return this.contract.getUsedVacationDays();
    }
    public void IncreaseUsedVacationDays() {
        this.contract.setUsedVacationDays(this.contract.getUsedVacationDays()+1);
    }

    public String getJobType() {
        return this.contract.getJobType();
    }
    public void setJobType(String jobType) {
        this.contract.setJobType(jobType);
    }

    public void setEmploymentStart(LocalDate employmentStart) {
        this.contract.setEmploymentStart(employmentStart);
    }

    public LocalDate getEmploymentStart() {
        return this.contract.getEmploymentStart();
    }

    public LocalDate getEmploymentEnd() {
        return this.contract.getEmploymentEnd();
    }


}