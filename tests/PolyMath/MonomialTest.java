package PolyMath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonomialTest {
    Monomial[] monomials = new Monomial[9];

    @BeforeEach
    void setUp() {
        monomials[0] = new Monomial(new Integer(0),0);
        monomials[1] = new Monomial(new Integer(1),0);
        monomials[2] = new Monomial(new Integer(1),2);
        monomials[3] = new Monomial(new Integer(4),5);
        monomials[4] = new Monomial(new Rational(1,2),0);
        monomials[5] = new Monomial(new Rational(1,2),1);
        monomials[6] = new Monomial(new Rational(1,2),2);
        monomials[7] = new Monomial(new Rational(5,4),7);
        monomials[8] = new Monomial(new Rational(-1,1),2);
    }

    @Test
    void testToString() {
        String[] expected = {"0","1","x^2","4x^5","1/2","1/2x","1/2x^2","5/4x^7","-x^2"};
        String[] actuals = new String[9];
        for(int i=0; i<expected.length; i++){
            actuals[i] = monomials[i].toString();
        }
        assertArrayEquals(expected,actuals);
    }

    @Test
    void add() {
        String[][] expected = {{"0","1",null,null,"1/2",null,null,null,null},
                {"1","2",null,null,"3/2",null,null,null,null},
                {null,null,"2x^2",null,null,null,"3/2x^2",null,"0"},
                {null,null,null,"8x^5",null,null,null,null,null},
                {"1/2","3/2",null,null,"1",null,null,null,null},
                {null,null,null,null,null,"x",null,null,null},
                {null,null,"3/2x^2",null,null,null,"x^2",null,"-1/2x^2"},
                {null,null,null,null,null,null,null,"5/2x^7",null},
                {null,null,"0",null,null,null,"-1/2x^2",null,"-2x^2"}
        };
        String[][] actuals = new String[9][9];
        for(int i=0; i<expected.length; i++){
            for (int j = 0; j < expected.length; j++) {
                Monomial m = monomials[j].add(monomials[i]);
                if(m == null)
                    actuals[i][j] = null;
                else actuals[i][j] = m.toString();
            }
            assertArrayEquals(expected[i],actuals[i]);
        }
    }

    @Test
    void mult() {
        String[][] expected = {{"0","0","0","0","0","0","0","0","0"},
                                {"0","1","x^2","4x^5","1/2","1/2x","1/2x^2","5/4x^7","-x^2"},
                                {"0","x^2","x^4","4x^7","1/2x^2","1/2x^3","1/2x^4","5/4x^9","-x^4"},
                                {"0","4x^5","4x^7","16x^10","2x^5","2x^6","2x^7","5x^12","-4x^7"},
                                {"0","1/2","1/2x^2","2x^5","1/4","1/4x","1/4x^2","5/8x^7","-1/2x^2"},
                                {"0","1/2x","1/2x^3","2x^6","1/4x","1/4x^2","1/4x^3","5/8x^8","-1/2x^3"},
                                {"0","1/2x^2","1/2x^4","2x^7","1/4x^2","1/4x^3","1/4x^4","5/8x^9","-1/2x^4"},
                                {"0","5/4x^7","5/4x^9","5x^12","5/8x^7","5/8x^8","5/8x^9","25/16x^14","-5/4x^9"},
                                {"0","-x^2","-x^4","-4x^7","-1/2x^2","-1/2x^3","-1/2x^4","-5/4x^9","x^4"}
        };
        String[][] actuals = new String[9][9];
        for(int i=0; i<expected.length; i++){
            for (int j = 0; j < expected.length; j++) {
                Monomial m = monomials[j].mul(monomials[i]);
                if(m == null)
                    actuals[i][j] = null;
                else actuals[i][j] = m.toString();
            }
            assertArrayEquals(expected[i],actuals[i]);
        }
    }

    @Test
    void evaluate() {
        int[] vals = {-3,-2,-1,0,1,2,3};
        String[][] expected = {{"0","1","9","-972","1/2","-3/2","9/2","-10935/4","-9"},
                {"0","1","4","-128","1/2","-1","2","-160","-4"},
                {"0","1","1","-4","1/2","-1/2","1/2","-5/4","-1"},
                {"0","1","0","0","1/2","0","0","0","0"},
                {"0","1","1","4","1/2","1/2","1/2","5/4","-1"},
                {"0","1","4","128","1/2","1","2","160","-4"},
                {"0","1","9","972","1/2","3/2","9/2","10935/4","-9"},
        };
        String[][] actuals = new String[7][9];
        for(int i=0; i<expected.length; i++){
            for (int j = 0; j < expected[0].length; j++) {
                actuals[i][j] = monomials[j].evaluate(new Integer(vals[i])).toString();
            }
            assertArrayEquals(expected[i],actuals[i]);
        }
    }
    @Test
    void evaluateRational() {
        Rational[] vals = { new Rational(-3,7),
                            new Rational(3,7),
                            new Rational(-15,9),
                            new Rational(15,9)
        };
        String[][] expected = {{"0","1","9/49","-972/16807","1/2","-3/14","9/98"},
                                {"0","1","9/49","972/16807","1/2","3/14","9/98"},
                                {"0","1","25/9","-12500/243","1/2","-5/6","25/18"},
                                {"0","1","25/9","12500/243","1/2","5/6","25/18"},
        };
        String[][] actuals = new String[4][7];
        for(int i=0; i<4; i++){
            for (int j = 0; j < 7; j++) {
                actuals[i][j] = monomials[j].evaluate(vals[i]).toString();
            }
            assertArrayEquals(expected[i],actuals[i]);
        }
    }

    @Test
    void derivative() {
        String[] expected = {"0","0","2x","20x^4","0","1/2","x","35/4x^6", "-2x"};
        String[] actuals = new String[9];
        for(int i=0; i<expected.length; i++){
            actuals[i] = monomials[i].derivative().toString();
        }
        assertArrayEquals(expected,actuals);
    }


}