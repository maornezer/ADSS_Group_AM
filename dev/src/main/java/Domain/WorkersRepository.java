package Domain;

import Data.DB;
import Data.WorkersDAO;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class WorkersRepository {

    protected Dictionary<Integer, Worker> workersRepo;

    public WorkersRepository(){
        workersRepo = new Hashtable<>();
    }

    public Worker getWorker(int id){
        Worker worker = workersRepo.get(id);
        if(worker == null) {
            worker = DB.getWorkersDAO().read(id);
            workersRepo.put(id, worker);
        }
        return worker;
    }

//    public void fireWorkers(){
//        List<Integer> workers = DB.getWorkersDAO().fireWorkers();
//        if(workers != null){
//            for(int id : workers)
//                workersRepo.remove(id);
//        }
//    }

    public List<Worker> getWorkersOfBranch(int branchNum){
        List<Worker> workerList = DB.getWorkersDAO().readWorkersByBranch(branchNum);
        List<Worker> res = new ArrayList<>();
        for(Worker worker: workerList){
            Worker tempWorker = workersRepo.get(worker.getId());
                if (tempWorker != null && tempWorker.getEmploymentEnd().isBefore(Chain.getToday())) {
                    res.add(tempWorker);
                } else {
                    if(worker.getEmploymentEnd().isBefore(Chain.getToday()))
                        res.add(worker);
                }
            }
        return res;
    }

    public Worker createWorker(Dictionary<String, String> data){
        Worker worker = DB.getWorkersDAO().create(data);
        if(worker != null){
            workersRepo.put(worker.getId(), worker);
            return worker;
        }
        return null;
    }

    public boolean updateWorker(Dictionary<String, String> data){
        Worker worker = workersRepo.get(Integer.parseInt(data.get("id")));
        if(worker == null)
            worker = DB.getWorkersDAO().read(Integer.parseInt(data.get("id")));
        if(worker != null){
            worker.updateWorker(data);
            DB.getWorkersDAO().update(data);
            return true;
        }
        return false;
    }

    public void deleteWorker(int id){
        Worker worker = workersRepo.get(id);
        if(worker != null) {
            workersRepo.remove(id);
        }
        DB.getWorkersDAO().delete(id);
    }
}
