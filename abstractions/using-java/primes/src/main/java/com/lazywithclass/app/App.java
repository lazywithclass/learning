package com.lazywithclass.app;

public class App {

    public static void main(String[] args) {
        for (Long i : Primes.iterable) {
            if (i > 20) break;
            System.out.println(i);
        }
    }
}
