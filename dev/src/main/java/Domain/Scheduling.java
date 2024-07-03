package Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class Scheduling {

    private int branchId;
    private Shift[][] schedule;
    private LocalDate[] dates;

    public Scheduling(int branchId, SystemLimitations limits) {
        this.branchId = branchId;
        schedule = new Shift[7][2];
        dates = Chain.getNextWeekDates();
        limits.creatNextWeek();
    }

    public Scheduling(int branchId) {
        this.branchId = branchId;
        schedule = new Shift[7][2];
        dates = Chain.getNextWeekDates();
        SystemLimitations limits = Chain.getSystemLimit(branchId);
        limits.creatNextWeek();
    }

    public Shift[][] getSchedule() {
        return schedule;
    }

    public void creatSchedule(){
        int[][] nextWeekLimits = Chain.getBranch(branchId).getSystemLimitations().getNextWeekLimits();
        for(int i =0; i<7; i++){
            for (int j = 0; j<2; j++){
                if(nextWeekLimits[i][j] == 1){
                    this.schedule[i][j] = new Shift(this.dates[i],j+1);
                }
                else{
                    this.schedule[i][j]= null;
                }
            }
        }
    }

    public void makeASchedule() {
        for(Shift[] day : schedule){
            for(int j = 0; j<2; j++) {
                if(day[j] == null)
                    continue;
                List<Worker> workersList = workersForOneShift(day[j].getGenShift());
                assignShift(day , workersList, j);
            }
            if(day[0] != null && day[1] != null) {
                List<Worker> workersList = workersForDay(day[0].getGenShift().getDay());
                List<Worker> notAssigned = assignShift(day, workersList, 0);
                assignShift(day, notAssigned, 1);
            }
        }
    }

    private List<Worker> assignShift(Shift[] day, List<Worker> workersList, int k) {
        Dictionary<String, Integer> notAssignedRoles = day[k].getNotAssignedRoles();
        List<Worker> notAssigned = new ArrayList<>(workersList);
        Enumeration<String> enu = notAssignedRoles.keys();
        while (enu.hasMoreElements()) {
            String role = (String) enu.nextElement();
            int amount = notAssignedRoles.get(role);
            List<Worker> workersForOneRole = workersForOneRole(workersList, role);
            List<Worker> workersForFewRoles = workersForRole(notAssigned, role);
            List<Worker> workersForRole = unionLists(workersForOneRole, workersForFewRoles);
            Collections.shuffle(workersForRole);
            for (int i = 0; i < amount && i < workersForRole.size(); i++) {
                notAssigned.remove(workersForRole.get(i));
                day[k].assigningWorker(workersForRole.get(i), role);
            }
        }
        return notAssigned;
    }

    public List<Worker> unionLists(List<Worker> list1, List<Worker> list2){
        List<Worker> resList = new ArrayList<>(list1);
        for(int i = 0 ; i<list2.size(); i++){
            if(!resList.contains(list2.get(i)))
                resList.add(list2.get(i));
        }
        return resList;
    }


    public Shift getShift(DayOfWeek day, int shiftType){
        return this.schedule[Chain.getDayValue(day)-1][shiftType-1];
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        for(Shift[] day : schedule){
            if(day[0] != null){
                res.append(day[0].toString());
            }
            if(day[1] != null)
                res.append(day[1].toString());
        }
        return res.toString();
    }

    public List<Worker> workersForOneShift(genShift shift){
        List<Worker> workerList = Chain.getWorkers(this.branchId);
        List<Worker> workersForShift = new ArrayList<>();
        if (workerList == null)
            return null;
        int otherShift = (shift.getShiftType() % 2) + 1;
        for(Worker worker : workerList){
            if(worker.ifCanWork(this.dates[Chain.getDayValue(shift.getDay())-1], shift.getShiftType()) &&
                    !worker.ifCanWork(this.dates[Chain.getDayValue(shift.getDay())-1], otherShift)){
                workersForShift.add(worker);
            }
        }
        return workersForShift;
    }

    public List<Worker> workersForDay(DayOfWeek dayOfWeek){
        List<Worker> workerList = Chain.getWorkers(this.branchId);
        List<Worker> workersForShift = new ArrayList<>();
        if (workerList == null)
            return null;
        for(Worker worker : workerList){
            if(worker.ifCanWork(this.dates[Chain.getDayValue(dayOfWeek) -1 ], 1) &&
                    worker.ifCanWork(this.dates[Chain.getDayValue(dayOfWeek)-1], 2)){
                workersForShift.add(worker);
            }
        }
        return workersForShift;
    }

    public List<Worker> workersForRole(List<Worker> workers, String role){
        List<Worker> workersRes = new ArrayList<>();
        for(Worker worker : workers){
            if(worker.checkRole(role))
                workersRes.add(worker);
        }
        return workersRes;
    }

    public List<Worker> workersForOneRole(List<Worker> workers, String role){
        List<Worker> workersRes = new ArrayList<>();
        for(Worker worker : workers){
            if(worker.checkRole(role) && worker.getRoles().getRoles().size() == 1)
                workersRes.add(worker);
        }
        return workersRes;
    }

}
