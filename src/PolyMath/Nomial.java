package PolyMath;

public interface Nomial <T> {

    public T add(T t);
    public T mul(T t);
    public Scalar evaluate(Scalar s);
    public T derivative();
}
