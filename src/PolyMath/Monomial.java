package PolyMath;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Monomial implements Nomial<Monomial> {

    private int exponent;
    private Scalar coefficient;


    public Monomial(Scalar coefficient, int exponent){
        if(exponent < 0)
            throw new IllegalArgumentException("exponent can't be negative");
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    @Override
    public Monomial add(Monomial m) {
        if(m.exponent != exponent)
            return null;
        return new Monomial(coefficient.add(m.coefficient), exponent);
    }

    @Override
    public Monomial mul(Monomial m) {
        int newExp = exponent+m.exponent;
        Scalar coef = coefficient.mul(m.coefficient);
        return new Monomial(coef, newExp);
    }

    @Override
    public Scalar evaluate(Scalar s) {
        Scalar s1 = s.power(exponent);
        s1 = s1.mul(coefficient);
        return s1;
    }

    @Override
    public Monomial derivative() {
        if(exponent == 0)
            return new Monomial(new Integer(0),0);
        Monomial m = new Monomial(coefficient.mul(new Integer(exponent)), exponent-1);
        return m;
    }

    public int sign(){
        return coefficient.sign();
    }

    public int getExponent() {
        return exponent;
    }

    public Scalar getCoefficient() {
        return coefficient;
    }

    public String toString(){
        String s = coefficient.toString();
        if(exponent == 0 | coefficient.sign() == 0)
            return s;
        if(coefficient.power(2).toString().equals("1")) {
            s = "";
            if (coefficient.sign() < 0)
                s = "-";
        }
        s = s + "x";
        if(exponent != 1)
            s = s + "^" + exponent;
        return s;

    }
}
