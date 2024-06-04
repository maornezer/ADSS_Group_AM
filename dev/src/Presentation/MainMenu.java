package Presentation;

import java.time.LocalDate;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class MainMenu {

    private Scanner scanner;
    private PresentationController presentationController;

    public void makeTomorrow(){
        String date = this.presentationController.makeTomorrow();
        System.out.println("today's date is - " + date);
    }

    public MainMenu() {
        this.scanner = new Scanner(System.in);
        this.presentationController = new PresentationController();
    }

    public void printMain(){
        Dictionary<String, String> data = new Hashtable<>();
        do {
            System.out.println("Enter branch Number: ");
            int branchNum = this.scanner.nextInt();
            System.out.println("Enter worker ID: ");
            int id = this.scanner.nextInt();
            data.put("branchNum", Integer.toString(branchNum));
            data.put("id", Integer.toString(id));
        } while (!this.presentationController.verification(data));
        if(data.get("branchNum").equals("0")){
            HRMenu();
        }
        else{
            WorkerMenu(data);
        }
    }

    public void WorkerMenu(Dictionary<String, String> data){
        int choice = 0;
        do {
            do {
                System.out.println("""
                        Please choose one of the following options:
                        1. Entering constraints for the next week
                        2. Notice of resignation
                        3. Print Shifts assignment
                        4. Back to the main menu
                        Your choice is:""");
                choice = this.scanner.nextInt();
            } while (choice > 4 || choice < 1);
            if (choice == 1) {
                WorkerLimitSubmissions(data);
            }
            if (choice == 2) {
                LocalDate temp = this.presentationController.WorkerResignationNotice(data);
                System.out.println("Resignation notice successfully received," +
                        " job termination date set for " + temp.toString());
            }
            if(choice == 3){
                PrintShiftsAssignmentWorker(data);
            }
        }while (choice != 4);
        printMain();
    }

    public void WorkerLimitSubmissions(Dictionary<String, String> data){
        if (this.presentationController.checkDeadLine(data)) {
            int[][] workerLimit = this.presentationController.getBranchLimitation(Integer.parseInt(data.get("branchNum")));
            LocalDate[] datesForNextWeek = this.presentationController.getDatesForNextWeek();
            for (int i = 0; i < 7; i++) {
                if (workerLimit[i][0] == 1) {
                    System.out.println("can you work morning " + datesForNextWeek[i].toString() + "?\n" +
                            "Enter 1 for confirmation, otherwise 0:");
                    workerLimit[i][0] = this.scanner.nextInt();
                }
                if (workerLimit[i][1] == 1) {
                    System.out.println("can you work evening " + datesForNextWeek[i].toString() + "?\n" +
                            "Enter 1 for confirmation, otherwise 0:");
                    workerLimit[i][1] = this.scanner.nextInt();
                }
            }
            if (this.presentationController.submitWorkerLimits(data, workerLimit))
                System.out.println("Submitted successfully");
        }
        else {
            System.out.println("Deadline passed, shifts cannot be submitted");
        }
    }

    public void PrintShiftsAssignmentWorker(Dictionary<String, String> data){
        String res = this.presentationController.PrintShiftsAssignment(data);
        System.out.println(res);
    }

    public void HRMenu() {
        int choice = 0;
        do {
            do {
                System.out.println("What action would you like to do:\n" +
                        "1. Changes in employee details\n" +
                        "2. Hiring/firing an employee\n" +
                        "3. Actions on a branch\n" +
                        "4. creat next week Shifts assignment\n" +
                        "5. Move on to tomorrow\n"+
                        "6. Back to the main menu\n" +
                        "Enter your choice here:");
                choice = this.scanner.nextInt();
            } while (choice > 6 || choice < 1);
            switch (choice) {
                case 1:
                    changeWorkerDetails();
                    break;
                case 2:
                    HiringOrFiringAnEmployee();
                    break;
                case 3:
                    ChangesInBranch();
                    break;
                case 4:
                    ShiftsAssignment();
                    break;
                case 5:
                    makeTomorrow();
                    break;
                default:
                    break;
            }
        }while (choice != 6);
        printMain();
    }

    public void changeWorkerDetails(){
        Dictionary<String, String> data = new Hashtable<>();
        do {
            System.out.println("Enter branch Number of worker: ");
            int branchNum = this.scanner.nextInt();
            System.out.println("Enter worker ID: ");
            int id = this.scanner.nextInt();
            data.put("branchNum", Integer.toString(branchNum));
            data.put("id", Integer.toString(id));
        } while (!this.presentationController.verification(data));
        System.out.println("What changes would you like to do:\n" +
                "1. Change first name\n" +
                "2. Change last name\n" +
                "3. Change bank details\n" +
                "4. Adding a role to the employee\n" +
                "5. Deleting a role from the employee\n" +
                "6. Hourly wage change\n" +
                "7. Changing the amount of vacation days\n" +
                "8. Reduce vacation days\n" +
                "9. Changing job type\n" +
                "10. Changing end of employment\n" +
                "11. Back to the main menu\n"+
                "Enter your choice here:");
        int choice = this.scanner.nextInt();
        switch (choice){
            case 1:
                changeFirstName(data);
                break;
            case 2:
                changeLastName(data);
                break;
            case 3:
                changeBankDetails(data);
                break;
            case 4:
                addRole(data);
                break;
            case 5:
                removeRole(data);
                break;
            case 6:
                changeHourRate(data);
                break;
            case 7:
                changeVacationDays(data);
                break;
            case 8:
                reduceVacationDays(data);
                break;
            case 9:
                changeJobType(data);
                break;
            case 10:
                changingEndOfEmployment(data);
                break;
            default:
                return;
        }
    }

    public void HiringOrFiringAnEmployee() {
        System.out.println("What action would you like to do:\n" +
                "1. Hire an employee\n" +
                "2. fire an employee\n" +
                "3. Back to the main menu\n" +
                "Enter your choice here:");
        int choice = this.scanner.nextInt();
        switch (choice) {
            case 1:
                hireWorker();
                break;
            case 2:
                fireEmployee();
                break;
            default:
                return;
        }
    }

    public void ChangesInBranch(){
        System.out.println("What action would you like to do:\n" +
                "1. View shift history\n" +
                "2. Changing the start or end time of a shift\n" +
                "3. Adding or deleting days without work at a branch\n" +
                "4. Change in the amount and type of workers in the shift\n" +
                "5. Changing the deadline for submitting employee constraints\n" +
                "6. Print shifts assignment for this week\n" +
                "7. Back to the main menu\n" +
                "Enter your choice here:");
        int choice = this.scanner.nextInt();
        switch (choice) {
            case 1:
                ViewShiftHistory();
                break;
            case 2:
                changeStartAndEndTime();
                break;
            case 3:
                AddOrRemoveDaysOffWork();
                break;
            case 4:
                ChangeAmountTypeOfWorkersShift();
                break;
            case 5:
                ChangingDeadline();
                break;
            case 6:
                PrintShiftsAssignment();
                break;
            default:
                return;
        }

    }

    public void ShiftsAssignment(){
        if(this.presentationController.ShiftsAssignment())
            System.out.println("Shifts assignment was made successfully");
        else{
            System.out.println("The deadline hasn't passed yet");
        }
    }


//    changeWorkerDetails

    public void changeFirstName(Dictionary<String, String> data){
        System.out.println("Enter new first name:");
        this.scanner.skip("\\R?");
        String newName = this.scanner.nextLine();
        data.put("newName", newName);
        this.presentationController.changeFirstName(data);
        System.out.println("First name changed successfully");
    }

    public void changeLastName(Dictionary<String, String> data){
        System.out.println("Enter new last name:");
        this.scanner.skip("\\R?");
        String newName = this.scanner.nextLine();
        data.put("newName", newName);
        this.presentationController.changeLastName(data);
        System.out.println("Last name changed successfully");
    }

    public void changeBankDetails(Dictionary<String, String> data){
        System.out.println("Enter new bank details : ");
        int newBank = this.scanner.nextInt();
        data.put("newBank", Integer.toString(newBank));
        this.presentationController.changeBankDetails(data);
        System.out.println("Bank details changed successfully");
    }

    public void addRole(Dictionary<String, String> data){
        String newRole = "";
        System.out.println("Enter new role to add : \n" +
                "1. Shift Manager\n" +
                "2. Cashier" +
                "3. Storekeeper\n" +
                "4. Another role");
        int choice = this.scanner.nextInt();
        if(choice == 4) {
            this.scanner.skip("\\R?");
            newRole = this.scanner.nextLine();
        }
        else{
            if(choice == 1)
                newRole = "Shift Manager";
            if(choice == 2)
                newRole = "Cashier";
            if(choice ==3)
                newRole = "Storekeeper";
        }
        data.put("newRole", newRole);
        this.presentationController.addRole(data);
        System.out.println("role added successfully");
    }

    public void removeRole(Dictionary<String, String> data){
        System.out.println("Enter role to remove : ");
        this.scanner.skip("\\R?");
        String RoleToRemove = this.scanner.nextLine();
        data.put("RoleToRemove", RoleToRemove);
        boolean flag = this.presentationController.removeRole(data);
        if(flag){
            System.out.println("role removed successfully");
        }
        else{
            System.out.println("Role does not exist for this employee");
        }
    }

    public void changeHourRate(Dictionary<String, String> data){
        System.out.println("Enter updated hourly wage : ");
        int newSalary = this.scanner.nextInt();
        data.put("newSalary", Integer.toString(newSalary));
        this.presentationController.changeHourRate(data);
        System.out.println("Hourly wage changed successfully");
    }

    public void changeVacationDays(Dictionary<String, String> data){
        System.out.println("Enter new amount of vacation days: ");
        int newAmount = this.scanner.nextInt();
        data.put("newAmount", Integer.toString(newAmount));
        this.presentationController.changeVacationDays(data);
        System.out.println("Vacation days changed successfully");
    }

    public void reduceVacationDays(Dictionary<String, String> data) {
        System.out.println("Enter amount of used vacation days : ");
        int newAmount = this.scanner.nextInt();
        data.put("newAmount", Integer.toString(newAmount));
        this.presentationController.reduceVacationDays(data);
        System.out.println("Vacation days changed successfully");
    }

    public void changeJobType(Dictionary<String, String> data) {
        System.out.println("""
                1 - Full Time Job\s
                2 - Part Time Job\s
                Enter wanted job type :
                """);
        int newType = this.scanner.nextInt();
        data.put("newType", Integer.toString(newType));
        this.presentationController.changeJobType(data);
        System.out.println("Job type changed successfully");
    }

    public void changingEndOfEmployment(Dictionary<String, String> data) {
        System.out.println("Enter the new date for end of employment : " +
                "Enter the year: ");
        int year = this.scanner.nextInt();
        System.out.println("\nEnter the month: ");
        int month = this.scanner.nextInt();
        System.out.println("\nEnter the day: ");
        int day = this.scanner.nextInt();
        data.put("year", Integer.toString(year));
        data.put("month", Integer.toString(month));
        data.put("day", Integer.toString(day));
        LocalDate date = this.presentationController.changingEndOfEmployment(data);
        System.out.println("End of employment changed successfully to: " + date);
    }

//    HiringOrFiringAnEmployee

    public void fireEmployee(){
        Dictionary<String, String> data = new Hashtable<>();
        System.out.println("Enter branch Number of worker to fire: ");
        int branchNum = this.scanner.nextInt();
        System.out.println("Enter worker ID to fire: ");
        int id = this.scanner.nextInt();
        data.put("branchNum", Integer.toString(branchNum));
        data.put("id", Integer.toString(id));
        LocalDate date = this.presentationController.fireEmployee(data);
        if(date != null)
            System.out.println("The employee was successfully fired - end of employment is set to " + date);
        else{
            System.out.println("The employee does not exist");
        }
    }

    public void hireWorker(){
        Dictionary<String, String> data = new Hashtable<>();
        System.out.println("Enter branch Number of new worker: ");
        int branchNum = this.scanner.nextInt();
        System.out.println("Enter worker ID: ");
        int id = this.scanner.nextInt();
        System.out.println("Enter worker first name:");
        this.scanner.skip("\\R?");
        String firstName = this.scanner.nextLine();
        System.out.println("Enter worker last name:");
        this.scanner.skip("\\R?");
        String lastName = this.scanner.nextLine();
        System.out.println("Enter bank details: ");
        int bankDetails = this.scanner.nextInt();
        System.out.println("Enter hour rate:");
        int hourRate = this.scanner.nextInt();
        System.out.println("Enter job type:");
        this.scanner.skip("\\R?");
        String jobType = this.scanner.nextLine();
        System.out.println("Enter date for end of employment : \n" + "Enter the year: ");
        int year = this.scanner.nextInt();
        System.out.println("\nEnter the month: ");
        int month = this.scanner.nextInt();
        System.out.println("\nEnter the day: ");
        int day = this.scanner.nextInt();
        System.out.println("\nEnter the amount of roles to add to this employee:");
        int amount = this.scanner.nextInt();
        data.put("amount", Integer.toString(amount));
        for(int i =0; i<amount ; i++){
            System.out.println("\nEnter role to add:");
            this.scanner.skip("\\R?");
            String role = this.scanner.nextLine();
            data.put(Integer.toString(i),role);
        }
        data.put("branchNum",Integer.toString(branchNum));
        data.put("firstName",firstName);
        data.put("lastName",lastName);
        data.put("bankDetails", Integer.toString(bankDetails));
        data.put("hourRate", Integer.toString(hourRate));
        data.put("jobType", jobType);
        data.put("year", Integer.toString(year));
        data.put("month", Integer.toString(month));
        data.put("day", Integer.toString(day));
        this.presentationController.hireWorker(data);
        System.out.println("Worker added successfully");
    }

//    ChangesInBranch

    public void ViewShiftHistory(){
        Dictionary<String, String> data = new Hashtable<>();
        System.out.println("Enter branch Number: ");
        int branchNum = this.scanner.nextInt();
        data.put("branchNum", Integer.toString(branchNum));
        String branchHistory = this.presentationController.ViewShiftHistory(data);
        System.out.println(branchHistory);
    }

    public void changeStartAndEndTime(){
        Dictionary<String, String> data = new Hashtable<>();
        System.out.println("Enter branch Number: ");
        int branchNum = this.scanner.nextInt();
        System.out.println("Enter day to change shift time at\n " +
                "1. Sunday\n" +
                "2. Monday\n" +
                "3. Tuesday\n" +
                "4. Wednesday\n" +
                "5. Thursday\n" +
                "6. Friday\n" +
                "7. Saturday");
        int day = this.scanner.nextInt();
        System.out.println("Enter shift to change time at\n " +
                "1. Morning\n" +
                "2. Evening" );
        int shift = this.scanner.nextInt();
        System.out.println("Enter start time for shift:");
        int start = this.scanner.nextInt();
        System.out.println("Enter end time for shift:");
        int end = this.scanner.nextInt();
        data.put("branchNum", Integer.toString(branchNum));
        data.put("day", Integer.toString(day));
        data.put("shift",Integer.toString(shift));
        data.put("start",Integer.toString(start));
        data.put("end",Integer.toString(end));
        if(this.presentationController.changeStartAndEndTime(data))
            System.out.println("Start and end time changed successfully.");
        else
            System.out.println("the branch is not working on this day.");
    }

    public void AddOrRemoveDaysOffWork(){
        Dictionary<String, String> data = new Hashtable<>();
        System.out.println("Enter branch Number: ");
        int branchNum = this.scanner.nextInt();
        data.put("branchNum", Integer.toString(branchNum));
        if(this.presentationController.checkBranchDeadLine(data)) {
            System.out.println("Enter the day to add or remove off shift at:\n " +
                    "1. Sunday\n" +
                    "2. Monday\n" +
                    "3. Tuesday\n" +
                    "4. Wednesday\n" +
                    "5. Thursday\n" +
                    "6. Friday\n" +
                    "7. Saturday");
            int day = this.scanner.nextInt();
            System.out.println("Enter shift to change at\n " +
                    "1. Morning\n" +
                    "2. Evening");
            int shift = this.scanner.nextInt();
            System.out.println("Enter the change you would like to do:\n 1. cancel shift\n 2. create shift");
            int action = this.scanner.nextInt();
            data.put("day", Integer.toString(day));
            data.put("shift", Integer.toString(shift));
            data.put("action", Integer.toString(action));
            if (this.presentationController.AddOrRemoveDaysOffWork(data))
                System.out.println("Change was made Successfully");
            else
                System.out.println("Branch number does not exist");
        }
        else
            System.out.println("Changes are not possible for next week, Schedule is posted.");
    }

    public void ChangeAmountTypeOfWorkersShift(){
        Dictionary<String, String> data = new Hashtable<>();
        System.out.println("Enter branch Number: ");
        int branchNum = this.scanner.nextInt();
        data.put("branchNum", Integer.toString(branchNum));
        if(this.presentationController.checkDeadLine(data)) {
            System.out.println("Enter the day to change workers needed in shift:\n " +
                    "1. Sunday\n" +
                    "2. Monday\n" +
                    "3. Tuesday\n" +
                    "4. Wednesday\n" +
                    "5. Thursday\n" +
                    "6. Friday\n" +
                    "7. Saturday");
            int day = this.scanner.nextInt();
            System.out.println("Enter shift to change at\n " +
                    "1. Morning\n" +
                    "2. Evening");
            int shift = this.scanner.nextInt();
            data.put("branchNum", Integer.toString(branchNum));
            data.put("day", Integer.toString(day));
            data.put("shift", Integer.toString(shift));
            System.out.println("Enter the new amount of workers in shift (do not include shift manager): ");
            int amount = this.scanner.nextInt();
            data.put("amount", Integer.toString(amount));
            for (int i = 0; i < amount; i++) {
                System.out.println("Enter role: ");
                this.scanner.skip("\\R?");
                String role = this.scanner.nextLine();
                data.put(Integer.toString(i), role);
            }
            this.presentationController.ChangeAmountTypeOfWorkersShift(data);
        }
        else
            System.out.println("Changes are not possible for next week, Schedule is posted.");
    }

    public void ChangingDeadline() {
        Dictionary<String, String> data = new Hashtable<>();
        System.out.println("Enter branch Number: ");
        int branchNum = this.scanner.nextInt();
        data.put("branchNum", Integer.toString(branchNum));
        if (!this.presentationController.checkDeadLine(data)) {
            System.out.println("Enter the new DeadLine:\n " +
                    "1. Sunday\n" +
                    "2. Monday\n" +
                    "3. Tuesday\n" +
                    "4. Wednesday\n" +
                    "5. Thursday\n" +
                    "6. Friday\n" +
                    "7. Saturday");
            int day = this.scanner.nextInt();
            data.put("branchNum", Integer.toString(branchNum));
            data.put("day", Integer.toString(day));
            if (this.presentationController.ChangingDeadline(data))
                System.out.println("Change was made Successfully");
            else
                System.out.println("change was not Successful");
        }
        else
            System.out.println("Changes are not possible for this week, may cause problems. try changing after current deadline passes");
    }

    public void PrintShiftsAssignment(){
        Dictionary<String, String> data = new Hashtable<>();
        System.out.println("Enter branch Number: ");
        int branchNum = this.scanner.nextInt();
        data.put("branchNum", Integer.toString(branchNum));
        String res = this.presentationController.PrintShiftsAssignment(data);
        System.out.println(res);
    }
}
