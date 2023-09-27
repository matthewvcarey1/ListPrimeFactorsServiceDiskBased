package uk.co.example.ListPrimeFactorsService;

import java.util.*;

import org.mapdb.*;


public class ListPrimeFactors {

    public long limit;

    private NavigableSet<Long>  primeSet;
    private NavigableMap<Long, Long> slowCompositeMap;
    private static ListPrimeFactors INSTANCE;

    private LRUCache cache;
    public synchronized static ListPrimeFactors getInstance(String path, String limitStr, int cacheSize, int cacheableMilliseconds, TreeSet<Long> mockSet, TreeMap<Long,Long> mockMap) {
        if (INSTANCE == null) {
            INSTANCE = new ListPrimeFactors(path, limitStr, cacheSize, cacheableMilliseconds, mockSet, mockMap);
        }
        return INSTANCE;
    }

    protected synchronized static Boolean removeInstance() throws Exception{
        if (INSTANCE != null) {
            if(INSTANCE.db != null)
                INSTANCE.db.close();
            INSTANCE = null;
        }
        return true;
    }
    //@Value("${cacheSize}")
    private int cacheSize=1000;

    //@Value("${cacheableMilliseconds")
    private int cacheableMilliseconds=1000;
    private DB db;
    private ListPrimeFactors(String path, String limitStr, int cacheSize, int cacheableMilliseconds, TreeSet<Long> mockSet, TreeMap<Long, Long> mockMap){
        NavigableSet<Long> tmpPrimeSet = null;

        try {
            if(path == null) {
                db = null;
                primeSet = mockSet;
                slowCompositeMap = mockMap;
            }
            else {
                db = DBMaker.fileDB(path).transactionEnable().make();
                primeSet = db
                        .treeSet("mySet")
                        .serializer(Serializer.LONG)
                        .createOrOpen();
                slowCompositeMap = db.treeMap("compositeMap")
                        .keySerializer(Serializer.LONG)
                        .valueSerializer(Serializer.LONG)
                        .createOrOpen();
            }
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
        this.cacheSize = cacheSize;
        this.cacheableMilliseconds = cacheableMilliseconds;
        cache =  new LRUCache(cacheSize);
    }



    public String ListFactorsString(long num) {
        //String cachedResult = cache.get(num);
        //if(cachedResult != null) return cachedResult;
        ArrayList<Long> factorsIn = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        FactorResultFlags flags = new FactorResultFlags();
        List<Long> factors	= Factors.listFactors(num, primeSet, slowCompositeMap, factorsIn, db, flags);
        long endTime = System.currentTimeMillis();
        List<BaseExponent> lbe = BuildBaseExponentList.build(factors);
        String result = generateBaseExponentFactorsString(lbe);
        long timeTaken = endTime-startTime;
        if(timeTaken > cacheableMilliseconds){
            if (flags.getNewPrime() == false){
                int size = factors.size();
                long smallFactor = factors.get(size - 2);
                long bigFactor =  factors.get(size - 1);
                long key = smallFactor * bigFactor;
                Runnable r = () -> ListPrimeFactors.insertScm(key, smallFactor, slowCompositeMap, db);
                new Thread(r).start();
            }
            System.out.println("result time taken " + timeTaken + " for " + num);
            //cache.put(num, result);
        }
        return result;

    }

    public static String generateBaseExponentFactorsString(List<BaseExponent> lbe) {
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


    private static synchronized void insertScm(long key, long value, NavigableMap<Long,Long> scm, DB db) {
        System.out.println("inserting key: " + key + " value: "+ value);
        scm.put(key,value);
        if(db != null) db.commit();
    }
}
