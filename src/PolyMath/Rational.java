package PolyMath;

public class Rational implements Scalar{

    private int numerator;
    private int denominator;

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public Rational(int numerator, int denominator){
        if(denominator == 0)
            throw new IllegalArgumentException("denominator can't be zero");
        if(numerator == 0)
            this.denominator = 1;
        else {
            int g = gcd(numerator, denominator);
            this.numerator = numerator / g;
            this.denominator = denominator / g;
        }
    }

    private int gcd (int n, int m){
        if(n*m == 0 | n*m == 1)
            return 1;
        m = Math.abs(m); n = Math.abs(n);
        int max = Math.max(m,n), min = Math.min(m,n);
        int r = min;
        while(min>1 & max>1 & max%min != 0){
            r = max%min;
            max = min;
            min = r;
        }
        return r;
    }

    public Rational reduce(){
        if(numerator == 0)
            return  new Rational(0,1);
        int g = gcd(numerator, denominator);
        Rational rational = new Rational(numerator/g, denominator/g);
        return rational;
    }

    @Override
    public Scalar add(Scalar s) {
        return s.addRational(this);
    }

    @Override
    public Scalar mul(Scalar s) {
        return s.mulRational(this);
    }

    @Override
    public Scalar addRational(Rational s) {
        int newNumerator1 = numerator * s.denominator;
        int newNumerator2 = denominator * s.numerator;
        int newDenominator = denominator * s.denominator;
        Rational rational = new Rational(newNumerator1+newNumerator2, newDenominator);
        return rational.reduce();
    }

    @Override
    public Scalar addInteger(Integer s) {
        int newNumerator = numerator + denominator * s.getNumber();
        Rational scalar = new Rational(newNumerator, denominator);
        return scalar.reduce();
    }

    @Override
    public Scalar mulRational(Rational s) {
        int newNumerator = numerator * s.numerator;
        int newDenominator = denominator * s.denominator;
        Rational rational = new Rational(newNumerator, newDenominator);
        return rational.reduce();
    }

    @Override
    public Scalar mulInteger(Integer s) {
        int newNumerator = numerator * s.getNumber();
        Rational rational = new Rational(newNumerator, denominator);
        return rational.reduce();
    }

    @Override
    public Scalar power(int exponent) {
        if(numerator == 0) {
            if (exponent == 0)
                return new Integer(1);
            else return new Integer(0);
        }
        int newNumerator = numerator;
        int newDenominator = denominator;
        if(exponent<0) {
            exponent = -exponent;
            newNumerator = denominator;
            newDenominator = numerator;
        }
        newNumerator = (int)Math.pow(newNumerator, exponent);
        newDenominator = (int)Math.pow(newDenominator, exponent);
        Rational rational = new Rational(newNumerator, newDenominator);
        return rational.reduce();
    }

    @Override
    public int sign() {
        if(numerator == 0)
            return 0;
        if(numerator * denominator >0)
            return 1;
        return -1;
    }

    @Override
    public Scalar neg() {
        Rational rational = new Rational(numerator, -1* denominator);
        return rational.reduce();
    }

    public String toString(){
        if(numerator == 0)
            return "0";
        String s = sign() * Math.abs(numerator) +"";
        if(Math.abs(denominator) != 1)
            s+= "/" + Math.abs(denominator);
        return s;
    }
}
