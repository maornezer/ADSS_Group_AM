//package Domain;
//
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.Dictionary;
//import java.util.Hashtable;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class WorkerTest {
//
//    Worker worker1;
//    Worker worker2;
//
//
//    @BeforeEach
//    void setUp() {
//        Chain chain = new Chain(100, "FN", "LN", 100, LocalDate.of(2024, 1, 1));
//        Dictionary<String, String > data = new Hashtable<>();
//        data.put("branchNum","1");
//        data.put("address", "Tel aviv");
//        data.put("deadLine", "THURSDAY");
//        chain.addBranch(data);
//
//        data = new Hashtable<>();
//        data.put("id", Integer.toString(103));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "Noa");
//        data.put("lastName", "Shvets");
//        data.put("bankDetails", Integer.toString(100));
//        data.put("hourRate", Integer.toString(300));
//        data.put("jobType", "1");
//        data.put("year", Integer.toString(2025));
//        data.put("month", Integer.toString(12));
//        data.put("day", Integer.toString(30));
//        data.put("amount","2");
//        data.put("1","Manager");
//        data.put("0", "Cashier");
//
//
//        worker1 = new Worker(data);
//        Branch branch = Chain.getBranch(1);
//        branch.addWorker(worker1);
//        data.put("id", Integer.toString(101));
//        data.put("branchNum", Integer.toString(1));
//        data.put("firstName", "Lee");
//        data.put("lastName", "Chemo");
//        data.put("bankDetails", Integer.toString(101));
//        data.put("hourRate", Integer.toString(30));
//        data.put("amount","3");
//        data.put("2","Storekeeper" );
//
//        worker2 = new Worker(data);
//        branch.addWorker(worker2);
//
//    }
//
//    @Test
//    void getId() {
//        assertEquals(103, worker1.getId());
//        assertEquals(101, worker2.getId());
//
//    }
//
//
//    @Test
//    void getFirstName() {
//        assertEquals("Lee", worker2.getFirstName());
//        worker2.setFirstName("mee");
//        assertEquals("mee", worker2.getFirstName());
//        worker2.setFirstName("Lee");
//
//        assertEquals("Noa", worker1.getFirstName());
//        worker1.setFirstName("naya");
//        assertEquals("naya", worker1.getFirstName());
//        worker1.setFirstName("Noa");
//
//    }
//
//    @Test
//    void getLastName() {
//        assertEquals("Chemo", worker2.getLastName());
//        worker2.setLastName("Shemo");
//        assertEquals("Shemo", worker2.getLastName());
//        worker2.setLastName("Chemo");
//
//        assertEquals("Shvets", worker1.getLastName());
//        worker1.setLastName("Mohan");
//        assertEquals("Mohan", worker1.getLastName());
//        worker1.setLastName("Shvets");
//
//    }
//
//    @Test
//    void getBankInfo() {
//        assertEquals(100, worker1.getBankInfo());
//        worker1.setBankInfo(200);
//        assertEquals(200, worker1.getBankInfo());
//
//        assertEquals(101, worker2.getBankInfo());
//        worker2.setBankInfo(300);
//        assertEquals(300, worker2.getBankInfo());
//
//    }
//
//    @Test
//    void testToString() {
//        assertEquals("Noa Shvets", worker1.toString());
//        assertEquals("Lee Chemo", worker2.toString());
//    }
//
//    @Test
//    void testResignationNotice(){
//        worker1.resignationNotice();
//        assertEquals(worker1.getEmploymentEnd().isBefore(Chain.getToday().plusDays(31)), true);
//        worker2.resignationNotice();
//        assertEquals(worker2.getEmploymentEnd().isBefore(Chain.getToday().plusDays(31)), true);
//    }
//
//    @Test
//    void testCheckRole(){
//        assertEquals(worker1.checkRole("Manager"), true);
//        assertEquals(worker1.checkRole("Cashier"), true);
//        assertEquals(worker1.checkRole("Storekeeper"), false);
//        assertEquals(worker2.checkRole("Manager"), true);
//        assertEquals(worker2.checkRole("Cashier"), true);
//        assertEquals(worker2.checkRole("Storekeeper"), true);
//    }
//}