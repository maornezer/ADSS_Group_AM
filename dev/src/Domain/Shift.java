package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Shift {
    private LocalDate date;
    private int shiftType;

    private LocalTime startTime;
    private LocalTime endTime;

    private List<String> roles;

    private Dictionary<String, Integer> notAssignedRoles;

    private Dictionary<Worker, String> workersInShift;

    private Worker shiftManager;

    private genShift genShift;

    public genShift getGenShift() {
        return genShift;
    }

    public Shift(LocalDate date, int shiftType) {
        this.date = date;
        this.shiftType = shiftType;
        if(shiftType == 1){
            this.startTime = LocalTime.of(8, 0);
            this.endTime = LocalTime.of(14,0);
        }
        else{
            this.startTime = LocalTime.of(14, 0);
            this.endTime = LocalTime.of(20,0);
        }
        roles = new ArrayList<>();
        notAssignedRoles = new Hashtable<>();
        String[] temp= {"Cashier", "Cashier", "Storekeeper"};
        this.roles.addAll(List.of(temp));
        notAssignedRoles.put("Manager",1);
        for (String role : roles){
            if(notAssignedRoles.get(role) == null)
                notAssignedRoles.put(role, 1);
            else
                notAssignedRoles.put(role, notAssignedRoles.get(role) + 1);
        }
        workersInShift = new Hashtable<>();
        this.genShift = new genShift(date.getDayOfWeek(),shiftType);
    }

    public LocalDate getDate() {
        return date;
    }

    public int getShiftType() {
        return shiftType;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Dictionary<String, Integer> getNotAssignedRoles() {
        return notAssignedRoles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
        for (String role : roles){
            if(notAssignedRoles.get(role) == null)
                notAssignedRoles.put(role, 1);
            else
                notAssignedRoles.put(role, notAssignedRoles.get(role) +1 );
        }
    }

    public void setRoles(Dictionary<String,String> data){
        List<String> roles = new ArrayList<>();
        int amount = Integer.parseInt(data.get("amount"));
        for(int i =0; i<amount; i++){
            roles.add(data.get(Integer.toString(i)));
        }
        this.roles = roles;
    }
    public Dictionary<Worker, String> getWorkersInShift() {
        return workersInShift;
    }

    public void setWorkersInShift(Dictionary<Worker, String> workersInShift) {
        this.workersInShift = workersInShift;
    }

    public Worker getShiftManager() {
        return shiftManager;
    }

    public void setShiftManager(Worker shiftManager) {
        this.shiftManager = shiftManager;
    }

    public void assigningWorker(Worker worker, String role){
        if(role.equals("Manager"))
            this.shiftManager = worker;
        else
            this.workersInShift.put(worker, role);

        if(this.notAssignedRoles.get(role) == 1)
            this.notAssignedRoles.remove(role);
        else
            this.notAssignedRoles.put(role, this.notAssignedRoles.get(role) -1);
    }

    public String workersInShiftToString(){
        String res = "";
        Enumeration enu = this.workersInShift.keys();
        while (enu.hasMoreElements()) {
            Worker temp = (Worker) enu.nextElement();
            res += temp.toString() + " works as " + this.workersInShift.get(temp) +"\n";
        }
        return res;
    }

    public String toString(){
        String res = "Shift date: " + this.date + "\nShift start time: " + this.startTime +
                "\nShift end time: " + this.endTime + "\nShift manager: " + this.shiftManager +
                "\nWorkers in shift: \n" + workersInShiftToString();
        return res;
    }

}
