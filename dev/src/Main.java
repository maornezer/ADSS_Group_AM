import Domain.*;
import Presentation.MainMenu;
import Presentation.configurationForSystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Dictionary;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//      build chain from configuration file
        configurationForSystem temp = new configurationForSystem();
        MainMenu mainMenu = new MainMenu();
        mainMenu.printMain();

//
    }



//    public static void main(String[] args) throws IOException {
//        Chain chain = new Chain(100, "FN", "LN", 100, LocalDate.of(2024, 1, 1));
//        Dictionary<String, String > data = new Hashtable<>();
//        data.put("branchNum","1");
//        data.put("address", "Tel aviv");
//        data.put("deadLine", "THURSDAY");
//        chain.addBranch(data);
//
//        LocalDate[] dates = Chain.getNextWeekDates();
//
//        System.out.println("dates for the first week of system. should start at 7.1");
//
//        for (LocalDate date : dates) {
//            System.out.println(date.toString());
//        }
//        Branch telAviv = Chain.getBranch(1);
//
//        System.out.println("value of SATURDAY = " + DayOfWeek.SATURDAY.getValue());
//        System.out.println("value of SUNDAY = " + DayOfWeek.SUNDAY.getValue());
//        System.out.println("value of MONDAY = " + DayOfWeek.MONDAY.getValue());
//
//        System.out.println("my value of SATURDAY = " + Chain.getDayValue(DayOfWeek.SATURDAY));
//        System.out.println("my value of SUNDAY = " + Chain.getDayValue(DayOfWeek.SUNDAY));
//        System.out.println("my value of MONDAY = " + Chain.getDayValue(DayOfWeek.MONDAY));
//
////        telAviv.addShiftOffConst(new genShift(DayOfWeek.SATURDAY, 1));
////        telAviv.addShiftOffConst(new genShift(DayOfWeek.SATURDAY, 2));
//
//        telAviv.creatNextWeek();
//
//        int[][] TelAvivLimits = telAviv.getSystemLimitations().getNextWeekLimits();
//
//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 2; j++) {
//                if (TelAvivLimits[i][j] == -1) {
//                    System.out.println("Tel Aviv will not work at " + dates[i].toString());
//                }
//            }
//        }
//
////        telAviv.addShiftOffTemp(new genShift(DayOfWeek.SUNDAY, 1));
////
////        System.out.println("added a new temp shift off");
////
////        for (int i = 0; i < 7; i++) {
////            for (int j = 0; j < 2; j++) {
////                if (TelAvivLimits[i][j] == -1) {
////                    System.out.println("Tel Aviv will not work at " + dates[i].toString());
////                }
////            }
////        }
//
//
//        Branch bash = Chain.getBranch(2);
//        data = new Hashtable<>();
//        data.put("id", Integer.toString(101));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "Noa");
//        data.put("lastName", "Shvets");
//        data.put("bankDetails", Integer.toString(200));
//        data.put("hourRate", Integer.toString(300));
//        data.put("jobType", "1");
//        data.put("year", Integer.toString(2025));
//        data.put("month", Integer.toString(12));
//        data.put("day", Integer.toString(30));
//        data.put("amount","3");
//        data.put("1","Manager");
//        data.put("0", "Cashier");
//        data.put("2","Storekeeper" );
//
//
//        LocalDate date = LocalDate.of(Integer.parseInt(data.get("year")),Integer.parseInt(data.get("month")),
//                Integer.parseInt(data.get("day")));
//
//        System.out.println("date should be - 30-12-2010 : "+ date);
//
//        Worker worker1 = new Worker(data);
//        telAviv.addWorker(worker1);
//
//
////        int[][] workerLimit = worker1.getLimitations().getLimitations();
////
////        for (int i = 0; i < 7; i++) {
////            for (int j = 0; j < 2; j++) {
////                if (workerLimit[i][j] == -1) {
////                    System.out.println("Worker will not work at " + dates[i].toString());
////                }
////            }
////        }
//
////        telAviv.addShiftOffTemp(new genShift(DayOfWeek.SUNDAY, 2));
////
////        System.out.println("Added a new temp shift off");
//
////        for (int i = 0; i < 7; i++) {
////            for (int j = 0; j < 2; j++) {
////                if (TelAvivLimits[i][j] == -1) {
////                    System.out.println("Tel Aviv will not work at " + dates[i].toString());
////                }
////            }
////        }
//
////        workerLimit = worker1.getLimitations().getLimitations();
////
////        for (int i = 0; i < 7; i++) {
////            for (int j = 0; j < 2; j++) {
////                if (workerLimit[i][j] == -1) {
////                    System.out.println("Worker will not work at " + dates[i].toString());
////                }
////            }
////        }
//
//        System.out.println("today's date is = " + Chain.getToday().toString());
//        Chain.tomorrow();
//        System.out.println("today's date is = " + Chain.getToday().toString());
//        Chain.tomorrow();
//        System.out.println("today's date is = " + Chain.getToday().toString());
//        Chain.tomorrow();
//        System.out.println("today's date is = " + Chain.getToday().toString());
//        Chain.tomorrow();
//        System.out.println("today's date is = " + Chain.getToday().toString());
//        Chain.tomorrow();
//        System.out.println("today's date is = " + Chain.getToday().toString());
//        Chain.tomorrow();
//        System.out.println("today's date is = " + Chain.getToday().toString());
//        Chain.tomorrow();
//        System.out.println("today's date is = " + Chain.getToday().toString());
//
//        Chain.creatNextWeek();
//
//        TelAvivLimits = telAviv.getSystemLimitations().getNextWeekLimits();
//
//        dates = Chain.getNextWeekDates();
//
//        for (int i = 0; i < 7; i++) {
//            for (int j = 0; j < 2; j++) {
//                if (TelAvivLimits[i][j] == -1) {
//                    System.out.println("Tel Aviv will not work at " + dates[i].toString());
//                }
//            }
//        }
//
////        workerLimit = worker1.getLimitations().getLimitations();
//
////        for (int i = 0; i < 7; i++) {
////            for (int j = 0; j < 2; j++) {
////                if (workerLimit[i][j] == -1) {
////                    System.out.println("Worker will not work at " + dates[i].toString());
////                }
////            }
////        }
////
////        workerLimit[0][1] = 0;
////        workerLimit[0][0] = 0;
////        workerLimit[1][1] = 0;
////
////        worker1.setLimitations(workerLimit);
////
////
////        for (int i = 0; i < 7; i++) {
////            for (int j = 0; j < 2; j++) {
////                if (workerLimit[i][j] == -1) {
////                    System.out.println("branch will not work at " + dates[i].toString());
////                }
////                if (workerLimit[i][j] == 0) {
////                    System.out.println("worker will not work at " + dates[i].toString());
////                }
////            }
////        }
//
//        System.out.println(worker1);
//
//        data.put("id", Integer.toString(102));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "lee");
//        data.put("lastName", "Chemo");
//
//        Worker worker2 = new Worker(data);
//        telAviv.addWorker(worker2);
//
//        data.put("id", Integer.toString(103));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "Tal");
//        data.put("lastName", "Cohen");
//        data.put("bankDetails", Integer.toString(500));
//        data.put("hourRate", Integer.toString(400));
//        data.put("jobType", "2");
//        data.put("year", Integer.toString(2025));
//        data.put("month", Integer.toString(10));
//        data.put("day", Integer.toString(12));
//        Worker worker3 = new Worker(data);
//
//        data.put("id", Integer.toString(104));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "Gal");
//        data.put("lastName", "Kirel");
//
//        Worker worker4 = new Worker(data);
//
//        data.put("id", Integer.toString(105));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "Shimon");
//        data.put("lastName", "Hemo");
//
//        Worker worker5 = new Worker(data);
//
//        data.put("id", Integer.toString(106));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "Rahel");
//        data.put("lastName", "Giladi");
//
//        Worker worker6 = new Worker(data);
//
//        data.put("id", Integer.toString(107));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "Yaron");
//        data.put("lastName", "hemo");
//
//        Worker worker7 = new Worker(data);
//
//        data.put("id", Integer.toString(108));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "yossi");
//        data.put("lastName", "hemo");
//
//        Worker worker8 = new Worker(data);
//
//        data.put("id", Integer.toString(109));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "levana");
//        data.put("lastName", "leibel");
//
//        Worker worker9 = new Worker(data);
//
//        data.put("id", Integer.toString(110));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "guy");
//        data.put("lastName", "leibel");
//
//        Worker worker10 = new Worker(data);
//
//        data.put("id", Integer.toString(111));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "idan");
//        data.put("lastName", "leibel");
//
//        Worker worker11 = new Worker(data);
//
//        data.put("id", Integer.toString(112));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "roei");
//        data.put("lastName", "asraf");
//
//        Worker worker12 = new Worker(data);
//
//        data.put("id", Integer.toString(113));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "peleg");
//        data.put("lastName", "asraf");
//
//        Worker worker13 = new Worker(data);
//
//        data.put("id", Integer.toString(114));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "moti");
//        data.put("lastName", "leibel");
//
//        Worker worker14 = new Worker(data);
//
//
//        data.put("id", Integer.toString(115));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "reut");
//        data.put("lastName", "leibel");
//
//        Worker worker15 = new Worker(data);
//
//        data.put("id", Integer.toString(116));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "yoni");
//        data.put("lastName", "leibel");
//
//        Worker worker16 = new Worker(data);
//        telAviv.addWorker(worker3);
//        telAviv.addWorker(worker4);
//        telAviv.addWorker(worker5);
//        telAviv.addWorker(worker6);
//        telAviv.addWorker(worker7);
//        telAviv.addWorker(worker8);
//        telAviv.addWorker(worker9);
//        telAviv.addWorker(worker10);
//        telAviv.addWorker(worker11);
//        telAviv.addWorker(worker12);
//        telAviv.addWorker(worker13);
//        telAviv.addWorker(worker14);
//        telAviv.addWorker(worker15);
//        telAviv.addWorker(worker16);
//
//        System.out.println(telAviv);
//        int[][] limits = new int[7][2];
//        for (int[] day : limits){
//            for(int shift: day){
//                shift = 1;
//            }
//        }
//
//        worker1.setLimitations(limits);
//
//
//        for (int[] day : limits){
//            for(int j = 0; j<2; j++){
//                day[j] = j;
//            }
//        }
//
//        worker2.setLimitations(limits);
//        worker4.setLimitations(limits);
//        worker6.setLimitations(limits);
//        worker8.setLimitations(limits);
//
//
//
//        for (int[] day : limits){
//            for(int j = 0; j<2; j++){
//                day[j] = (j+1) %2 ;
//            }
//        }
//
//        worker3.setLimitations(limits);
//        worker5.setLimitations(limits);
//        worker7.setLimitations(limits);
//        worker9.setLimitations(limits);
//
//        MainMenu menu = new MainMenu();
//        menu.printMain();
//    }
}