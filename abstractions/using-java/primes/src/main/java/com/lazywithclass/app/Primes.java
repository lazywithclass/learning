package com.lazywithclass.app;

import java.util.Iterator;

class Primes {

    // disallow instantiation
    private Primes() {}

    public static Iterable<Long> iterable = new Iterable<Long>() {

        @Override
        public Iterator<Long> iterator() {

            return new Iterator<Long>() {
                private long n = 0;

                @Override
                public Long next() {
                    do {
                        n++;
                    } while (!isPrime(n));
                    return n;
                }

                @Override
                public boolean hasNext() {
                    return true;
                } 
            };
        };
    };

    public static boolean isPrime(Long n) {
        for (Long i = 2l; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
