package PolyMath;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class RationalTest {

    private Rational rational;

    @BeforeEach
    void setUp() {
        rational = new Rational(1, 1);
    }

    @org.junit.jupiter.api.Test
    void reduce() {
        assertEquals(1/1, rational);
    }


}