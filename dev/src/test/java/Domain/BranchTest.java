package Domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class BranchTest {

    Branch branch;

    @BeforeEach
    void setUp() {
        Dictionary<String, String > data = new Hashtable<>();
        data.put("id", Integer.toString(500));
        data.put("firstName", "bla");
        data.put("lastName", "bla");
        data.put("bankDetails", Integer.toString(500));
        data.put("year", Integer.toString(2024));
        data.put("month", Integer.toString(04));
        data.put("day", Integer.toString(07));

        Chain chain = new Chain(data);

        data.put("branchNum","20");
        data.put("address", "Tel aviv");
        data.put("deadline", "THURSDAY");
        chain.addBranch(data);
        this.branch = Chain.getBranch(20);

        data = new Hashtable<>();
        data.put("id", Integer.toString(501));
        data.put("branchNum", Integer.toString(20));
        data.put("firstName", "Noa");
        data.put("lastName", "Shvets");
        data.put("bankDetails", Integer.toString(200));
        data.put("hourRate", Integer.toString(300));
        data.put("jobType", "1");
        data.put("year", Integer.toString(2025));
        data.put("month", Integer.toString(12));
        data.put("day", Integer.toString(30));
        data.put("amount","3");
        data.put("1","Manager");
        data.put("0", "Cashier");
        data.put("2","Storekeeper" );


        branch.addWorker(data);

        data.put("id", Integer.toString(502));
        data.put("firstName", "lee");
        data.put("lastName", "Chemo");

        branch.addWorker(data);

        data.put("id", Integer.toString(503));
        data.put("firstName", "Tal");
        data.put("lastName", "Cohen");
        data.put("bankDetails", Integer.toString(500));

        branch.addWorker(data);

        data.put("id", Integer.toString(504));
        data.put("firstName", "Gal");
        data.put("lastName", "Kirel");

        branch.addWorker(data);

        data.put("id", Integer.toString(505));
        data.put("firstName", "Shimon");
        data.put("lastName", "Hemo");

        branch.addWorker(data);

        data.put("id", Integer.toString(506));
        data.put("firstName", "Rahel");
        data.put("lastName", "Giladi");

        branch.addWorker(data);

        data.put("id", Integer.toString(507));
        data.put("firstName", "Yaron");
        data.put("lastName", "hemo");

        branch.addWorker(data);

        data.put("id", Integer.toString(508));
        data.put("firstName", "yossi");
        data.put("lastName", "hemo");

        branch.addWorker(data);

        data.put("id", Integer.toString(509));
        data.put("firstName", "levana");
        data.put("lastName", "leibel");

        branch.addWorker(data);

        data.put("id", Integer.toString(510));
        data.put("firstName", "guy");
        data.put("lastName", "leibel");

        branch.addWorker(data);


    }

    @AfterEach
    void tearDown() {
        Chain.getBranchesRepository().deleteBranch(20);

        Chain.getWorkersRepository().deleteWorker(501);
        Chain.getWorkersRepository().deleteWorker(502);
        Chain.getWorkersRepository().deleteWorker(503);
        Chain.getWorkersRepository().deleteWorker(504);
        Chain.getWorkersRepository().deleteWorker(505);
        Chain.getWorkersRepository().deleteWorker(506);
        Chain.getWorkersRepository().deleteWorker(507);
        Chain.getWorkersRepository().deleteWorker(508);
        Chain.getWorkersRepository().deleteWorker(509);
        Chain.getWorkersRepository().deleteWorker(510);
    }

    @Test
    void getWorkers() {
        assertEquals(10, branch.getWorkers().size());
    }



    @Test
    void addWorker() {
        Dictionary<String,String> data = new Hashtable<>();
        data.put("id", Integer.toString(511));
        data.put("branchNum", Integer.toString(20));
        data.put("firstName", "Noa");
        data.put("lastName", "Shvets");
        data.put("bankDetails", Integer.toString(200));
        data.put("hourRate", Integer.toString(300));
        data.put("jobType", "1");
        data.put("year", Integer.toString(2025));
        data.put("month", Integer.toString(12));
        data.put("day", Integer.toString(30));
        data.put("amount","3");
        data.put("1","Manager");
        data.put("0", "Cashier");
        data.put("2","Storekeeper" );

        branch.addWorker(data);

        assertEquals(11, branch.getWorkers().size());

        Chain.getWorkersRepository().deleteWorker(511);
    }

    @Test
    void removeWorker() {
        Dictionary<String,String> data = new Hashtable<>();
        data.put("id", Integer.toString(512));
        data.put("branchNum", Integer.toString(20));
        data.put("firstName", "Noa");
        data.put("lastName", "Shvets");
        data.put("bankDetails", Integer.toString(200));
        data.put("hourRate", Integer.toString(300));
        data.put("jobType", "1");
        data.put("year", Integer.toString(2025));
        data.put("month", Integer.toString(12));
        data.put("day", Integer.toString(30));
        data.put("amount","3");
        data.put("1","Manager");
        data.put("0", "Cashier");
        data.put("2","Storekeeper" );

        branch.addWorker(data);

        assertEquals(11, branch.getWorkers().size());

        Chain.getWorkersRepository().deleteWorker(512);

        assertEquals(10, branch.getWorkers().size());
    }

    @Test
    void getDeadLine() {
        assertEquals(branch.getDeadLine().equals(DayOfWeek.THURSDAY), true);
        branch.setDeadLine(DayOfWeek.WEDNESDAY);
        assertEquals(branch.getDeadLine().equals(DayOfWeek.WEDNESDAY), true);
    }

    @Test
    void getDaysOffConst() {
        assertEquals(branch.getDaysOffConst().size(), 2);
        branch.addShiftOffConst(new genShift(DayOfWeek.FRIDAY, 1));
        assertEquals(branch.getDaysOffConst().size(), 3);
    }

    @Test
    void getDaysOffTemp() {
        assertEquals(branch.getDaysOffTemp().size(), 0);
        branch.addShiftOffTemp(new genShift(DayOfWeek.SUNDAY, 1));
        assertEquals(branch.getDaysOffTemp().size(), 1);
        branch.removeShiftOffTemp(new genShift(DayOfWeek.SUNDAY, 1));
        assertEquals(branch.getDaysOffTemp().size(), 0);
    }

    @Test
    void getBranchNum() {
        assertEquals(20, branch.getBranchNum());
    }

    @Test
    void getAddress() {
        assertEquals("Tel aviv", branch.getAddress());
    }

    @Test
    void changeStartAndEndTime() {
        Dictionary<String, String> data = new Hashtable<>();
        data.put("day", "7");
        data.put("shift", "1");
        data.put("start", "7");
        data.put("end", "13");
        assertEquals(false, branch.changeStartAndEndTime(data));
    }

    @Test
    void testToString() {
        assertNotEquals(0, branch.toString().length());
    }


}