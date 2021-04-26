package PolyMath;

public interface Nomial <T> {

    public T add(T t);
    public T mult(T t);
    public Scalar evaluate(Scalar s);
    public T dierivative();
}
