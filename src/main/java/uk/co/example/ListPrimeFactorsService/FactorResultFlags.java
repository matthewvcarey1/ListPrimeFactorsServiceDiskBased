package uk.co.example.ListPrimeFactorsService;

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