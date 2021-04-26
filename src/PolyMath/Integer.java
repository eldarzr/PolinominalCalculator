package PolyMath;

public class Integer implements Scalar {
    int number;

    public Integer(int number) {
        this.number = number;
    }

    @Override
    public Scalar add(Scalar s) {
       return s.addInteger(this);
    }

    @Override
    public Scalar mul(Scalar s) {
        s.
    }

    @Override
    public Scalar addRational(Rational s) {
        return null;
    }

    @Override
    public Scalar addInteger(Integer s) {
        return new Integer;
    }

    @Override
    public Scalar mulRational(Rational s) {
        return null;
    }

    @Override
    public Scalar mulInteger(Integer s) {
        return null;
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
