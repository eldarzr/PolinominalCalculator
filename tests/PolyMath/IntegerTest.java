package PolyMath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerTest {
    private static Integer[] integers;
    private static Rational[] rationals;


    @BeforeEach
    void setUp() {
        integers = new Integer[]{new Integer(3), new Integer(6), new Integer(1500),
                                 new Integer(300), new Integer(-4), new Integer(0),
                                 new Integer(33), new Integer(-7), new Integer(0)};

        rationals = new Rational[]{new Rational(4,6),new Rational(17,29),
                                   new Rational(8,2),new Rational(19,5),
                                   new Rational(-9,3),new Rational(-3,6),
                                   new Rational(0,3)};
    }

    @Test
    void add() {
        for (int i = 0; i < integers.length; i++) {
            for (int j = 0; j < integers.length; j++) {
                int excepted=integers[i].getNumber()+integers[j].getNumber();
                assertEquals(""+excepted,integers[i].add(integers[j]).toString());
            } }
}


    @Test
    void mul() {
        for (int i = 0; i < integers.length; i++) {
            for (int j = 0; j < integers.length; j++) {
                int excepted=integers[i].getNumber()*integers[j].getNumber();
                assertEquals(""+excepted,integers[i].mul(integers[j]).toString());
            } }
    }

    @Test
    void sign() {
        assertEquals(1,integers[0].sign());
        assertEquals(1,integers[2].sign());
        assertEquals(0,integers[8].sign());
        assertEquals(-1,integers[4].sign());
        assertEquals(-1,integers[7].sign());
    }

    @Test
    void addRational() {
        String excepteds[] = {"20/3", "191/29", "10", "49/5", "3", "11/2", "6"};

        for (int i = 0; i < rationals.length; i++) {
            assertEquals(excepteds[i], (integers[1].add(rationals[i])).toString());
        }

        assertEquals("0", (integers[8].add(rationals[6])).toString());
    }


    @Test
    void power() {
        String excepteds [] = {"1","6","36","10077696","1/1296"};
        int powers []  = {0,1,2,9,-4};

        for (int i = 0; i < excepteds.length; i++) {
            assertEquals(excepteds[i], (integers[1].power(powers[i]).toString()));
        }

        assertEquals("1/256", (integers[4].power(powers[4]).toString()));
        assertEquals("1/2401", (integers[7 ].power(powers[4]).toString()));

    }

    @Test
    void neg() {
    }
}