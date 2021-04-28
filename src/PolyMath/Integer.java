package PolyMath;

public class Integer implements Scalar {
    private int number;


    public Integer(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public Scalar add(Scalar s) {
        return s.addInteger(this);
    }

    @Override
    public Scalar mul(Scalar s) {
        return s.mulInteger(this);
    }

    @Override
    public Scalar addRational(Rational s) {
        return s.addInteger(this);
    }

    @Override
    public Scalar addInteger(Integer s) {

        return new Integer(s.getNumber()+number);
    }

    @Override
    public Scalar mulRational(Rational s) {
        return s.mulInteger(this);
    }

    @Override
    public Scalar mulInteger(Integer s) {
        return new Integer(s.getNumber()*number);

    }

    @Override
    public Scalar power(int exponent) {
        if(exponent<0)
            return new Rational(number,1).power(exponent);
        if((number>2 || number <-2) && exponent>31)
            throw  new IllegalArgumentException("the excepted number powered by this exponent cannot be represented in Integer");
        return new Integer((int)Math.pow(number,exponent));
    }

    @Override
    public int sign() {
        if(number<0)
            return -1;
        if (number>0)
            return 1;
        return 0;
    }

    @Override
    public Scalar neg() {
        return new Integer(number*-1);
    }

    @Override
    public String toString() {
        return ""+number;
    }
}
