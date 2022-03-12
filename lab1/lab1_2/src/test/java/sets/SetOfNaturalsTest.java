/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class SetOfNaturalsTest {
    private SetOfNaturals setA;
    private SetOfNaturals setB;
    private SetOfNaturals setC;
    private SetOfNaturals setD;

    @BeforeEach
    public void setUp() {
        setA = new SetOfNaturals();
        setB = SetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        setC = new SetOfNaturals();
        for (int i = 5; i < 50; i++) {
            setC.add(i * 10);
        }
        setD = SetOfNaturals.fromArray(new int[]{30, 40, 50, 60, 10, 20});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Test
    @DisplayName("Add a duplicate value to the set throws an error")
    public void testDuplicate() {
        assertTrue(setB.contains(10));
        assertThrows(IllegalArgumentException.class, () -> setB.add(10), "add: added element was added but already is in set");
    }

    @Test
    @DisplayName("Add a non positive value to the set throws an error")
    public void testNonPositive() {
        assertThrows(IllegalArgumentException.class, () -> setA.add(0), "add: zero added but it is not in range of 1..+oo");
        assertThrows(IllegalArgumentException.class, () -> setA.add(-3), "add: negative value added but it is not in range og 1..+oo");
    }

    @Test
    public void testAddBadArray() {
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    @DisplayName("Add an array with a duplicate value to the set throws an error")
    public void testDuplicateFromArray() {
        assertThrows(IllegalArgumentException.class, () -> SetOfNaturals.fromArray(new int[]{38,39,40,39}));
    }

    @Test
    @DisplayName("Add an array with a non positive value to the set throws an error")
    public void testNonPositiveFromArray() {
        assertThrows(IllegalArgumentException.class, () -> SetOfNaturals.fromArray(new int[]{38,39,40,-2}));
        assertThrows(IllegalArgumentException.class, () -> SetOfNaturals.fromArray(new int[]{38,39,40,0}));
    }

    @Test
    @DisplayName("Testing if it returns true when two sets intersect")
    public void testIntersection() {
        assertTrue(setB.intersects(SetOfNaturals.fromArray(new int[]{38,39,40,41})));

    }


    @Test
    public void testIntersectForNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection but was reported as existing");

    }

}
