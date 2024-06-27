package Domain;

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
        Chain chain = new Chain(100, "FN", "LN", 100, LocalDate.of(2024, 1, 1));
        Dictionary<String, String > data = new Hashtable<>();
        data.put("branchNum","1");
        data.put("address", "Tel aviv");
        data.put("deadLine", "THURSDAY");
        chain.addBranch(data);
        this.branch = Chain.getBranch(1);

        data = new Hashtable<>();
        data.put("id", Integer.toString(101));
        data.put("branchNum", Integer.toString(1));
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

        Worker worker1 = new Worker(data);
        branch.addWorker(worker1);

        data.put("id", Integer.toString(102));
        data.put("branchNum", Integer.toString(1));
        data.put("firstName", "lee");
        data.put("lastName", "Chemo");

        Worker worker2 = new Worker(data);
        branch.addWorker(worker2);

        data.put("id", Integer.toString(103));
        data.put("firstName", "Tal");
        data.put("lastName", "Cohen");
        data.put("bankDetails", Integer.toString(500));

        Worker worker3 = new Worker(data);

        data.put("id", Integer.toString(104));
        data.put("branchNum", Integer.toString(1));
        data.put("firstName", "Gal");
        data.put("lastName", "Kirel");

        Worker worker4 = new Worker(data);

        data.put("id", Integer.toString(105));
        data.put("branchNum", Integer.toString(1));
        data.put("firstName", "Shimon");
        data.put("lastName", "Hemo");

        Worker worker5 = new Worker(data);

        data.put("id", Integer.toString(106));
        data.put("branchNum", Integer.toString(1));
        data.put("firstName", "Rahel");
        data.put("lastName", "Giladi");

        Worker worker6 = new Worker(data);

        data.put("id", Integer.toString(107));
        data.put("branchNum", Integer.toString(1));
        data.put("firstName", "Yaron");
        data.put("lastName", "hemo");

        Worker worker7 = new Worker(data);

        data.put("id", Integer.toString(108));
        data.put("branchNum", Integer.toString(1));
        data.put("firstName", "yossi");
        data.put("lastName", "hemo");

        Worker worker8 = new Worker(data);

        data.put("id", Integer.toString(109));
        data.put("branchNum", Integer.toString(1));
        data.put("firstName", "levana");
        data.put("lastName", "leibel");

        Worker worker9 = new Worker(data);

        data.put("id", Integer.toString(110));
        data.put("branchNum", Integer.toString(1));
        data.put("firstName", "guy");
        data.put("lastName", "leibel");

        Worker worker10 = new Worker(data);

        branch.addWorker(worker3);
        branch.addWorker(worker4);
        branch.addWorker(worker5);
        branch.addWorker(worker6);
        branch.addWorker(worker7);
        branch.addWorker(worker8);
        branch.addWorker(worker9);
        branch.addWorker(worker10);

    }

    @Test
    void getWorkers() {
        assertEquals(branch.getWorkers().size(), 10);
        branch.removeWorker(branch.getWorkers().get(0));
        assertEquals(branch.getWorkers().size(), 9);
        assertEquals(branch.getFiredWorkers().size(),1);
    }

    @Test
    void addWorker() {
        Dictionary<String,String> data = new Hashtable<>();
        data.put("id", Integer.toString(101));
        data.put("branchNum", Integer.toString(1));
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

        Worker worker1 = new Worker(data);
        branch.addWorker(worker1);

        assertEquals(branch.getWorkers().contains(worker1), true);
    }

    @Test
    void removeWorker() {
        Worker worker = branch.getWorkers().get(0);
        branch.removeWorker(worker);
        assertEquals(branch.getWorkers().size(), 9);
        assertEquals(branch.getFiredWorkers().size(),1);
        assertEquals(branch.getFiredWorkers().contains(worker), true);
        assertEquals(branch.getWorkers().contains(worker), false);
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

}