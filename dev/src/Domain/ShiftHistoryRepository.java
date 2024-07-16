package Domain;

import DAL.DB;
import DAL.ShiftHistoryDAO;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class ShiftHistoryRepository {
    protected Dictionary<Integer, List<String>> shiftHistoryRepo;
    protected ShiftHistoryDAO shiftHistoryDAO;

    public ShiftHistoryRepository() {
        shiftHistoryRepo = new Hashtable<>();
        shiftHistoryDAO = new ShiftHistoryDAO();
    }

    public List<String> getHistory(int branchNum) {
        List<String> history = shiftHistoryRepo.get(branchNum);
        if (history == null) {
            history = shiftHistoryDAO.read(branchNum);
            shiftHistoryRepo.put(branchNum, history);
        }
        return history;
    }

    public void create(Dictionary<String, String> data) {
        int branchNum = Integer.parseInt(data.get("branchNum"));
        String shiftString = data.get("shiftString");

        shiftHistoryDAO.create(data);

        if (shiftHistoryRepo.get(branchNum) == null) {
            shiftHistoryRepo.put(branchNum, new ArrayList<>());
        }

        shiftHistoryRepo.get(branchNum).add(shiftString);
    }

    public void updateHistory(Dictionary<String, String> data) {
        int branchNum = Integer.parseInt(data.get("branchNum"));
        String shiftString = data.get("shiftString");
        int index = Integer.parseInt(data.get("index"));

        List<String> history = shiftHistoryRepo.get(branchNum);
        if (history == null) {
            history = shiftHistoryDAO.read(branchNum);
            shiftHistoryRepo.put(branchNum, history);
        }

        if (index >= 0 && index < history.size()) {
            history.set(index, shiftString);
        }

        shiftHistoryDAO.update(data);
    }

    public void deleteBranchFromHistory(int id) {
        shiftHistoryDAO.delete(id);
        shiftHistoryRepo.remove(id);
    }

    public void deleteHistory() {
        shiftHistoryDAO.delete();
        shiftHistoryRepo = new Hashtable<>();
    }
}
