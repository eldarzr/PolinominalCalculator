package PolyMath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @BeforeEach
    void setUp() {


        TreeMap<java.lang.Integer, Monomial> map = new TreeMap<>();
        map.put(3, new Monomial(new Integer(3), 3));
        map.put(1, new Monomial(new Integer(1), 1));
        map.put(5, new Monomial(new Integer(5), 5));
        map.put(7, new Monomial(new Integer(7), 7));
        map.put(4, new Monomial(new Integer(4), 4));

        TreeMap<java.lang.Integer, Monomial> map2 = new TreeMap<>();
        map2.put(3, new Monomial(new Integer(3), 3));
        map2.put(5, new Monomial(new Integer(5), 5));
        map2.put(2, new Monomial(new Integer(2), 2));

        Set<java.lang.Integer> set = map.descendingKeySet();

        for (var e: set) {
            System.out.println(e);
        }
    }

    @Test
    void build() {
       System.out.println(Polynomial.build("1 2 3"));
        System.out.println(Polynomial.build("0 0 5"));
        System.out.println(Polynomial.build("1 2/3 -8"));

    }

    @Test
    void add() {
    }

    @Test
    void mult() {
    }
}