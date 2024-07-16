package Domain;

import DAL.WorkersDAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class WorkersRepository {

    protected Dictionary<Integer, Worker> workersRepo;
    protected WorkersDAO workersDAO;


    public WorkersRepository(){
        workersRepo = new Hashtable<>();
        workersDAO = new WorkersDAO();
    }

    public Worker getWorker(int id){
        Worker worker = workersRepo.get(id);
        if(worker == null) {
            worker = workersDAO.read(id);
            workersRepo.put(id, worker);
        }
        return worker;
    }

    public List<Worker> getWorkersOfBranch(int branchNum){
        List<Worker> workerList = workersDAO.readWorkersByBranch(branchNum);
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
        Worker worker = workersDAO.create(data);
        if(worker != null){
            workersRepo.put(worker.getId(), worker);
            return worker;
        }
        return null;
    }

    public boolean updateWorker(Dictionary<String, String> data){
        Worker worker = workersRepo.get(Integer.parseInt(data.get("id")));
        if(worker == null)
            worker = workersDAO.read(Integer.parseInt(data.get("id")));
        if(worker != null){
            worker.updateWorker(data);
            workersDAO.update(data);
            return true;
        }
        return false;
    }

    public void deleteWorker(int id){
        Worker worker = workersRepo.get(id);
        if(worker != null) {
            workersRepo.remove(id);
        }
        workersDAO.delete(id);
    }

    public void deleteFiredWorkers() {
        LocalDate today = Chain.getToday();// delete all workers that date passed in table and delete from repo
        List<Integer> idsFiredWorker = workersDAO.deleteWorkersBeforeDate(today);
        for (Integer id : idsFiredWorker) {
            workersRepo.remove(id);
        }
    }
}