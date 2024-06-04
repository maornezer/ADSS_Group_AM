package Domain;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkerTest {

    Worker worker1;
    Worker worker2;
    Worker worker3;

    Worker worker4;

    Worker worker5;


    @BeforeEach
    void setUp() {
        worker1 = new Worker(100, "lee", "chemo", 100, 1);
        worker2 = new Worker(101, "maya", "dohan", 101, 1);
        worker3 = new Worker(102, "angelika", "mirziyan", 102, 1);
        worker4 = new Worker(103, "noa", "shvets", 103, 1);
        worker5 = new Worker(104, "ron", "shukrun", 104, 1);


    }

    @Test
    void getId() {
        assertEquals(100, worker1.getId());
        assertEquals(101, worker2.getId());
        assertEquals(102, worker3.getId());

    }


    @Test
    void getFirstName() {
        assertEquals("lee", worker1.getFirstName());
        worker1.setFirstName("mee");
        assertEquals("mee", worker1.getFirstName());

        assertEquals("maya", worker2.getFirstName());
        worker2.setFirstName("naya");
        assertEquals("naya", worker2.getFirstName());

        assertEquals("angelika", worker3.getFirstName());
        worker2.setFirstName("angelina");
        assertEquals("angelina", worker3.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals("chemo", worker1.getLastName());
        worker1.setLastName("shemo");
        assertEquals("shemo", worker1.getLastName());

        assertEquals("dohan", worker2.getLastName());
        worker2.setFirstName("mohan");
        assertEquals("mohan", worker2.getLastName());

        assertEquals("mirziyan", worker3.getLastName());
        worker2.setFirstName("kirziyan");
        assertEquals("kirziyan", worker3.getLastName());
    }

    @Test
    void getBankInfo() {
        assertEquals(100, worker1.getBankInfo());
        worker1.setBankInfo(200);
        assertEquals(200, worker1.getBankInfo());

        assertEquals(101, worker2.getBankInfo());
        worker2.setBankInfo(300);
        assertEquals(200, worker2.getBankInfo());

    }

    @Test
    void testToString() {
        assertEquals("Worker name: noa shvets", worker4.toString());
        assertEquals("Worker name: ron shukrun", worker5.toString());
    }
}