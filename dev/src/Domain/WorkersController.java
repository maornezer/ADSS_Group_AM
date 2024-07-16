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
    protected ShiftHistoryRepository shiftHistoryRepository;

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
        branchesRepository.createBranch(data);
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

    public boolean checkDeadline(int branchNum){
        Branch branch= branchesRepository.getBranch(branchNum);
        if(branch == null)
            return false;
        return Chain.getTodayValue() <= Chain.getDayValue(branch.getDeadLine());
    }

    public boolean updateBranch(Dictionary<String, String> data){
        return branchesRepository.updateBranch(data);
    }

    public LocalDate WorkerResignationNotice(Dictionary<String, String> info){
        return getWorker(info).resignationNotice();
    }

//    public int[][] getBranchLimitation(int branchNum){
//        return (Chain.getSystemLimit(branchNum).getNextWeekLimits()).clone();
//    }

    public LocalDate[] getDatesForNextWeek(){
        return Chain.getNextWeekDates();
    }

    public boolean submitWorkerLimits(Dictionary<String, String> data,int[][] workerLimits){
        Worker worker = getWorker(data);
        return worker.setLimitations(workerLimits);
    }

    public boolean checkBranchDeadLine(Dictionary<String, String> data){
        return checkDeadline(Integer.parseInt(data.get("branchNum")));
    }

    public boolean checkBranchDeadLinePassed(Dictionary<String, String> data){
        Branch branch = branchesRepository.getBranch(Integer.parseInt(data.get("branchNum")));
        if(branch == null)
            return false;
        return !checkBranchDeadLine(data);
    }

    public void ChangeAmountTypeOfWorkersShift(Dictionary<String, String> data){
        data.put("key", data.get("branchNum"));
        data.put("update","changeAmount");

        updateBranch(data);
    }

    public void changeFirstName(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","firstName");
        data.put("type", "string");

        updateWorker(data);
    }

    public void changeLastName(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","lastName");
        data.put("type", "string");

        updateWorker(data);
    }

    public void changeBankDetails(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","bankDetails");
        data.put("type", "int");

        updateWorker(data);
    }

    public void addRole(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","role");
        data.put("update2","add");
        data.put("type", "string");

        updateWorker(data);
    }

    public void removeRole(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","role");
        data.put("update2","remove");
        data.put("type", "string");

        updateWorker(data);
    }

    public void changeHourRate(Dictionary<String, String> data){
        data.put("key", data.get("id"));
        data.put("update","hourRate");
        data.put("type", "string");

        updateWorker(data);
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

        updateWorker(data);
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

        updateWorker(data);

        return date;
    }

    public void updateWorker(Dictionary<String, String> data){
        workersRepository.updateWorker(data);
    }
    public void hireWorker(Dictionary<String, String> data){
        branchesRepository.getBranch(Integer.parseInt(data.get("branchNum"))).addWorker(data);
    }

    public LocalDate fireEmployee(Dictionary<String, String> data){
        Branch branch = branchesRepository.getBranch(Integer.parseInt(data.get("branchNum")));
        if(branch == null || branch.checkBranchDeadLinePassed())
            return null;

        return branch.removeWorker(data);
    }

    public boolean changeStartAndEndTime(Dictionary<String, String> data) {
        data.put("key", data.get("branchNum"));
        data.put("update","changeTime");

        return updateBranch(data);
    }

    public String ViewShiftHistory(Dictionary<String, String> data) {
        int branchNum = Integer.parseInt(data.get("branchNum"));
        Branch branch = branchesRepository.getBranch(branchNum);
        if(branch == null){
            return "Branch number does not exists";
        }
        List<String> history = shiftHistoryRepository.getHistory(branchNum);

        StringBuilder res = new StringBuilder("Branch number: " + Integer.parseInt(data.get("branchNum")) + "\n");
        for (String schedule: history){
            res.append(schedule);
        }
        return res.toString();
    }

    public boolean AddOrRemoveDaysOffWork(Dictionary<String, String> data) {
        data.put("key", data.get("branchNum"));
        data.put("update","daysOff");

        return updateBranch(data);
    }

    public void ChangingDeadline(Dictionary<String, String> data){
        data.put("key", data.get("branchNum"));
        data.put("update","deadline");

        int day = Integer.parseInt(data.get("day"));
        DayOfWeek dayOfWeek = Chain.getNextWeekDates()[day -1].getDayOfWeek();

        data.put("value", dayOfWeek.toString());

        updateBranch(data);
    }

    public boolean ShiftsAssignment(){
        boolean flag = true;

        List<Integer> branchesNums = branchesRepository.getBranchesNums();
        for (int branchNum : branchesNums) {
            flag = flag & branchesRepository.getBranch(branchNum).checkBranchDeadLinePassed();
        }

        if(!flag)
            return false;

        workersRepository.deleteFiredWorkeres();

        Dictionary<LocalDate, List<String[]>> transports = transportRepository.getTransportDetails();

        LocalDate[] nextWeekDates = Chain.getNextWeekDates();
        for(LocalDate date : nextWeekDates){
            List<String[]> temp = transports.get(date);
            for(String[] transport : temp){
                String driverName = transport[1];
                int branchNum = Integer.parseInt(transport[2]);
                Shift shift = branchesRepository.getBranch(branchNum).getShiftNextWeek(date.getDayOfWeek(), 1);
                shift.transport(driverName);
            }
        }

        for (int branchNum : branchesNums)
            branchesRepository.getBranch(branchNum).makeASchedule();

        return true;

    }

    public String PrintShiftsAssignment(Dictionary<String, String> data){
        int branchNum = Integer.parseInt(data.get("branchNum"));
        Branch branch = branchesRepository.getBranch(branchNum);
        if(branch == null)
            return "Branch doesnt exist";
        String res = branch.getScheduleThisWeek();
        return res;
    }

    public String makeTomorrow(){
        if(Chain.tomorrow())
            creatNextWeek();
        return Chain.getToday().toString() + " " + Chain.getToday().getDayOfWeek().toString();
    }

    public void creatNextWeek(){
        List<Integer> branchesNums = branchesRepository.getBranchesNums();
        for(int branchNum: branchesNums){

            Dictionary <String, String> data = branchesRepository.getBranch(branchNum).creatNextWeek();
            this.shiftHistoryRepository.create(data);

            List<Worker> workers = workersRepository.getWorkersOfBranch(branchNum);
            for(Worker worker : workers){
                worker.DefaultNextWeek();
            }
        }
    }



}
