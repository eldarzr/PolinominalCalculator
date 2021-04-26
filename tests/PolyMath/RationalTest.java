package PolyMath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalTest {


    private Rational[] rationals;
    private Integer[] integers;

    @BeforeEach
    void setUp() {
        rationals = new Rational[10];
//        rationals[0] = new Rational(0, 0);
        rationals[0] = new Rational(0, 1);
        rationals[1] = new Rational(0, 1);
        rationals[2] = new Rational(1, 1);
        rationals[3] = new Rational(1, 2);
        rationals[4] = new Rational(2, 4);
        rationals[5] = new Rational(9, 3);
        rationals[6] = new Rational(-9, 3);
        rationals[7] = new Rational(-9, -3);
        rationals[8] = new Rational(9, -3);
        rationals[9] = new Rational(4, 200);

        integers = new Integer[10];
//        rationals[0] = new Rational(0, 0);
        integers[0] = new Integer(0);
        integers[1] = new Integer(0);
        integers[2] = new Integer(1);
        integers[3] = new Integer(2);
        integers[4] = new Integer(3);
        integers[5] = new Integer(-2);
        integers[6] = new Integer(-9);
        integers[7] = new Integer(-3);
        integers[8] = new Integer(0);
        integers[9] = new Integer(200);
    }

    @org.junit.jupiter.api.Test
    void reduce() {
        String[] expected = {"","0", "1", "1/2", "1/2", "3","-3", "3", "-3", "1/50"};
        assertEquals(expected[1],rationals[1].reduce().toString());
        assertEquals(expected[2],rationals[2].reduce().toString());
        assertEquals(expected[3],rationals[3].reduce().toString());
        assertEquals(expected[4],rationals[4].reduce().toString());
        assertEquals(expected[5],rationals[5].reduce().toString());
        assertEquals(expected[6],rationals[6].reduce().toString());
        assertEquals(expected[7],rationals[7].reduce().toString());
        assertEquals(expected[8],rationals[8].reduce().toString());
        assertEquals(expected[9],rationals[9].reduce().toString());
    }


    @Test
    void addInteger() {
        String[] expected = {"0","0", "2", "5/2", "7/2", "1","-12", "0", "-3", "10001/50"};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i],rationals[i].addInteger(integers[i]).toString());
        }

    }
}