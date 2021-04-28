package PolyMath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {
Polynomial p123;
String [] buil = {"1 2 3" , "8" , "0 1/2" , "0 0 0 0 0 0 8" , "-4 -4 -4" ,"-1 0 0 0 -8"
       ,"-1 -2 -3" , "0 0 0 0 0","-1/4 -8 1/2","-32/5 0 7"};

    @BeforeEach
    void setUp() {


        TreeMap<java.lang.Integer, Monomial> map = new TreeMap<>();

        map.put(0, new Monomial(new Integer(1), 0));
        map.put(1, new Monomial(new Integer(2), 1));
        map.put(2, new Monomial(new Integer(3), 2));
//        map.put(3, new Monomial(new Integer(3), 3));
//        map.put(1, new Monomial(new Integer(1), 1));
//        map.put(5, new Monomial(new Integer(5), 5));
         p123 = new Polynomial(map);

    }


    @Test
    void build() {

        String builder [] = {"1 1 1 1 1 1 ","6","123 2 5","1 2 3","1/1296 -7 0 0 4"};
        String excepteds [] = {"1+x+x^2+x^3+x^4+x^5","6","123+2x+5x^2","1+2x+3x^2","1/1296-7x+4x^4"};
        for (int i = 0; i < builder.length; i++) {
            assertEquals(excepteds[i],Polynomial.build(builder[i]).toString());
        }
    }

    @Test
    void add() {

        String excep [] = {"2+4x+6x^2","9+2x+3x^2","1+5/2x+3x^2","1+2x+3x^2+8x^6" , "-3-2x-x^2" , "2x+3x^2-8x^4"
                           ,"0", "1+2x+3x^2","3/4-6x+7/2x^2","-27/5+2x+10x^2",};
        for (int i = 0; i < buil.length; i++) {
            Polynomial p2 = Polynomial.build(buil[i]);
            assertEquals(excep[i], p123.add(p2).toString());
        }


    }

    @Test
    void mul() {
        String [] excep = {"1+4x+10x^2+12x^3+9x^4","8+16x+24x^2","1/2x+x^2+3/2x^3","8x^6+16x^7+24x^8",
        "-4-12x-24x^2-20x^3-12x^4","-1-2x-3x^2-8x^4-16x^5-24x^6","-1-4x-10x^2-12x^3-9x^4","0",
                "-1/4-17/2x-65/4x^2-23x^3+3/2x^4","-32/5-64/5x-61/5x^2+14x^3+21x^4"};
        for (int i = 0; i < buil.length; i++) {
            Polynomial p2 = Polynomial.build(buil[i]);
            assertEquals(excep[i], p123.mul(p2).toString());
        }

    }

    @Test
    void evaluate() {
        String builder [] = {"1 1 1 1 1 1 ","6","123 2 5","1 2 3","1/1296 -7 0 0 4"};
        String[][] excepteds = {{"-182","6","162","22","447121/1296"},
                {"-21","6","139","9","101089/1296"},
                {"0","6","126","2","14257/1296"},
                {"1","6","123","1","1/1296"},
                {"6","6","130","6","-3887/1296"},
                {"63","6","147","17","64801/1296"},
                {"364","6","174","34","392689/1296"} };
        String[][] actuals = new String[7][5];
        int[] ints = {-3,-2,-1,0,1,2,3};
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < builder.length; j++) {
                actuals[i][j] = Polynomial.build(builder[j]).evaluate(new Integer(ints[i])).toString();
            }
            assertArrayEquals(excepteds[i],actuals[i]);
        }
    }

    @Test
    void derivative() {
        String builder [] = {"1 1 1 1 1 1 ","6","123 2 5","1 2 3","1/1296 -7 0 0 4"};
        String excepteds [] = {"1+2x+3x^2+4x^3+5x^4","0","2+10x","2+6x","-7+16x^3"};
        String[] actuals = new String[5];
        for (int i = 0; i < builder.length; i++) {
            actuals[i] = Polynomial.build(builder[i]).derivative().toString();
        }
        assertArrayEquals(excepteds,actuals);
    }

    @Test
    void testToString() {
        String excepteds [] = {"1+2x+3x^2","8","1/2x","8x^6","-4-4x-4x^2","-1-8x^4"
        ,"-1-2x-3x^2","0","-1/4-8x+1/2x^2","-32/5+7x^2"};
        for (int i = 0; i < buil.length; i++)
            assertEquals(excepteds[i], Polynomial.build(buil[i]).toString());
        }
    }
