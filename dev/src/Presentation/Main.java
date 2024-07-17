package Presentation;


import java.io.*;
import java.util.Scanner;


public class Main
{
    private static Scanner scanner;

    public static void main(String[] args) {

        createDB();
        TransportMenu menuT = new TransportMenu();
        WorkerMenu menuW = new WorkerMenu();
        PresentationController controller = new PresentationController();
        scanner = new Scanner(System.in);
        System.out.println("Welcome to Super-Li :)");
        System.out.println("Please enter which module you want");
        System.out.println("1. Transport module");
        System.out.println("2. Employee module");
        System.out.println("0. Exit");
        scanner.skip("\\R?");
        String module =  scanner.nextLine();
        switch (module) {
            case "1":
                menuT.printMenu(scanner, controller);
                break;
            case "2":
                WorkerMenu.creatChain(scanner, controller);
                break;
            case "0":
                System.out.println("Exiting the program. Goodbye!");
                scanner.close();
                System.exit(0);
                break;
        }
    }

    private static boolean createDB() {
        // if directory does not exist, create it
        File directory = new File("src/");
        if (!directory.exists())
            directory.mkdir();
        directory = new File("src/resources/");
        if (!directory.exists())
            directory.mkdir();

        String dbPath = "src/resources/Transports.sqlite";
        File dbFile = new File(dbPath);
        if (!dbFile.exists()) {
            try (InputStream in = Main.class.getResourceAsStream("/Transports.sqlite");
                 OutputStream out = new FileOutputStream(dbPath)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}




