package PolyMath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalTest {


    private Rational[] rationals;
    private Integer[] integers;

    @BeforeEach
    void setUp() {
        rationals = new Rational[9];
        rationals[0] = new Rational(4, 200);
        rationals[1] = new Rational(0, 1);
        rationals[2] = new Rational(1, 1);
        rationals[3] = new Rational(1, 2);
        rationals[4] = new Rational(2, 4);
        rationals[5] = new Rational(9, 3);
        rationals[6] = new Rational(-9, 3);
        rationals[7] = new Rational(-9, -3);
        rationals[8] = new Rational(9, -3);


        integers = new Integer[9];
        integers[0] = new Integer(200);
        integers[1] = new Integer(0);
        integers[2] = new Integer(1);
        integers[3] = new Integer(2);
        integers[4] = new Integer(3);
        integers[5] = new Integer(-2);
        integers[6] = new Integer(-9);
        integers[7] = new Integer(-3);
        integers[8] = new Integer(0);
    }

    @Test
    void reduce() {
        String[] expected = {"1/50","0", "1", "1/2", "1/2", "3","-3", "3", "-3"};
        assertEquals(expected[0],rationals[0].reduce().toString());
        assertEquals(expected[1],rationals[1].reduce().toString());
        assertEquals(expected[2],rationals[2].reduce().toString());
        assertEquals(expected[3],rationals[3].reduce().toString());
        assertEquals(expected[4],rationals[4].reduce().toString());
        assertEquals(expected[5],rationals[5].reduce().toString());
        assertEquals(expected[6],rationals[6].reduce().toString());
        assertEquals(expected[7],rationals[7].reduce().toString());
        assertEquals(expected[8],rationals[8].reduce().toString());
    }


    @Test
    void addInteger() {
        String[] expected = {"10001/50","0", "2", "5/2", "7/2", "1","-12", "0", "-3"};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i],rationals[i].addInteger(integers[i]).toString());
        }

    }

    @Test
    void mulRational() {
        String[][] expected = {{"1/2500","0", "1/50", "1/100", "1/100", "3/50","-3/50"},
                                {"0","0","0","0","0","0","0"},
                                {"1/50","0", "1", "1/2", "1/2", "3","-3"},
                                {"1/100","0", "1/2", "1/4", "1/4", "3/2","-3/2"},
                                {"1/100","0", "1/2", "1/4", "1/4", "3/2","-3/2"},
                                {"3/50","0", "3", "3/2", "3/2", "9","-9"},
                                {"-3/50","0", "-3", "-3/2", "-3/2", "-9","9"}
        };

        String[][] actuals= new String[7][7];
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                actuals[i][j] = rationals[i].mulRational(rationals[j]).toString();
            }
            assertArrayEquals(expected[i], actuals[i]);
        }
    }

    @Test
    void mulInteger() {
        String[][] expected = {{"4","0", "200", "100", "100", "600","-600", "600", "-600"},
                                {"0","0","0","0","0","0","0","0","0"},
                                {"1/50","0", "1", "1/2", "1/2", "3","-3", "3", "-3"},
                                {"1/25","0", "2", "1", "1", "6","-6", "6", "-6"},
                                {"3/50","0", "3", "3/2", "3/2", "9","-9", "9", "-9"},
                                {"-1/25","0", "-2", "-1", "-1", "-6","6", "-6", "6"},
                                {"-9/50","0", "-9", "-9/2", "-9/2", "-27","27", "-27", "27"},
                                {"-3/50","0", "-3", "-3/2", "-3/2", "-9","9", "-9", "9"},
                                {"0","0","0","0","0","0","0","0","0"}
        };

        String[][] actuals= new String[9][9];
        for(int i=0; i<expected.length; i++){
            for(int j=0; j<expected.length; j++){
                actuals[i][j] = rationals[j].mulInteger(integers[i]).toString();
            }
            assertArrayEquals(expected[i], actuals[i]);
        }



    }

    @Test
    void addRational() {
        String[][] expected = {{"1/25","1/50", "51/50", "13/25", "13/25", "151/50", "-149/50","151/50", "-149/50"},
                {"1/50","0", "1", "1/2", "1/2", "3","-3", "3", "-3"},
                {"51/50","1", "2", "3/2", "3/2", "4","-2", "4", "-2"},
                {"13/25","1/2", "3/2", "1", "1", "7/2","-5/2", "7/2", "-5/2"},
                {"13/25","1/2", "3/2", "1", "1", "7/2","-5/2", "7/2", "-5/2"},
                {"151/50","3", "4", "7/2", "7/2", "6","0", "6", "0"},
                {"-149/50","-3", "-2", "-5/2", "-5/2", "0","-6", "0", "-6"},
                {"151/50","3", "4", "7/2", "7/2", "6","0", "6", "0"},
                {"-149/50","-3", "-2", "-5/2", "-5/2", "0","-6", "0", "-6"},
        };

        String[][] actuals= new String[9][9];
        for(int i=0; i<expected.length; i++){
            for(int j=0; j<expected.length; j++){
                actuals[i][j] = rationals[i].addRational(rationals[j]).toString();
            }
            assertArrayEquals(expected[i], actuals[i]);
        }
    }

    @Test
    void power() {
        int[] pow = {-3,-2,-1,0,1,2,3};
        String[][] expected = {{"125000","0", "1", "8", "8", "1/27","-1/27"},
                {"2500","0", "1", "4", "4", "1/9","1/9"},
                {"50","0", "1", "2", "2", "1/3","-1/3"},
                {"1","1", "1", "1", "1", "1","1"},
                {"1/50","0", "1", "1/2", "1/2", "3","-3"},
                {"1/2500","0", "1", "1/4", "1/4", "9","9"},
                {"1/125000","0", "1", "1/8", "1/8", "27","-27"}
        };

        String[][] actuals= new String[7][7];

        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                actuals[i][j] = rationals[j].power(pow[i]).toString();
            }
            assertArrayEquals(expected[i], actuals[i]);
        }
    }
}