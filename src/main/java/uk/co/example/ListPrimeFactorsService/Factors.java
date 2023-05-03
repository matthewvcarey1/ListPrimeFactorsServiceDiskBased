package uk.co.example.ListPrimeFactorsService;

import org.mapdb.DB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NavigableSet;


public final class Factors {

    private Factors(){}
    protected static ArrayList<Long> listFactors(long num, NavigableSet<Long> primeSet, ArrayList<Long> results, DB db) {
        // regular base case
        if (num < 2){
            return results;
        }
        if(primeSet.contains(num)){
            results.add(num);
            return results;
        }
        long sqrtNum = (long) Math.sqrt(num);
        Iterator<Long> pit = primeSet.iterator();
        while(pit.hasNext()){
            long prime = pit.next();
            if (prime > sqrtNum){
                break;
            }
            if (num % prime == 0) {
                results.add(prime);
                return listFactors(num / prime, primeSet, results, db);
            }
        }
        results.add(num);
        // Kick off a thread to store the newly discovered prime number in the database.
        Runnable r = new Runnable() {
            public void run() {
                Factors.insertPrime(num, primeSet, db);
            }
        };
        new Thread(r).start();


        return results;
    }


    private static synchronized void insertPrime(long num, NavigableSet<Long> primeSet, DB db) {
        System.out.println("inserting: "+num);
        primeSet.add(num);
        if(db != null) db.commit();
    }

}
