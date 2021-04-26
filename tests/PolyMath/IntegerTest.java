package PolyMath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerTest {
    private static Integer[] integers;
    private static Rational[] rationals;


    @BeforeEach
    void setUp() {
        integers = new Integer[]{new Integer(3), new Integer(6), new Integer(1500), new Integer(300),
                new Integer(-4), new Integer(0), new Integer(33), new Integer(-7), new Integer(0)};
        rationals = new Rational[]{new Rational(4,6)};
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
        assertEquals("11/3",integers[3].add(rationals[0]).toString());
    }

    @Test
    void mulRational() {
    }
}