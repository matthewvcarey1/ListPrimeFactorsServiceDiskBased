package uk.co.example.ListPrimeFactorsService;

public class PrimeLimits {
    private final String lowerLimit;
    private final String upperLimit;

    private final String error;

    public PrimeLimits(String lower, String upper, String error){
        lowerLimit = lower;
        upperLimit = upper;
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
