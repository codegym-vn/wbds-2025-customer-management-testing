package com.codegym;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DummyTest {
    @Test
    void assertionWorked() {
        int actual = 1 + 1;
        int expected = 2;
        assertEquals(expected, actual);
    }
}
