package Domain;

import Data.DB;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ShiftHistoryRepository {
    protected Dictionary<Integer, List<String>> shiftHistoryRepo;

    public ShiftHistoryRepository(){
        shiftHistoryRepo = new Hashtable<>();
    }

    public List<String> getHistory(int branchNum, int weekCounter){
        List<String> history = shiftHistoryRepo.get(branchNum);
        if(history.size() != weekCounter - 1){
            history = DB.getShiftHistoryDAO().read(branchNum);
            shiftHistoryRepo.put(branchNum, history);
        }
        return history;
    }

    public void create(Dictionary<String, String> data){
        DB.getShiftHistoryDAO().create(data);
        if(shiftHistoryRepo.get(Integer.parseInt(data.get("branchNum"))) == null)
            shiftHistoryRepo.put(Integer.parseInt(data.get("branchNum")), new ArrayList<>());
        shiftHistoryRepo.get(Integer.parseInt(data.get("branchNum"))).add(data.get("shiftString"));
    }

    public void updateHistory(Dictionary<String, String> data){
    }

    public void deleteBranchFromHistory(int id){
        DB.getShiftHistoryDAO().delete(id);
    }

    public void deleteHistory(){
        DB.getShiftHistoryDAO().delete();
    }

}
