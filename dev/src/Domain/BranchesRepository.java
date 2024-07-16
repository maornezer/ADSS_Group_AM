package Domain;

import DAL.BranchDAO;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class BranchesRepository {

    protected Dictionary<Integer, Branch> branchesRepo;
    private BranchDAO branchDAO;

    public BranchesRepository(){
        branchesRepo = new Hashtable<>();
        branchDAO = new BranchDAO();
    }

    public Branch getBranch(int id){
        Branch branch  = branchesRepo.get(id);
        if(branch == null) {
            branch = branchDAO.read(id);
            if(branch != null) {
                branchesRepo.put(id, branch);
            } else {
                System.out.println("BranchesRepo: No branch found with id " + id);
            }
        }
        return branch;
    }

    public Boolean createBranch(Dictionary<String, String> data){
        Branch branch = branchDAO.create(data);
        if(branch != null){
            branchesRepo.put(branch.getBranchNum(), branch);
            return true;
        }
        return false;
    }

    public boolean updateBranch(Dictionary<String, String> data){
        Branch branch = branchesRepo.get(Integer.parseInt(data.get("key")));
        if(branch != null) {
            boolean flag = branch.updateBranch(data);
            branchDAO.update(data);
            return flag;
        }
        return false;
    }

    public void deleteBranch(int id){
        Branch branch = branchesRepo.get(id);
        if(branch != null) {
            branchesRepo.remove(id);
        }
        branchDAO.delete(id);
    }

    public List<Integer> getBranchesNums(){
        return branchDAO.getBranchesNums();
    }

    public boolean validMatchZone(String source, String destination ) {
        Branch b1 = getBranch(Integer.parseInt(source));
        Branch b2 =  getBranch(Integer.parseInt(destination));
        if (b1!=null && b2!=null) {
            return b1.getZone().compareTo(b2.getZone())==0;
        }
        return false;
    }
}


//package Domain;
//
//import DAL.BranchDAO;
//import DAL.DB;
//import DAL.SiteDAO;
//
//import java.util.Dictionary;
//import java.util.Hashtable;
//import java.util.List;
//
//public class BranchesRepository {
//
//    protected Dictionary<Integer, Branch> branchesRepo;
//    private BranchDAO branchDAO;
//
//
//    public BranchesRepository(){
//        branchesRepo = new Hashtable<>();
//        branchDAO = new BranchDAO();
//    }
//
//    public Branch getBranch(int id){
//        Branch branch  = branchesRepo.get(id);
//        if(branch == null) {
//            branch = branchDAO.read(id);
//            try {
//                branchesRepo.put(id, branch);
//            }
//            catch (Exception e){
//                System.out.println("BranchesRepo - " + e.getMessage());
//            }
//        }
//        return branch;
//    }
//
//    public Boolean createBranch(Dictionary<String, String> data){
//        Branch branch = branchDAO.create(data);
//        if(branch != null){
//            branchesRepo.put(branch.getBranchNum(), branch);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean updateBranch(Dictionary<String, String> data){
//        Branch branch = branchesRepo.get(Integer.parseInt(data.get("key")));
//        if(branch != null) {
//            boolean flag = branch.updateBranch(data);
//            branchDAO.update(data);
//            return flag;
//        }
//        return false;
//    }
//
//    public void deleteBranch(int id){
//        Branch branch = branchesRepo.get("id");
//        if(branch != null) {
//            branchesRepo.remove(id);
//        }
//        branchDAO.delete(id);
//    }
//
//    public List<Integer> getBranchesNums(){
//        return branchDAO.getBranchesNums();
//    }
//    public boolean validMatchZone(String source, String destination )
//    {
//        Branch b1 = getBranch(Integer.parseInt(source));
//        Branch b2 =  getBranch(Integer.parseInt(destination));
//        if (b1!=null && b2!=null)
//        {
//            return b1.getZone().compareTo(b2.getZone())==0;
//        }
//        return false;
//    }
//}