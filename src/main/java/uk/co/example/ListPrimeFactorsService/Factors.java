package uk.co.example.ListPrimeFactorsService;

import org.mapdb.DB;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.NavigableSet;


public final class Factors {

    private Factors(){}
    static ArrayList<Long> listFactors(long num,
                                       NavigableSet<Long> primeSet, NavigableMap<Long,Long> slowCompositeMap,
                                       ArrayList<Long> results,
                                       DB db,
                                       FactorResultFlags flags) {
        // regular base case
        if (num < 2){
            return results;
        }
        if(primeSet.contains(num)){
            results.add(num);
            return results;
        }
        if (slowCompositeMap.containsKey(num)){
            long prime = slowCompositeMap.get(num);
            results.add(prime);
            return listFactors(num / prime, primeSet, slowCompositeMap, results, db, flags);
        }
        long sqrtNum = (long) Math.sqrt(num);
        for (long prime : primeSet) {
            if (prime > sqrtNum) {
                break;
            }
            if (num % prime == 0) {
                results.add(prime);
                return listFactors(num / prime, primeSet, slowCompositeMap, results, db, flags);
            }
        }
        results.add(num);
        flags.setNewPrime(true);
        // Kick off a thread to store the newly discovered prime number in the database.
        Runnable r = () -> Factors.insertPrime(num, primeSet, db);
        new Thread(r).start();


        return results;
    }


    private static synchronized void insertPrime(long num, NavigableSet<Long> primeSet, DB db) {
        System.out.println("inserting: "+num);
        primeSet.add(num);
        if(db != null) db.commit();
    }

}
