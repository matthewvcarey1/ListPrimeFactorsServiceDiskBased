package uk.co.example.ListPrimeFactorsService;

/**
 * Mutable boolean holder for flag
 */
public class FactorResultFlags {
    private boolean newPrime;

    public FactorResultFlags() {
        this.newPrime = false;
    }

    public void setNewPrime(boolean flag){
        this.newPrime = flag;
    }

    public boolean getNewPrime(){
         return this.newPrime;
    }
}