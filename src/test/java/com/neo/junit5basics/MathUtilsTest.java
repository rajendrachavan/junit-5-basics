package com.neo.junit5basics;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class MathUtilsTest {

    MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    @BeforeAll
    static void beforeAllInit() {
        System.out.println("This needs to run before all \n");
    }

    /**
     * Recommended way of reporting to Junit Test Console is using TestReporter interface.
     * Instead of writing it to each and every testcase we can use it in @BeforeEach Annotated Method.
     */
    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter) {
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        mathUtils = new MathUtils();
        testReporter.publishEntry("Running "+ testInfo.getDisplayName()+" with Tags "+ testInfo.getTags() +"\n");
    }

    @AfterEach
    void cleanup() {
        System.out.println("Cleaning up...\n");
    }

    @Test
    @DisplayName("Testing Old Add Method")
    void testAdd() {
        int expected = 10;
        int actual = mathUtils.add(5,5);
        assertEquals(expected, actual, "The add method should add two numbers");
    }

    /**
     * @Tag :
     * This is used to mark tests with names, that can help you run tests based on Tags having same name,
     * and can be specified in run configuration.
     */
    @Nested
    @DisplayName("New Add Method")
    @Tag("Math")
    class AddTest {

        @Test
        @DisplayName("When adding two positive numbers")
        void testAddPositive() {
            assertEquals(14, mathUtils.add(7, 7), "Should return the right sum");
        }


        /**
         * To Optimize the code for lazy assert messages, we can use lambda for strings:
         * which indeeds get loaded only if the specific test fails.
         * it can be used when you have a string which is expensive to load.
         */
        @Test
        @DisplayName("When adding two Negative numbers")
        void testAddNegative() {
            assertEquals(-14, mathUtils.add(-7, -7), () -> "Should return the right sum");
        }
    }

    @Test
    @DisplayName("Multiply")
    @Tag("Math")
    void testMultiply() {
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2, 2)),
                () -> assertEquals(6, mathUtils.multiply(2, 3)),
                () -> assertEquals(-4, mathUtils.multiply(2, -2))
        );
    }

    @Test
    //@EnabledOnOs(OS.LINUX) -> to run some test on specific OS only otherwise it is skipped.
    @Tag("Math")
    void testDivide() {
        //boolean isServerUp = false; // returns some value
        //assumeTrue(isServerUp); //Assumptions...
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "divide by zero should throw");
    }

    /**
     * @RepeatedTest(no_of_times_test_to_be_repeated)
     * RepetitionInfo Class is used to get info about repetition cycle,
     * It can also be used to manage what happens when test reaches certain repetition.
     */
    @RepeatedTest(3)
    @Tag("Circle")
    void testComputeCircleArea(RepetitionInfo repetitionInfo) {
        // repetitionInfo.getCurrentRepetition();
        // repetitionInfo.getTotalRepetitions();
        assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "Should return right circle area");
    }

    @Test
    @Disabled
    @DisplayName("Test Driven Development method, should not run")
    void testDisabled() {
        fail("This test should be disabled");
    }
}