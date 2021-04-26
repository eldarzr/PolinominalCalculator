package PolyMath;

public class Rational implements Scalar{

    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator){
        if(denominator == 0)
            throw new IllegalArgumentException("denominator can't be zero");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    private int gcd (int n, int m){
        int r = m;
        while(r>1 & n%m != 0){
            r = n%m;
            n = m;
            m = r;
        }
        return r;
    }

    public Rational reduce(){
        int g = gcd(denominator, numerator);
        Rational rational = new Rational(numerator/g, denominator/g);
        return rational;
    }

    @Override
    public Scalar add(Scalar s) {
        return null;
    }

    @Override
    public Scalar mul(Scalar s) {
        return null;
    }

    @Override
    public Scalar addRational(Rational s) {
        int newNumerator1 = numerator * s.denominator;
        int newNumerator2 = denominator * s.numerator;
        int newDenominator = denominator * denominator;
        Rational rational = new Rational(newNumerator1+newNumerator2, newDenominator);
        return rational.reduce();
    }

    @Override
    public Scalar addInteger(Integer s) {
        int newNumerator = numerator * s;
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
        int newNumerator = numerator * s;
        Rational rational = new Rational(newNumerator, denominator);
        return rational.reduce();
    }

    @Override
    public Scalar power(int exponent) {
        return null;
    }

    @Override
    public int sign() {
        return 0;
    }

    @Override
    public Scalar neg() {
        return null;
    }
}
