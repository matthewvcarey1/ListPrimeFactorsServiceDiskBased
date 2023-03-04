package uk.co.example.ListPrimeFactorsService;

import java.io.Serializable;

public class PrimeLimits implements Serializable {
    private String lowerLimit;
    private  String upperLimit;

    private  String error;


    public void setLowerLimit(String limit){
        lowerLimit = limit;
    }
    public void setUpperLimit(String limit){
        upperLimit = limit;
    }

    public void setError(String e){
        error = e;
    }
    public String getUpperLimit(){
        return upperLimit;
    }
    public String getLowerLimit(){
        return lowerLimit;
    }
    public String getError(){
        return error;
    }
}
