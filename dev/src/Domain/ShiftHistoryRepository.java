package Domain;

import DAL.DB;
import DAL.ShiftHistoryDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ShiftHistoryRepository {
    protected Dictionary<Integer, List<String>> shiftHistoryRepo;
    protected ShiftHistoryDAO shiftHistoryDAO;

    public ShiftHistoryRepository(){
        shiftHistoryRepo = new Hashtable<>();
        shiftHistoryDAO = new ShiftHistoryDAO();
    }

    public List<String> getHistory(int branchNum, int weekCounter){
        List<String> history = shiftHistoryRepo.get(branchNum);
        if(history.size() != weekCounter - 1){
            history = shiftHistoryDAO.read(branchNum);
            shiftHistoryRepo.put(branchNum, history);
        }
        return history;
    }

    public void create(Dictionary<String, String> data){
        shiftHistoryDAO.create(data);
        if(shiftHistoryRepo.get(Integer.parseInt(data.get("branchNum"))) == null)
            shiftHistoryRepo.put(Integer.parseInt(data.get("branchNum")), new ArrayList<>());
        shiftHistoryRepo.get(Integer.parseInt(data.get("branchNum"))).add(data.get("shiftString"));
    }

    public void updateHistory(Dictionary<String, String> data){
    }

    public void deleteBranchFromHistory(int id){
        shiftHistoryDAO.delete(id);
    }

    public void deleteHistory(){
        shiftHistoryDAO.delete();
    }

}