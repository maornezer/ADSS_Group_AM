package Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Dictionary;
import java.util.List;
import java.util.Objects;


public class WorkersController {

    protected TransportRepository transportRepository;
    protected BranchesRepository branchesRepository;
    protected WorkersRepository workersRepository;
    protected static ShiftHistoryRepository shiftHistoryRepository;

    public WorkersController(TransportRepository transportRepository){
        this.transportRepository = transportRepository;
        this.branchesRepository = new BranchesRepository();
        workersRepository = new WorkersRepository();
        shiftHistoryRepository = new ShiftHistoryRepository();
    }

    public void createChain(Dictionary<String, String> data){
        Chain chain = new Chain(data);
        shiftHistoryRepository.deleteHistory();
    }

    public void addBranch(Dictionary<String, String> data) {
        Chain.addBranch(data);
    }

    public List<Worker> getWorkers(int branchId){
        return workersRepository.getWorkersOfBranch(branchId);
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

    public Branch getBranch(int branchId){
        return branchesRepository.getBranch(branchId);
    }

    public Worker getWorker(Dictionary<String, String> info){
        List<Worker> workers = getWorkers(Integer.parseInt(info.get("branchNum")));
        if(workers == null)
            return null;
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
        return (Chain.getSystemLimit(branchNum).getNextWeekLimits()).clone();
    }

    public LocalDate[] getDatesForNextWeek(){
        return Chain.getNextWeekDates();
    }

    public boolean submitWorkerLimits(Dictionary<String, String> data,int[][] workerLimits){
        Worker worker = getWorker(data);
        return worker.setLimitations(workerLimits);
    }

    public boolean checkBranchDeadLine(Dictionary<String, String> data){
        Branch branch = Chain.getBranch(Integer.parseInt(data.get("branchNum")));
        if(branch == null)
            return false;
        return branch.checkBranchDeadLine();
    }

    public boolean checkBranchDeadLinePassed(Dictionary<String, String> data){
        Branch branch = Chain.getBranch(Integer.parseInt(data.get("branchNum")));
        if(branch == null)
            return false;
        return !checkBranchDeadLine(data);
    }

    public void ChangeAmountTypeOfWorkersShift(Dictionary<String, String> data){
//        Branch branch = Chain.getBranch(Integer.parseInt(data.get("branchNum")));
//        if(branch == null)
//            return;
//        branch.ChangeAmountTypeOfWorkersShift(data);
        data.put("key", data.get("branchNum"));
        data.put("update","changeAmount");

        Chain.updateBranch(data);
    }

    public void changeFirstName(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","firstName");
        data.put("type", "string");

        Chain.updateWorker(data);
    }

    public void changeLastName(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","lastName");
        data.put("type", "string");

        Chain.updateWorker(data);
    }

    public void changeBankDetails(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","bankDetails");
        data.put("type", "int");

        Chain.updateWorker(data);
    }

    public void addRole(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","role");
        data.put("update2","add");
        data.put("type", "string");

        Chain.updateWorker(data);
    }

    public void removeRole(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","role");
        data.put("update2","remove");
        data.put("type", "string");

        Chain.updateWorker(data);

//        String roleToRemove = data.get("RoleToRemove");
//        return getWorker(data).removeRole(roleToRemove);
    }

    public void changeHourRate(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","hourRate");
        data.put("type", "string");

        Chain.updateWorker(data);
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
        data.put("key", data.get("id"));
        data.put("update","jobType");
        data.put("type", "string");

        Chain.updateWorker(data);
    }

    public LocalDate changingEndOfEmployment(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","date");

        int year = Integer.parseInt(data.get("year"));
        int month = Integer.parseInt(data.get("month"));
        int day = Integer.parseInt(data.get("day"));

        LocalDate date = LocalDate.of(year, month,day);
        LocalDate nextSunday = Chain.getNextWeekDates()[0];
        if(date.isBefore(nextSunday)){
            data.put("year",Integer.toString(nextSunday.getYear()));
            data.put("month",Integer.toString(nextSunday.getMonthValue()));
            data.put("day",Integer.toString(nextSunday.getDayOfMonth()));
            date = nextSunday;
        }

        Chain.updateWorker(data);

        return date;
    }

    public void hireWorker(Dictionary<String, String> data){
        Chain.getBranch(Integer.parseInt(data.get("branchNum"))).addWorker(data);
    }

    public LocalDate fireEmployee(Dictionary<String, String> data){
        Branch branch = Chain.getBranch(Integer.parseInt(data.get("branchNum")));
        if(branch == null || branch.checkBranchDeadLinePassed())
            return null;

        return branch.removeWorker(data);
    }

    public boolean changeStartAndEndTime(Dictionary<String, String> data) {
        data.put("key", data.get("branchNum"));
        data.put("update","changeTime");

        return Chain.updateBranch(data);
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
//        int branchNum = Integer.parseInt(data.get("branchNum"));
//        Branch branch = Chain.getBranch(branchNum);
//        if(branch == null)
//            return false;
//        branch.AddOrRemoveDaysOffWork(data);
//        return true;
        data.put("key", data.get("branchNum"));
        data.put("update","daysOff");

        return Chain.updateBranch(data);
    }

    public void ChangingDeadline(Dictionary<String, String> data){
//        int branchNum = Integer.parseInt(data.get("branchNum"));
//        Branch branch = Chain.getBranch(branchNum);
        data.put("key", data.get("branchNum"));
        data.put("update","deadline");

        int day = Integer.parseInt(data.get("day"));
        DayOfWeek dayOfWeek = Chain.getNextWeekDates()[day -1].getDayOfWeek();

        data.put("value", dayOfWeek.toString());

        Chain.updateBranch(data);
//        return branch.ChangingDeadline(data);
    }

    public boolean ShiftsAssignment(){
        return Chain.ShiftsAssignment(this.transportRepository);
    }

    public String PrintShiftsAssignment(Dictionary<String, String> data){
        int branchNum = Integer.parseInt(data.get("branchNum"));
        Branch branch = Chain.getBranch(branchNum);
        if(branch == null)
            return "Branch doesnt exist";
        String res = branch.getScheduleThisWeek();
        return res;
    }

    public String makeTomorrow(){
        Chain.tomorrow();
        return Chain.getToday().toString() + " " + Chain.getToday().getDayOfWeek().toString();
    }

    public void creatScheduleForConfig(){
        Chain.creatScheduleForConfig();
    }

}
