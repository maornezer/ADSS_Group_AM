//package Presentation;
//import Domain.*;
//
//import java.util.Arrays;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class TruckCLI
//{
//    TruckController tr = TruckController.getInstance();
//    public void run() {
//        int choose = -1;
//        boolean bool = false;
//        String[] validInput = {"0", "1", "2", "3","4"};
//        while (!bool)
//        {
//                System.out.println("Welcome, I'm the truck driver, what would you like to do?:");
//                System.out.println("Click 1 to add a new truck.");
//                System.out.println("Click 2 to Search for a specific truck.");
//                System.out.println("Click 3 to remove truck");
//                System.out.println("Click 4 to view all current trucks.");
//                System.out.println("Click 0 to Exit");
//                //choose =
//                switch (choose)
//                {
//                    case 0:
//                        bool = true;
//                        break;
//                    case 1:
//                        addTruckCli();
//                        break;
//                    case 2:
//                        searchTruckCli();
//                        break;
//                    case 3:
//                        removeTruckCli();
//                    case 4:
//                        viewAllTrucksCli();
//                        break;
//                }
//        }
//    }
//    private void addTruckCli() {
//        Scanner scanner = null;
//        System.out.println("Enter truck id");
//        int idTruck = Integer.parseInt(scanner.nextLine());
//        System.out.println("Enter truck initial weight (kg)\n");
//        double initialWeight = Double.parseDouble(scanner.nextLine());
//        System.out.println("Enter how much the truck can carry (Max weight) (kg)\n");
//        double maxWeight = Double.parseDouble(scanner.nextLine());
//        System.out.println("Enter Truck model.\n");
//        String model = scanner.nextLine();
//
//        boolean added = tr.addTruck(idTruck, initialWeight, maxWeight, model);
//        if (added) {
//            System.out.println("Truck added successfully.");
//        } else {
//            System.out.println("");
//        }
//    }
//    private void searchTruckCli()
//    {
//        Scanner scanner = null;
//        System.out.println("Enter truck ID to search:");
//        int idTruck = Integer.parseInt(scanner.nextLine());
//
//        Truck truck = tr.getTruckByID(idTruck);
//        if (truck != null) {
//            System.out.println(truck.toString());
//        } else {
//            System.out.println("Truck with ID " + idTruck + " not found.");
//        }
//    }
//
//    private void removeTruckCli()
//    {
//        Scanner scanner = null;
//        System.out.println("Enter truck ID to remove:");
//        int idTruck = Integer.parseInt(scanner.nextLine());
//
//        boolean success = tr.removeTruckByIDTruck(idTruck);
//        if (success)
//        {
//            System.out.println("Truck removed successfully.");
//        }
//        else
//        {
//            System.out.println("Truck with ID " + idTruck + " cannot be removed.");
//        }
//    }
//
//    private void viewAllTrucksCli()
//    {
//        System.out.println("Currently, a total of " + tr.getAmountOfTrucks() + " trucks are listed.\n");
//        if (tr.getAmountOfTrucks() > 0) {
//            System.out.println(tr.toString());
//        }
//    }
//}
