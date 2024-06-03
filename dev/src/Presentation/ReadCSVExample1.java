package Presentation;
import Presentation.PresentationController;

import java.io.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class ReadCSVExample1 {

    private PresentationController presentationController;

    public ReadCSVExample1() {
        presentationController = new PresentationController();
    }

    public void main(String[] args) throws Exception {
//parsing a CSV file into Scanner class constructor
        Scanner sc = new Scanner(new File("C:\\Users\\lilo5\\OneDrive\\שולחן העבודה\\configuration.csv"));
        sc.useDelimiter(",");   //sets the delimiter pattern

        while (sc.hasNext())  //returns a boolean value
        {

            String line = sc.nextLine();

            // Check if the line starts with a specific word
            if (line.startsWith("HR Manager")) {
                line = sc.nextLine();
                String[] details = line.split(","); // Split the line into details using comma as delimiter

                Dictionary<String, String> HRDict = new Hashtable<String, String>();
                HRDict.put("id", details[0]);
                HRDict.put("firstName", details[1]);
                HRDict.put("lastName", details[2]);
                HRDict.put("bankDetails", details[3]);
                HRDict.put("year", details[4]);
                HRDict.put("month", details[5]);
                HRDict.put("day", details[6]);


            }


            else if (line.startsWith("Branches")) {
                line = sc.nextLine();
                String[] details = line.split(","); // Split the line into details using comma as delimiter

                    Dictionary<String, String> BranchesDict = new Hashtable<String, String>();
                    BranchesDict.put("branchNum", details[0]);
                    BranchesDict.put("address", details[1]);
                    BranchesDict.put("branchDeadLine", details[2]);

                }



            else if (line.startsWith("Workers")) {
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    String[] details = line.split(","); // Split the line into details using comma as delimiter

                    Dictionary<String, String> dataDict = new Hashtable<String, String>();
                    dataDict.put("branchNum", details[0]);
                    dataDict.put("id", details[1]);
                    dataDict.put("firstName", details[2]);
                    dataDict.put("lastName", details[3]);
                    dataDict.put("bankDetails", details[4]);
                    dataDict.put("hourRate", details[5]);
                    dataDict.put("jobType", details[6]);
                    dataDict.put("year", details[7]);
                    dataDict.put("month", details[8]);
                    dataDict.put("day", details[9]);
                    dataDict.put("amount", details[10]);
                    dataDict.put("0", details[11]);
                    dataDict.put("1", details[12]);

                    this.presentationController.hireWorker(dataDict);

//                    for (String detail : details) {
//                        System.out.println(detail);
//                    }
                }
            }
        }
        sc.close();  //closes the scanner

    }
}




