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

    /**
     *
     * @param numerator
     * @param denominator cant be zero.
     */
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

    /**
     * @param n
     * @param m
     * @return the gcd of tow numbers.
     */
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

    /**
     *
     * @return the reduced form of the rational number.
     */
    public Rational reduce(){
        if(numerator == 0)
            return  new Rational(0,1);
        int g = gcd(numerator, denominator);
        Rational rational = new Rational(numerator/g, denominator/g);
        return rational;
    }

    /**
     *
     * @param s
     * @return new scalar that is the result of me + s
     */
    @Override
    public Scalar add(Scalar s) {
        if(s == null)
            throw new IllegalArgumentException("scalar cant be null");
        return s.addRational(this);
    }

    /**
     *
     * @param s
     * @return new scalar that is the result of me * s
     */
    @Override
    public Scalar mul(Scalar s) {
        if(s == null)
            throw new IllegalArgumentException("scalar cant be null");
        return s.mulRational(this);
    }

    /**
     *
     * @param s
     * @return new scalar that is the result of me + s
     */
    @Override
    public Scalar addRational(Rational s) {
        if(s == null)
            throw new IllegalArgumentException("scalar cant be null");
        int newNumerator1 = numerator * s.denominator;
        int newNumerator2 = denominator * s.numerator;
        int newDenominator = denominator * s.denominator;
        Rational rational = new Rational(newNumerator1+newNumerator2, newDenominator);
        return rational.reduce();
    }

    /**
     *
     * @param s
     * @return new scalar that is the result of me + s
     */
    @Override
    public Scalar addInteger(Integer s) {
        if(s == null)
            throw new IllegalArgumentException("scalar cant be null");
        int newNumerator = numerator + denominator * s.getNumber();
        Rational scalar = new Rational(newNumerator, denominator);
        return scalar.reduce();
    }

    /**
     *
     * @param s
     * @return new scalar that is the result of me * s
     */
    @Override
    public Scalar mulRational(Rational s) {
        if(s == null)
            throw new IllegalArgumentException("scalar cant be null");
        int newNumerator = numerator * s.numerator;
        int newDenominator = denominator * s.denominator;
        Rational rational = new Rational(newNumerator, newDenominator);
        return rational.reduce();
    }

    /**
     *
     * @param s
     * @return new scalar that is the result of me * s
     */
    @Override
    public Scalar mulInteger(Integer s) {
        if(s == null)
            throw new IllegalArgumentException("scalar cant be null");
        int newNumerator = numerator * s.getNumber();
        Rational rational = new Rational(newNumerator, denominator);
        return rational.reduce();
    }

    /**
     *
     * @param exponent
     * @return new scalar that is the result of me ^ exponent
     */
    @Override
    public Scalar power(int exponent) {
        if(numerator == 0) {
            if (exponent == 0)
                return new Integer(1);
            else return new Integer(0);
        }
        if(exponent<0)
            return powerByPositive(denominator, numerator, -exponent);
        return powerByPositive(numerator, denominator, exponent);
    }

    /**
     *
     * @param numerator
     * @param denominator
     * @param exponent
     * @return new scalar that is the result of (numerator/denominator)^exponent.
     */
    private Scalar powerByPositive(int numerator, int denominator, int exponent){
        if(exponent > 30)
            if(Math.abs(numerator/denominator) > 2 |
                Math.abs(numerator/denominator) == 2 & exponent > 31)
            throw new IllegalArgumentException("exponent cant be over 31");
        numerator = (int)Math.pow(numerator,exponent);
        denominator = (int)Math.pow(denominator,exponent);
        return new Rational(numerator,denominator).reduce();
    }

    /**
     *
     * @return the sign of me:
     * 1 if me>0
     * 0 if me = 0
     * -1 otherwise.
     */
    @Override
    public int sign() {
        if(numerator == 0)
            return 0;
        if(numerator * denominator >0)
            return 1;
        return -1;
    }

    /**
     *
     * @return new scalar that is the result of me * -1
     */
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
