package dev.ssims.fizzbuzz;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Main} class.
 * <p>
 * Validates the {@code multipleOf} helper method used by the FizzBuzz logic,
 * covering multiples of 3, 5, both, non-multiples, and edge cases.
 */
@DisplayName("Main FizzBuzz Tests")
class MainTest {

    @Test
    @DisplayName("Test multipleOf returns true for multiples of 3")
    void testMultipleOfThree() {
        assertTrue(Main.multipleOf(3, 3));
        assertTrue(Main.multipleOf(3, 6));
        assertTrue(Main.multipleOf(3, 9));
        assertTrue(Main.multipleOf(3, 15));
    }

    @Test
    @DisplayName("Test multipleOf returns true for multiples of 5")
    void testMultipleOfFive() {
        assertTrue(Main.multipleOf(5, 5));
        assertTrue(Main.multipleOf(5, 10));
        assertTrue(Main.multipleOf(5, 15));
        assertTrue(Main.multipleOf(5, 25));
    }

    @Test
    @DisplayName("Test multipleOf returns true for multiples of both 3 and 5")
    void testMultipleOfBothThreeAndFive() {
        assertTrue(Main.multipleOf(3, 15));
        assertTrue(Main.multipleOf(5, 15));
        assertTrue(Main.multipleOf(3, 30));
        assertTrue(Main.multipleOf(5, 30));
    }

    @Test
    @DisplayName("Test multipleOf returns false for non-multiples")
    void testNotMultiple() {
        assertFalse(Main.multipleOf(3, 2));
        assertFalse(Main.multipleOf(3, 4));
        assertFalse(Main.multipleOf(5, 2));
        assertFalse(Main.multipleOf(5, 7));
    }

    @Test
    @DisplayName("Test multipleOf with edge case of 1")
    void testMultipleOfOne() {
        assertTrue(Main.multipleOf(1, 1));
        assertTrue(Main.multipleOf(1, 100));
    }

    @Test
    @DisplayName("Test multipleOf returns true when numbers are equal")
    void testMultipleOfEqual() {
        assertTrue(Main.multipleOf(5, 5));
        assertTrue(Main.multipleOf(10, 10));
    }
}
