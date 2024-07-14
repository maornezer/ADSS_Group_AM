import Presentation.MainMenu;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){
//        if (createDB()) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.printMain();
//        } else {
//            logger.error("Failed to create or locate the database.");
//        }
    }

    private static boolean createDB() {
        // Ensure the 'src/resources/' directory exists
        File directory = new File("src/resources/");
        if (!directory.exists() && !directory.mkdirs()) {
            logger.error("Failed to create directory: " + directory.getPath());
            return false;
        }

        // Path to the SQLite database file
        String dbPath = "src/resources/identifier.sqlite";
        File dbFile = new File(dbPath);

        // If the database file does not exist, copy it from resources
        if (!dbFile.exists()) {
            try (InputStream in = Main.class.getResourceAsStream("/identifier.sqlite");
                 OutputStream out = new FileOutputStream(dbPath)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                return true;
            } catch (IOException e) {
                logger.error("Error creating database file: ", e);
                return false;
            }
        }
        return true;
    }
}





//import Presentation.MainMenu;
//
//import java.io.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class Main {
//    public static void main(String[] args){
////        createDB();
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.printMain();
//
//    }
//
//    private static boolean createDB() {
//        // if directory does not exist, create it
//        File directory = new File("src/");
//        if (!directory.exists())
//            directory.mkdir();
//        directory = new File("src/resources/");
//        if (!directory.exists())
//            directory.mkdir();
//
//        String dbPath = "src/resources/identifier.sqlite";
//        File dbFile = new File(dbPath);
//        if (!dbFile.exists()) {
//            try (InputStream in = Main.class.getResourceAsStream("/identifier.sqlite");
//                 OutputStream out = new FileOutputStream(dbPath)) {
//                byte[] buffer = new byte[1024];
//                int length;
//                while ((length = in.read(buffer)) > 0) {
//                    out.write(buffer, 0, length);
//                }
//                return true;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return true;
//    }
//
//}