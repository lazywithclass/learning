package com.lazywithclass.app;

import java.util.Set;

public class App {

    public static void main( String[] args ) {
        Relation<Integer, String> r = new Relation<>();
        r.put(0, "a");
        r.put(0, "b");
        r.put(0, "c");

        r.put(1, "b");
        r.put(2, "c");

        r.remove(0, "a");

        Set<String> set0 = r.image(0);
        Set<Integer> setb = r.preImage("b");
        System.out.println(set0);
        System.out.println(setb);
    }
}
