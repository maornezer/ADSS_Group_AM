package Presentation;

import Domain.Branch;
import Domain.Chain;
import Domain.DomainController;

import java.time.LocalDate;
import java.util.Dictionary;
import java.util.List;

public class PresentationController {
    private DomainController domainController;

    public PresentationController() {
        domainController = new DomainController();
    }

    public void creatChain(Dictionary<String, String> data){
        if(data == null)
            MainMenu.creatChain();
        else{
            this.domainController.createChain(data);
        }
        creatScheduleForConfig();
    }

    public void addBranch(Dictionary<String, String> data){
        this.domainController.addBranch(data);
    }

    public boolean verification(Dictionary<String, String> data) {
        return domainController.verification(data);
    }

    // WorkerMenu
    public boolean submitWorkerLimits(Dictionary<String, String> data, int[][] workerLimits) {
        return this.domainController.submitWorkerLimits(data, workerLimits);
    }

    public LocalDate WorkerResignationNotice(Dictionary<String, String> data) {
        return this.domainController.WorkerResignationNotice(data);
    }


    public int[][] getBranchLimitation(int branchNum) {
        return this.domainController.getBranchLimitation(branchNum);
    }

    public LocalDate[] getDatesForNextWeek() {
        return this.domainController.getDatesForNextWeek();
    }


    public boolean checkBranchDeadLine(Dictionary<String, String> data) {
        return this.domainController.checkBranchDeadLine(data);
    }

    public boolean checkBranchDeadLinePassed(Dictionary<String, String> data) {
        return this.domainController.checkBranchDeadLine(data);
    }

    public void ChangeAmountTypeOfWorkersShift(Dictionary<String, String> data) {
        this.domainController.ChangeAmountTypeOfWorkersShift(data);
    }

    public void changeFirstName(Dictionary<String, String> data) {
        this.domainController.changeFirstName(data);
    }

    public void changeLastName(Dictionary<String, String> data) {
        this.domainController.changeLastName(data);
    }

    public void changeBankDetails(Dictionary<String, String> data) {
        this.domainController.changeBankDetails(data);
    }

    public void addRole(Dictionary<String, String> data) {
        this.domainController.addRole(data);
    }

    public void removeRole(Dictionary<String, String> data) {
        this.domainController.removeRole(data);
    }

    public void changeHourRate(Dictionary<String, String> data) {
        this.domainController.changeHourRate(data);
    }

    public void changeVacationDays(Dictionary<String, String> data) {
        this.domainController.changeVacationDays(data);
    }

    public void reduceVacationDays(Dictionary<String, String> data) {
        this.domainController.reduceVacationDays(data);
    }

    public void changeJobType(Dictionary<String, String> data) {
        this.domainController.changeJobType(data);
    }

    public LocalDate changingEndOfEmployment(Dictionary<String, String> data) {
        return this.domainController.changingEndOfEmployment(data);
    }

    public void hireWorker(Dictionary<String, String> data) {
        this.domainController.hireWorker(data);
    }

    public LocalDate fireEmployee(Dictionary<String, String> data) {
        return this.domainController.fireEmployee(data);
    }

    public boolean changeStartAndEndTime(Dictionary<String, String> data) {
        return this.domainController.changeStartAndEndTime(data);
    }

    public String ViewShiftHistory(Dictionary<String, String> data) {
        return this.domainController.ViewShiftHistory(data);
    }

    public boolean AddOrRemoveDaysOffWork(Dictionary<String, String> data) {
        return this.domainController.AddOrRemoveDaysOffWork(data);
    }

    public void ChangingDeadline(Dictionary<String, String> data) {
        this.domainController.ChangingDeadline(data);
    }

    public boolean ShiftsAssignment() {
        return this.domainController.ShiftsAssignment();
    }

    public String makeTomorrow() {
        return this.domainController.makeTomorrow();
    }

    public String PrintShiftsAssignment(Dictionary<String, String> data) {
        return this.domainController.PrintShiftsAssignment(data);
    }

    public void creatScheduleForConfig(){
        this.domainController.creatScheduleForConfig();
    }
}
