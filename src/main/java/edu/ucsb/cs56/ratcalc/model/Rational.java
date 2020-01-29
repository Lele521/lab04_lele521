package edu.ucsb.cs56.ratcalc.model;

/**
 * A class to represent a rational number with a numerator and denominator
 *
 * @author P. Conrad for CS56 F16
 *
 */

public class Rational {

    private int num;
    private int denom;

    /** 
	greatest common divisor of a and b
	@param a first number
	@param b second number
	@return gcd of a and b
    */
    public static int gcd(int a, int b) {
        if (a==0)
            return b;
        else if (b==0)
            return a;
        else
            return gcd(b%a, a);
    }

    // lcm
    public static int lcm(int a, int b) {
        if ((a == 0) && ( b == 0)) {
            return 0;
        }
        int product = a * b;
        product = Math.abs(product);
        int GCD = gcd(a,b);
        return (product / GCD);
    }
    

    // sum
    public static Rational sum(Rational a, Rational b) {
    	
        int denom1 = a.getDenominator();
            int denom2 = b.getDenominator();
        int num1 = a.getNumerator();
        int num2 = b.getNumerator();	
        int LCM = Rational.lcm(denom1,denom2);
        LCM = Math.abs(LCM);
        int numerator = 0;
        numerator = numerator + (num1 * (LCM/denom1));
        numerator = numerator + (num2 * (LCM/denom2));
        return new Rational(numerator,LCM);
    }


    // plus
    public Rational plus (Rational r) {
        int denom1 = r.getDenominator();
        int num1 = r.getNumerator();
        int LCM = Rational.lcm(denom1,this.denom);
        LCM = Math.abs(LCM);
        int numerator = 0;
        numerator = numerator + (num1 * (LCM/denom1));
        numerator = numerator + (this.num * (LCM/this.denom));
        return new Rational(numerator,LCM);
    }


    // difference
     public static Rational difference(Rational a, Rational b) {

        int denom1 = a.getDenominator();
        int denom2 = b.getDenominator();
        int num1 = a.getNumerator();
        int num2 = b.getNumerator();
        int LCM = Rational.lcm(denom1,denom2);
        LCM = Math.abs(LCM);
	    int numerator = 0;
        numerator = numerator + (num1 * (LCM/denom1));
        numerator = numerator - (num2 * (LCM/denom2));
        return new Rational(numerator,LCM);
    }


    // minus
    public Rational minus(Rational r) {
        int denom1 = r.getDenominator();
        int num1 = r.getNumerator();
        int LCM = Rational.lcm(denom1,this.denom);
        int numerator = 0;
	    LCM = Math.abs(LCM);
        numerator = numerator - (num1 * (LCM/denom1));
        numerator = numerator + (this.num * (LCM/this.denom));
        return new Rational(numerator,LCM);
    }


    //reciprocal
    public Rational reciprocalOf() {
    	if (this.num == 0) {
	        throw new ArithmeticException("denominator may not be zero");
	    }
        int num = this.denom;
        int denom = this.num;
        return new Rational(num,denom);
    }


    //divided by
    public Rational dividedBy(Rational r) {
    	Rational r1 = r.reciprocalOf();
        Rational r2 = new Rational(this.num,this.denom);
        return Rational.product(r1,r2);
    }


    //quotient
    public static Rational quotient(Rational a,Rational b) {
    	Rational r1 = b.reciprocalOf();
	    return Rational.product(a,r1);
    }

    public Rational() {
	    this.num = 1;
	    this.denom = 1;
    }

    public Rational(int num, int denom) {
        if (denom== 0) {
            throw new IllegalArgumentException("denominator may not be zero");
        }
        this.num = num;
        this.denom = denom;
        if (num != 0) {
            int gcd = Rational.gcd(num,denom);
            this.num /= gcd;
            this.denom /= gcd;
        }
    }

    public String toString() {
        if (denom == 1 || num == 0)
            return "" + num;
        if (denom < 0 && num > 0)
            return -num + "/" + -denom;
        return num + "/" + denom;
    }

    public int getNumerator() { return this.num; }
    public int getDenominator() { return this.denom; }

    public Rational times(Rational r) {
        return new Rational(this.num * r.num,
                    this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
        return new Rational(a.num * b.num,
                    a.denom * b.denom);
    }

    
    /** 
	For testing getters.  
	@param args unused
     */

    public static void main (String [] args) {
        Rational r = new Rational(5,7);
        System.out.println("r.getNumerator()=" + r.getNumerator());
        System.out.println("r.getDenominator()=" + r.getDenominator());

        int result = Rational.lcm(0,0);
        System.out.println(result);
	
    }
    
}