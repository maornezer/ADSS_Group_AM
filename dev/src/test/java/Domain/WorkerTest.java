package Domain;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkerTest {

    Branch branch;

    Worker worker1;

    Worker worker2;

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


        this.worker1 = branch.addWorker(data);

        data.put("id", Integer.toString(502));
        data.put("firstName", "Lee");
        data.put("lastName", "Chemo");

        this.worker2 = branch.addWorker(data);
    }

    @AfterEach
    void tearDown() {
        Chain.getBranchesRepository().deleteBranch(20);

        Chain.getWorkersRepository().deleteWorker(501);
        Chain.getWorkersRepository().deleteWorker(502);
    }

    @Test
    void getId() {
        assertEquals(501, worker1.getId());
        assertEquals(502, worker2.getId());

    }

    @Test
    void getUsedVacationDays() {
        assertEquals(0, worker1.getUsedVacationDays());
        assertEquals(0, worker2.getUsedVacationDays());

        worker1.IncreaseUsedVacationDays();
        worker1.IncreaseUsedVacationDays();
        worker2.IncreaseUsedVacationDays();

        assertEquals(2, worker1.getUsedVacationDays());
        assertEquals(1, worker2.getUsedVacationDays());
    }

    @Test
    void getFirstName() {
        assertEquals("Lee", worker2.getFirstName());
        assertEquals("Noa", worker1.getFirstName());

    }

    @Test
    void getJobType() {
        assertEquals("1", worker1.getJobType());
        assertEquals("1",  worker2.getJobType());

        worker1.setJobType("fullTime");
        worker2.setJobType("partTime");

        assertEquals("fullTime",  worker1.getJobType());
        assertEquals("partTime", worker2.getJobType());


    }

    @Test
    void getLastName() {
        assertEquals("Chemo", worker2.getLastName());
        assertEquals("Shvets", worker1.getLastName());
    }


    @Test
    void testToString() {
        assertEquals("Noa Shvets", worker1.toString());
        assertEquals("Lee Chemo", worker2.toString());
    }

    @Test
    void testResignationNotice(){
        worker1.resignationNotice();
        assertEquals(worker1.getEmploymentEnd().isBefore(Chain.getToday().plusDays(31)), true);
        worker2.resignationNotice();
        assertEquals(worker2.getEmploymentEnd().isBefore(Chain.getToday().plusDays(31)), true);
    }

    @Test
    void getEmploymentEnd(){
        worker1.setEmploymentEnd(LocalDate.now().plusDays(30));
        worker2.setEmploymentEnd(LocalDate.now().plusDays(32));
        assertEquals(LocalDate.now().plusDays(30), worker1.getEmploymentEnd());
        assertEquals(LocalDate.now().plusDays(32), worker2.getEmploymentEnd());
    }

    @Test
    void testCheckRole(){
        assertEquals(worker1.checkRole("Manager"), true);
        assertEquals(worker1.checkRole("Cashier"), true);
        assertEquals(worker1.checkRole("Storekeeper"), true);
        assertEquals(worker2.checkRole("Manager"), true);
        assertEquals(worker2.checkRole("Cashier"), true);
        assertEquals(worker2.checkRole("Storekeeper"), true);
    }

    @Test
    void ifCanWork(){
        int temp = 0;
        if(LocalDate.now().getDayOfWeek().equals(DayOfWeek.SATURDAY))
            temp = 1;
        assertEquals(worker1.ifCanWork(LocalDate.now().plusDays(temp), 1), true);
        assertEquals(worker1.ifCanWork(LocalDate.now().plusDays(temp), 2), true);
        assertEquals(worker2.ifCanWork(LocalDate.now().plusDays(temp), 1), true);
        assertEquals(worker2.ifCanWork(LocalDate.now().plusDays(temp), 2), true);

    }
}