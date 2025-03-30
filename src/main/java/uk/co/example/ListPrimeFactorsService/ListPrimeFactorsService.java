package uk.co.example.ListPrimeFactorsService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;




@Service
public class ListPrimeFactorsService implements Serializable {
    private static final String PARSE_ERROR_MESSAGE = "The input value could not be parsed as a Long Integer";
    @Value("${primesDbPath}")
    private String primesDbPath;

    @Value("${upperLimitString}")
    private String upperLimitString;

    private long limit = 0;
    protected ListPrimeFactors lpf;

    public void setPrimeDBPath(String path){
        primesDbPath = path;
    }

    public void setUpperLimitString(String limitString){
        upperLimitString = limitString;
    }

    public void setLimit(long l){
        limit = l;
    }

    public void setLpf(ListPrimeFactors listPrimeFactors){
        lpf = listPrimeFactors;
    }

    public long getLimit(){
        return limit;
    }

    public String getUpperLimitString() {
        return upperLimitString;
    }

    public String getPrimesDbPath(){
        return primesDbPath;
    }

    public int cacheSize = 100;
    public int cacheableMillisecs = 1000;

    public void setCacheSize(int sz){
        cacheSize = sz;
    }

    public int getCacheSize(){
        return cacheSize;
    }

    public void setCacheableMillisecs(int cms){
        cacheableMillisecs = cms;
    }

    public int getCacheableMillisecs(){
        return cacheableMillisecs;
    }

    public ListFactorsResult list(String num) {
        try {
            if(this.lpf == null) this.lpf = ListPrimeFactors.getInstance(primesDbPath, upperLimitString,cacheSize, cacheableMillisecs, null,  null);
            if(limit == 0) limit =  Long.parseLong(upperLimitString);

            long value = Long.parseLong(num);

            if (!lpf.validate(value)) {
                return new ListFactorsResult("Value not in range  2 - " + this.limit, "", "");
            }
            return new ListFactorsResult("", lpf.ListFactorsString(value), Long.toString(value));
        } catch (NumberFormatException e) {
            return new ListFactorsResult(PARSE_ERROR_MESSAGE, "", "");
        }
    }

    public PrimeLimits limits() {
        PrimeLimits limits = new PrimeLimits();
        try {
            if(lpf == null) lpf = ListPrimeFactors.getInstance(primesDbPath, upperLimitString, cacheSize, cacheableMillisecs, null, null);
            if(limit == 0) limit =  Long.parseLong(upperLimitString);
            final long upperLimit = this.limit;
            final long lowerLimit = 2L;
            limits.setLowerLimit(Long.valueOf(lowerLimit).toString());
            limits.setUpperLimit(Long.valueOf(upperLimit).toString());
            limits.setError("");
        } catch (Exception e) {
            limits.setLowerLimit("");
            limits.setUpperLimit("");
            limits.setError(e.getMessage());
        }
        return limits;
    }
}