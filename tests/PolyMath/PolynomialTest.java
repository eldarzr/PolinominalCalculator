package PolyMath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @BeforeEach
    void setUp() {

//
//        TreeMap<java.lang.Integer, Monomial> map = new TreeMap<>();
//        map.put(3, new Monomial(new Integer(3), 3));
//        map.put(1, new Monomial(new Integer(1), 1));
//        map.put(5, new Monomial(new Integer(5), 5));
//        map.put(7, new Monomial(new Integer(7), 7));
//        map.put(4, new Monomial(new Integer(4), 4));
//
//        TreeMap<java.lang.Integer, Monomial> map2 = new TreeMap<>();
//        map2.put(3, new Monomial(new Integer(3), 3));
//        map2.put(5, new Monomial(new Integer(5), 5));
//        map2.put(2, new Monomial(new Integer(2), 2));
//
//        Set<java.lang.Integer> set = map.descendingKeySet();
//
//        for (var e: set) {
//            System.out.println(e);
//        }
//    }
    }
    @Test
    void build() {

        String builder [] = {"1 1 1 1 1 1 ","6","123 2 5","1 2 3","1/1296 -7 0 0 4"};
        String excepteds [] = {"1+x+x^2+x^3+x^4+x^5","6","123+2x+5x^2","1+2x+3x^2","1/1296+7x+4x^4"};
        for (int i = 0; i < builder.length; i++) {
            assertEquals(excepteds[i],Polynomial.build(builder[i]).toString());
        }

        String builder2 [] = {"0000000 1","4 ","123 2 5","1 2 3","1/1296 -7 0 0 4"};
        String excepteds2 [] = {"x","6","123+2x+5x^2","1+2x+3x^2","1/1296+7x+4x^4"};
        for (int i = 0; i < builder.length; i++) {
            assertEquals(excepteds2[i],Polynomial.build(builder2[i]).toString());
        }


    }

    @Test
    void add() {
        Polynomial p1 = Polynomial.build("1/2 1");
        Polynomial p2 = Polynomial.build("5 0 -2");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.add(p2));
        System.out.println(p1);
        System.out.println(p2);


    }

    @Test
    void mul() {
    }
}