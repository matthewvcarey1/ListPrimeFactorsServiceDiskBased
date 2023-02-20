package uk.co.example.ListPrimeFactorsService;

import java.util.*;

import org.mapdb.DB;
import org.mapdb.DBException;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

public class ListPrimeFactors {

    public long limit;

    private NavigableSet<Long>  primeSet;
    private static ListPrimeFactors INSTANCE;

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

    }
    public String ListFactorsString(long num) {
        ArrayList<Long> factorsIn = new ArrayList<>();
        ArrayList<Long> factors	= Factors.listFactors(num, primeSet, factorsIn, db);
        ArrayList<BaseExponent> lbe = BuildBaseExponentList.build(factors);
        return generateBaseExponentFactorsString(lbe);
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
