import Presentation.MainMenu;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.printMain();

        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            System.out.println("kuku");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM new_table");

//            while(resultSet.next()){
//                System.out.println(resultSet.getString("userName"));
//                System.out.println(resultSet.getString("password"));
//            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("kuku1");

        }
    }

}