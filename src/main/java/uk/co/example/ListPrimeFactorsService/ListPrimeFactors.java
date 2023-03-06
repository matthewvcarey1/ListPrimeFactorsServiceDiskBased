package uk.co.example.ListPrimeFactorsService;

import java.util.*;

import org.mapdb.DB;
import org.mapdb.DBException;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;
import org.springframework.cache.annotation.Cacheable;


public class ListPrimeFactors {

    public long limit;

    private NavigableSet<Long>  primeSet;
    private static ListPrimeFactors INSTANCE;

    private LRUCache cache;
    public synchronized static ListPrimeFactors getInstance(String path, String limitStr) {
        if (INSTANCE == null) {
            INSTANCE = new ListPrimeFactors(path, limitStr);
        }
        return INSTANCE;
    }


    private DB db;
    private ListPrimeFactors(String path, String limitStr){
        NavigableSet<Long> tmpPrimeSet = null;
        try {
            db = DBMaker.fileDB(path).transactionEnable().make();
            primeSet = db
                    .treeSet("mySet")
                    .serializer(Serializer.LONG)
                    .createOrOpen();
            long last = primeSet.last();
            if(limitStr==null){
                this.limit = limit;
            }else{
                this.limit = Long.parseLong(limitStr);
            }
        }catch(DBException e){
            e.printStackTrace();
            primeSet = null;
        }
        cache =  new LRUCache(100);

    }



    public String ListFactorsString(long num) {
        String cachedResult = cache.get(num);
        if(cachedResult != null) return cachedResult;
        ArrayList<Long> factorsIn = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        ArrayList<Long> factors	= Factors.listFactors(num, primeSet, factorsIn, db);
        long endTime = System.currentTimeMillis();
        ArrayList<BaseExponent> lbe = BuildBaseExponentList.build(factors);
        String result = generateBaseExponentFactorsString(lbe);
        long timeTaken = endTime-startTime;
        if(timeTaken > 1000){
            System.out.println("caching result time taken " + timeTaken + " for " + num);
            cache.put(num, result);
        }
        return result;

    }

    public static String generateBaseExponentFactorsString(ArrayList<BaseExponent> lbe) {
        StringBuilder sb = new StringBuilder();
        for (BaseExponent be : lbe) {
            if(!sb.isEmpty()) {
                sb.append(" × ");
            }
            sb.append(be);
        }
        return sb.toString();
    }

    private long getTopLimit(){
        return this.limit;
    }

    public boolean validate(long decimal){
        final long limit = getTopLimit();
        if(decimal <= 1 || decimal > limit){
            return false;
        }
        return true;
    }
}
