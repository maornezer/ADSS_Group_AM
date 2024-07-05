package Presentation;

import java.io.*;

public class Main
{

    public static void main(String[] args) {
        createDB();
        Menu menu = new Menu();
        menu.printMenu();
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




