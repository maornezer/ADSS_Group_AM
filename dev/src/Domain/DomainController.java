package Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Dictionary;
import java.util.List;
import java.util.Objects;

public class DomainController {
    public DomainController(){
    }

    public boolean verification(Dictionary<String, String> info){
        if(Objects.equals(info.get("branchNum"), "0")) {
            if (Integer.toString(Chain.getHrManager().getId()).equals(info.get("id")))
                return true;
            else
                return false;
        }
        if(getWorker(info) != null)
            return true;
        else
            return false;

    }

    public Worker getWorker(Dictionary<String, String> info){
        List<Worker> workers = Chain.getWorkers(Integer.parseInt(info.get("branchNum")));
        for (Worker worker: workers){
            if (Integer.toString(worker.getId()).equals(info.get("id")))
                return worker;
        }
        return null;
    }

    public LocalDate WorkerResignationNotice(Dictionary<String, String> info){
        return getWorker(info).resignationNotice();
    }

    public int[][] getBranchLimitation(int branchNum){
        return Chain.getSystemLimit(branchNum).getNextWeekLimits();
    }

    public LocalDate[] getDatesForNextWeek(){
        return Chain.getNextWeekDates();
    }

    public boolean submitWorkerLimits(Dictionary<String, String> data,int[][] workerLimits){
        Worker worker = getWorker(data);
        return worker.setLimitations(workerLimits);
    }

    public boolean checkDeadLine(Dictionary<String, String> data){
        return getWorker(data).checkDeadLine();
    }

    public boolean checkBranchDeadLine(Dictionary<String, String> data){
        Branch branch = Chain.getBranch(Integer.parseInt(data.get("branchNum")));
        if(branch == null)
            return false;
        return branch.checkBranchDeadLine();
    }

    public void ChangeAmountTypeOfWorkersShift(Dictionary<String, String> data){
        Branch branch = Chain.getBranch(Integer.parseInt(data.get("branchNum")));
        if(branch == null)
            return;
        branch.ChangeAmountTypeOfWorkersShift(data);
    }

    public void changeFirstName(Dictionary<String, String> data){
        String newName = data.get("newName");
        getWorker(data).setFirstName(newName);
    }

    public void changeLastName(Dictionary<String, String> data){
        String newName = data.get("newName");
        getWorker(data).setLastName(newName);
    }

    public void changeBankDetails(Dictionary<String, String> data){
        int newBank = Integer.parseInt(data.get("newBank"));
        getWorker(data).setBankInfo(newBank);
    }

    public void addRole(Dictionary<String, String> data){
        String newRole = data.get("newRole");
        getWorker(data).addRole(newRole);
    }

    public boolean removeRole(Dictionary<String, String> data){
        String roleToRemove = data.get("roleToRemove");
        return getWorker(data).removeRole(roleToRemove);
    }

    public void changeHourRate(Dictionary<String, String> data){
        int newSalary = Integer.parseInt(data.get("newSalary"));
        getWorker(data).setHourRate(newSalary);
    }

    public void changeVacationDays(Dictionary<String, String> data){
        int newAmount = Integer.parseInt(data.get("newAmount"));
        getWorker(data).setVacationDays(newAmount);
    }

    public void reduceVacationDays(Dictionary<String, String> data){
        int newAmount = Integer.parseInt(data.get("newAmount"));
        getWorker(data).setUsedVacationDays(getWorker(data).getVacationDays() - newAmount);
    }

    public void changeJobType(Dictionary<String, String> data){
        String newType = data.get("newType");
        getWorker(data).setJobType(newType);
    }

    public LocalDate changingEndOfEmployment(Dictionary<String, String> data){
        int year = Integer.parseInt(data.get("year"));
        int month = Integer.parseInt(data.get("month"));
        int day = Integer.parseInt(data.get("day"));
        LocalDate date = LocalDate.of(year, month,day);
        return getWorker(data).setEmploymentEnd(date);
    }

    public void hireWorker(Dictionary<String, String> data){
        Chain.getBranch(Integer.parseInt(data.get("branchNum"))).addWorker(data);
    }

    public LocalDate fireEmployee(Dictionary<String, String> data){
        return Chain.getBranch(Integer.parseInt(data.get("branchNum"))).removeWorker(getWorker(data));
    }

    public boolean changeStartAndEndTime(Dictionary<String, String> data) {
        int branchNum = Integer.parseInt(data.get("branchNum"));
        return Chain.getBranch(branchNum).changeStartAndEndTime(data);
    }

    public String ViewShiftHistory(Dictionary<String, String> data) {
        int branchNum = Integer.parseInt(data.get("branchNum"));
        Branch branch = Chain.getBranch(branchNum);
        if(branch == null){
            return "Branch number does not exists";
        }
        return branch.getShiftHistory();
    }

    public boolean AddOrRemoveDaysOffWork(Dictionary<String, String> data) {
        int branchNum = Integer.parseInt(data.get("branchNum"));
        Branch branch = Chain.getBranch(branchNum);
        if(branch == null)
            return false;
        branch.AddOrRemoveDaysOffWork(data);
        return true;
    }

    public boolean ChangingDeadline(Dictionary<String, String> data){
        int branchNum = Integer.parseInt(data.get("branchNum"));
        Branch branch = Chain.getBranch(branchNum);
        return branch.ChangingDeadline(data);
    }

    public void ShiftsAssignment(){
        List<Branch> branches = Chain.getBranches();
        for (Branch branch : branches)
            branch.makeASchedule();
    }

    public void makeTomorrow(){
        Chain.tomorrow();
    }

    public void assigningHR(Dictionary<String, String> data){

    }

}

