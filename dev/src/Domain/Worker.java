package Domain;

import java.time.LocalDate;
import java.util.Dictionary;

public class Worker {
    private int id;
    private String firstName;
    private String lastName;
    private int bankInfo;
    private Contract contract;
    private Roles roles;
    private WorkerLimit limitations;

    private int branchId;

    public Worker(int id, String firstName, String lastName, int bankInfo, int branchId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bankInfo = bankInfo;
        this.contract = new Contract(id);
        this.roles = new Roles(id);
        this.limitations = new WorkerLimit(id, branchId);
        this.branchId = branchId;
    }

    public Worker(Dictionary<String, String> data){
        this(Integer.parseInt(data.get("id")), data.get("firstName"),data.get("lastName"),
                Integer.parseInt(data.get("bankDetails")), Integer.parseInt(data.get("branchNum")));
        this.setHourRate(Integer.parseInt(data.get("hourRate")));
        this.setJobType(data.get("jobType"));
        LocalDate date = LocalDate.of(Integer.parseInt(data.get("year")),Integer.parseInt(data.get("month")),
                Integer.parseInt(data.get("day")));
        this.setEmploymentEnd(date);
        int amount = Integer.parseInt(data.get("amount"));
        for(int i = 0; i< amount; i++){
            addRole(data.get(Integer.toString(i)));
        }
    }

    public int getId() {
        return id;
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

    public Roles getRoles() {
        return roles;
    }

    public void addRole(String role){
        this.roles.addRole(role);
    }

    public boolean removeRole(String role){
        return this.roles.removeRole(role);
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public WorkerLimit getLimitations() {
        return limitations;
    }

    public LocalDate resignationNotice(){
        if(Chain.getToday().plusDays(30).isBefore(this.contract.getEmploymentEnd()))
            this.contract.setEmploymentEnd(Chain.getToday().plusDays(30));
        return this.contract.getEmploymentEnd();
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

    public LocalDate setEmploymentEnd(LocalDate employmentEnd) {
        return this.contract.setEmploymentEnd(employmentEnd);
    }
    public boolean setLimitations(int[][] Limitations){
        if(checkDeadLine())
            this.limitations.setLimitations(Limitations);
        return checkDeadLine();
    }

    public boolean checkDeadLine(){
        return Chain.getTodayValue() <= Chain.getDeadLineValue(this.branchId);
    }

    public boolean stillEmployed(LocalDate now){
        return this.contract.getEmploymentEnd().isAfter(now);
    }

    public boolean ifCanWork(LocalDate date, int shiftType){
        return stillEmployed(date) && this.limitations.ifCanWork(date.getDayOfWeek(), shiftType);
    }

    public boolean checkShiftManager(){
        return this.roles.checkShiftManager();
    }

    public boolean checkRole(String role){
        return this.roles.checkRole(role);
    }

    public String toString(){
        String res = this.firstName + " " + this.lastName;
        return res;
    }

}
