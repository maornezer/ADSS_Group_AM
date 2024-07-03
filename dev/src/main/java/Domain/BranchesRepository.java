package Domain;

import Data.BranchesDAO;
import Data.DB;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class BranchesRepository {

    protected Dictionary<Integer, Branch> branchesRepo;

    public BranchesRepository(){
        branchesRepo = new Hashtable<>();
    }

    public Branch getBranch(int id){
        Branch branch  = branchesRepo.get(id);
        if(branch == null) {
            branch = DB.getBranchesDAO().read(id);
            branchesRepo.put(id, branch);
        }
        return branch;
    }

    public Boolean createBranch(Dictionary<String, String> data){
        Branch branch = DB.getBranchesDAO().create(data);
        if(branch != null){
            branchesRepo.put(branch.getBranchNum(), branch);
            return true;
        }
        return false;
    }

    public boolean updateBranch(Dictionary<String, String> data){
        Branch branch = branchesRepo.get(Integer.parseInt(data.get("id")));
        if(branch != null) {
            branch.updateBranch(data);
            DB.getBranchesDAO().update(data);
            return true;
        }
        return false;
    }

    public void deleteBranch(int id){
        Branch branch = branchesRepo.get("id");
        if(branch != null) {
            branchesRepo.remove(id);
        }
        DB.getBranchesDAO().delete(id);
    }

    public List<Integer> getBranchesNums(){
        return DB.getBranchesDAO().getBranchesNums();
    }

}
