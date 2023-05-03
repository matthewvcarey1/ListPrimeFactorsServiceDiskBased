package uk.co.example.ListPrimeFactorsService;

/**
 * A base and exponent class that knows how to turn itself into a readable string
 */
public class BaseExponent {
    long base;
    long exponent;

    protected BaseExponent(long base, long exponent){
        this.base = base;
        this.exponent = exponent;
    }

    // This array is needed as the superscript digit glyphs are not contiguous in their unicode coding.
    private static final String[] superscriptDigits = new String[] {
            "⁰",
            "¹",
            "²",
            "³",
            "⁴",
            "⁵",
            "⁶",
            "⁷",
            "⁸",
            "⁹", };

    private void appendExponentStringBuffer(long n, StringBuffer s){
        if (n < 1){
            return;
        }
        int val  = (int)n % 10;
        appendExponentStringBuffer(n/10, s);
        // Because we find the least significant digits first we add them to the string result after
        // the recursive call has added the more significant digits.
        s.append(superscriptDigits[val]);
    }

    /**
     * Does what it says on the tin
     * @return a string readable by humans
     */
    public String toString(){
        StringBuffer buff = new StringBuffer();
        buff.append(base);
        if(exponent > 1){
            appendExponentStringBuffer(exponent, buff);
        }
        return buff.toString();
    }

    // For the purpose of testing
    public boolean equals(Object obj){
        BaseExponent other = (BaseExponent) obj;
        return (this.base == other.base
                && this.exponent == other.exponent);
    }

}
