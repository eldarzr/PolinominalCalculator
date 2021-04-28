package PolyMath;

import java.util.*;

public class Polynomial implements Nomial<Polynomial> {
    TreeMap<java.lang.Integer, Monomial> map = new TreeMap<>();

    public Polynomial(TreeMap<java.lang.Integer, Monomial> map) {
        if(map==null)
            throw new NullPointerException("cannot create instance of Polynomial with null map");
        this.map = map;
    }

    public static Polynomial build(String input) {
        if(input ==null || input == "")
            throw new IllegalArgumentException("null/Illegal string");
        int power = 0;
        TreeMap<java.lang.Integer, Monomial> newMap = new TreeMap<>();

        for (String e : input.split(" ")) {
            try {
                //fill values for each string given
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



    public TreeMap<java.lang.Integer, Monomial> getMap() {
        return map;
    }

    @Override
    public Polynomial add (Polynomial p) {
        TreeMap <java.lang.Integer,Monomial> newMap = new TreeMap<>();
        Set<java.lang.Integer> currentSet = map.descendingKeySet();
        Set<java.lang.Integer> givenSet = p.getMap().descendingKeySet();

        for (var  e : currentSet)
        {
            if(p.getMap().containsKey(e))
                newMap.put(e,map.get(e).add(p.getMap().get(e)));
            else
                newMap.put(e,map.get(e));
        }


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
        newMap.put(0,new Monomial(new Integer(0),0));
        Polynomial poly = new Polynomial(newMap);

        for (var  e : currentSet)
             poly = poly.add(mulByMono(p,map.get(e)));

        return poly;
    }

    private Polynomial mulByMono(Polynomial p, Monomial m) {
        TreeMap <java.lang.Integer,Monomial> newMap = new TreeMap<>();
        Monomial moly,molx;
        int expo;
        for (var e : p.getMap().descendingKeySet())
        {
         molx=p.getMap().get(e);
         moly=m.mul(molx);
         expo=moly.getExponent();
         if(newMap.containsKey(expo))
             newMap.put(expo,newMap.get(e).add(moly));
         else
             newMap.put(expo,moly);
        }
        return new Polynomial(newMap);

    }

    @Override
    public Scalar evaluate(Scalar s) {
        Scalar newScalar=new Integer(0);
        for(var e :  map.descendingKeySet())
            newScalar=newScalar.add(map.get(e).evaluate(s));

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

        if(st.length()>1 && st.charAt(0)=='+')
            st=st.substring(1);

        if (st.equals(""))
            return "0";

        return st;
    }
}
