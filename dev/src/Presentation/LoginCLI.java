package Presentation

import java.util.Scanner;

public class LoginUI {
    private Scanner scanner;
    private Main main;
    //private TransportManagerUI transportManagerUI;
    //private DriverUI driverUI;

    public LoginUI() {
        this.scanner = new Scanner(System.in);
        this.main = new Main();
        //this.transportManagerUI = new TransportManagerUI();
        //this.driverUI = new DriverUI();
    }

    public void run() {
        loadData();
        boolean terminate = false;
        while (!terminate) {
            System.out.println("Hello There! \nPress the number to:");
            System.out.println("1. Register as Transport Manager");
            System.out.println("2. Register as Driver");
            System.out.println("3. Login");
            System.out.println("0. Exit");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    registerTransportManager();
                    break;
                case "2":
                    registerDriver();
                    break;
                case "3":
                    login();
                    break;
                case "0":
                    terminate = true;
                    break;
                default:
                    System.out.println("Please enter valid input!");
            }
        }
    }

//    private void loadData() {
//        Response res = ServiceFactory.getInstance().loadData();
//        if (res.isErrorResponse()) {
//            System.out.println(res.ErrorMsg);
//        }
//    }

    private void registerTransportManager() {
        System.out.print("Enter your ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        Response res = userService.registerTransportManager(id, name, password);
        if (!res.isErrorResponse()) {
            System.out.println("Registration successful!");
        } else {
            System.out.println(res.ErrorMsg);
        }
    }

    private void registerDriver() {
        System.out.print("Enter your ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        Response res = userService.registerDriver(id, name, password);
        if (!res.isErrorResponse()) {
            System.out.println("Registration successful!");
        } else {
            System.out.println(res.ErrorMsg);
        }
    }

    private void login() {
        System.out.println("Please login");
        System.out.print("Enter your ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Response res = userService.login(id, password);
        if (!res.isErrorResponse()) {
            if (userService.isTransportManager(id)) {
                transportManagerUI.run();
            } else if (userService.isDriver(id)) {
                driverUI.run();
            } else {
                System.out.println("Invalid user role.");
            }
        } else {
            System.out.println(res.ErrorMsg);
        }
    }

    public static void main(String[] args) {
        LoginUI loginUI = new LoginUI();
        loginUI.run();
    }
}
