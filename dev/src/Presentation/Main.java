package Presentation;

import Presentation.TruckCLI;

import java.util.Scanner;

public class Main
{
    private Scanner scanner;
    private TruckCLI truckCLI;
    private SiteCLI siteCLII;

    //private ShippingUI shippingUI;


    public Main() {
        this.scanner = new Scanner(System.in);
        this.truckCLI = new TruckCLI();
        this.siteCLII = new SiteCLI();
        //this.shippingUI = new ShippingUI();

    }

    public void run() {
        System.out.println("Welcome to Super-Li Shipping control module!");
        while (true) {
            boolean exit = mainMenu();
            if (exit) {
                break;
            }
        }
    }

    private boolean mainMenu() {
        System.out.println("Please choose data to manage:");
        System.out.println("\t1. Manage Trucks");
        System.out.println("\t2. Manage Shipments");
        System.out.println("\t3. Manage Sites");
        System.out.println("\tX. Exit");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                truckCLI.run();
                return false;
            case "2":
                //shippingUI.run();
                return false;
            case "3":
                //siteCLII.run();
                return false;
            case "X":
                return true;
            default:
                System.out.println("Invalid choice. Please try again.");
                return false;
        }
    }

    public static void main(String[] args) {
        Main mainUI = new Main();
        mainUI.run();
    }
}
