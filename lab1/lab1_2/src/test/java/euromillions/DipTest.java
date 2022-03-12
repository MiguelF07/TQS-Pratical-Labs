/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class DipTest {

    private Dip instance;


    public DipTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


    @Test
    public void testConstructorFromBadArrays() {
        // todo: instantiate a dip passing valid or invalid arrays

        assertAll("Should throw an IllegalArgumentException error",
            //Wrong size of numbers
            () -> assertThrows(IllegalArgumentException.class, () -> new Dip(new int[]{10, 20, 30, 40}, new int[]{1, 2})),
            //Wrong size of stars
            () -> assertThrows(IllegalArgumentException.class, () -> new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1})),
            //Wrong size of both
            () -> assertThrows(IllegalArgumentException.class, () -> new Dip(new int[]{10, 20, 30, 40}, new int[]{1}))
        );
    }

    @Test
    public void testFormat() {
        // note: correct the implementation of the format(), not the test...
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

    @Disabled("Disabled because the range of stars was changed to 1..12")
    @DisplayName("Test to check if the original range (1..50 on numbers and 1..10 in stars) is working")
    @Test
    public void testStarGeneratorTo10() {
        assertAll("Should throw an IllegalArgumentException when out of bounds",
            //Wrong range for numbers
            () -> assertThrows(IllegalArgumentException.class, () -> new Dip(new int[]{10, 20, 30, 40, 52}, new int[]{1, 9})),
            //Wrong range for stars
            () -> assertThrows(IllegalArgumentException.class, () -> new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 12}))
        );
    }

    @DisplayName("Test to check if the new range (1..50 on numbers and 1..12 in stars) is working")
    @Test
    public void testStarGeneratorTo12() {
        assertAll("Should throw an IllegalArgumentException when out of bounds",
            //Wrong range for numbers
            () -> assertThrows(IllegalArgumentException.class, () -> new Dip(new int[]{10, 20, 30, 40, 52}, new int[]{1, 9})),
            //Wrong range for stars
            () -> assertThrows(IllegalArgumentException.class, () -> new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 13})),
            () -> assertThrows(IllegalArgumentException.class, () -> new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 0}))
        );
    }






}
