//package Presentation;
//
//import Domain.Branch;
//import Domain.Chain;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.nio.file.Paths;
//import java.util.Dictionary;
//import java.util.Hashtable;
//import java.util.Scanner;
//
//public class configurationForSystem {
//    private PresentationController presentationController;
//
//    public configurationForSystem() {
//        presentationController = new PresentationController();
//    }
//
//    public void readCSV() throws FileNotFoundException {
//        //parsing a CSV file into Scanner class constructor
//        String filePath = getPath();
//        Scanner sc = new Scanner(new File(filePath));
//        sc.useDelimiter(",");   //sets the delimiter pattern
//        Dictionary<String, String> HRDict = new Hashtable<>();
//        while (sc.hasNext())  //returns a boolean value
//        {
//
//            String line = sc.nextLine();
//
//            // Check if the line starts with a specific word
//            if (line.startsWith("HR Manager")) {
//                line = sc.nextLine();
//                String[] details = line.split(","); // Split the line into details using comma as delimiter
//
//                HRDict.put("id", details[0]);
//                HRDict.put("firstName", details[1]);
//                HRDict.put("lastName", details[2]);
//                HRDict.put("bankDetails", details[3]);
//                HRDict.put("year", details[4]);
//                HRDict.put("month", details[5]);
//                HRDict.put("day", details[6]);
//
//
//            }
//            this.presentationController.creatChain(HRDict);
//
//            if (line.startsWith("Branches")) {
//                while (sc.hasNextLine()) {
//                    line = sc.nextLine();
//                    if (line.startsWith("Workers")) {
//                        break;  // Break the loop if the next line starts with "Workers"
//                    }
//                    String[] details = line.split(","); // Split the line into details using comma as delimiter
//
//
//                    Dictionary<String, String> BranchesDict = new Hashtable<String, String>();
//                    BranchesDict.put("branchNum", details[0]);
//                    BranchesDict.put("address", details[1]);
//                    BranchesDict.put("deadLine", details[2]);
//
//                    this.presentationController.addBranch(BranchesDict);
//
//                }
//            }
//
//            this.presentationController.creatScheduleForConfig();
//
//
//            if (line.startsWith("Workers")) {
//                while (sc.hasNextLine()) {
//                    line = sc.nextLine();
//                    String[] details = line.split(","); // Split the line into details using comma as delimiter
//
//                    Dictionary<String, String> dataDict = new Hashtable<String, String>();
//                    dataDict.put("branchNum", details[0]);
//                    dataDict.put("id", details[1]);
//                    dataDict.put("firstName", details[2]);
//                    dataDict.put("lastName", details[3]);
//                    dataDict.put("bankDetails", details[4]);
//                    dataDict.put("hourRate", details[5]);
//                    dataDict.put("jobType", details[6]);
//                    dataDict.put("year", details[7]);
//                    dataDict.put("month", details[8]);
//                    dataDict.put("day", details[9]);
//                    dataDict.put("amount", details[10]);
//                    dataDict.put("0", details[11]);
//                    dataDict.put("1", details[12]);
//
//                    this.presentationController.hireWorker(dataDict);
//
//                }
//            }
//        }
//        sc.close();  //closes the scanner
//    }
//
//    public String getPath(){
//        String dir = System.getProperty("user.dir");
//        if(dir.endsWith("dev"))
//            return "configuration.csv";
//        File dirFile = new File(dir);
//        return Paths.get(dirFile.getParent(), "dev", "configuration.csv").toString();
//    }
//}
