package PolyMath;

import java.util.*;

public class Polynomial implements Nomial<Polynomial> {
    TreeMap<java.lang.Integer, Monomial> map = new TreeMap<>();

    public static Polynomial build(String input) {
        int power = 0;
        TreeMap<java.lang.Integer, Monomial> newMap = new TreeMap<>();
        for (var e : input.split(" ")) {
            try {
                fillMap(newMap, e, power);
                power++;
            }
            catch (Exception exception) {
                throw new IllegalArgumentException("Invalid String");
            }
        }

        return new Polynomial(newMap);
    }

    private static void fillMap(TreeMap<java.lang.Integer, Monomial> newMap, String e, int power) {
        int a, b;
        String[] ratio = new String[1];
        if (e.contains("/")) {
            ratio = e.split("/");
            a = java.lang.Integer.parseInt(ratio[0]);
            b = java.lang.Integer.parseInt(ratio[1]);
            newMap.put(power, new Monomial(new Rational(a, b), power));
        } else {
            a = java.lang.Integer.parseInt(e);
            if (a != 0)
                newMap.put(power, new Monomial(new Integer(a), power));
        }
    }


    public Polynomial(TreeMap<java.lang.Integer, Monomial> map) {
        this.map = map;
    }

    public TreeMap<java.lang.Integer, Monomial> getMap() {
        return map;
    }

    @Override
    public Polynomial add (Polynomial p) {
        TreeMap <java.lang.Integer,Monomial> newMap = new TreeMap<>();
        Set<java.lang.Integer> currentSet = map.descendingKeySet();
        Set<java.lang.Integer> givenSet = map.descendingKeySet();

        for (var  e : currentSet)
        {
            if(p.getMap().containsKey(e))
                newMap.put(e,map.get(e).add(p.getMap().get(e)));
            else
                newMap.put(e,map.get(e));
        }

    //    givenSet.removeAll(currentSet);
        for (var  e : givenSet) {
            if(!newMap.containsKey(e))
            newMap.put(e, p.getMap().get(e));
        }

      return new Polynomial(newMap);
    }

    @Override
    public Polynomial mul(Polynomial p) {
        TreeMap <java.lang.Integer,Monomial> newMap = new TreeMap<>();
        Set<java.lang.Integer> currentSet = map.descendingKeySet();
        Set<java.lang.Integer> givenSet = map.descendingKeySet();

        for (var  e : currentSet)
        {
            if(p.getMap().containsKey(e))
                newMap.put(e,map.get(e).mul(p.getMap().get(e)));
            else
                newMap.put(e,map.get(e));
        }

        givenSet.removeAll(currentSet);
        for (var  e : givenSet)
            newMap.put(e,p.getMap().get(e));

        return new Polynomial(newMap);
    }

    @Override
    public Scalar evaluate(Scalar s) {
        Scalar newScalar=new Integer(0);
        for(var e :  map.descendingKeySet())
            newScalar=newScalar.add(s.add(map.get(e).evaluate(s)));

        return newScalar;
    }

    @Override
    public Polynomial derivative() {
        TreeMap <java.lang.Integer,Monomial> newMapping = new TreeMap<>();
        Scalar newScalar=new Integer(0);
        for(var e : map.descendingKeySet()) {
            if(e!=0)
            newMapping.put(e-1, map.get(e).derivative());
        }

        return new Polynomial(newMapping);
    }

    @Override
    public String toString() {
        String st="";

        for(var e : map.descendingKeySet())
        {
            String current = map.get(e).toString();
            if (!current.equals("0")) {
                if (current.charAt(0) == '-')
                    st = current + st;
                else
                    st = "+" + current + st;
            }
        }
        if (st.equals("0"))
            return "0";

        if(st.length()>1)
            st=st.substring(1);

        return st;
    }
}
