package uk.co.example.ListPrimeFactorsService;


import java.io.Serializable;


public class PrimeLimits implements Serializable {
    private String lowerLimit;
    private  String upperLimit;
    private  String error;

    public void setLowerLimit(String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }

    public void setError(String error){
        this.error = error;
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
