/**********************
 * PrimeFinder.java
 * 
 * Implementation of two separate algorithms to find primes
 * up to N
 *
 * Important: Please read the inline comments to understand
 * what this program is doing and how it works, and where
 * you should add code
 *Base by professor and algorithm written by Nyla Worker
 ***********************/
public class PrimeFinder {

  /*****
   * The main method. You should really only mess with 
   * this to change N or switch which algorithm is being run, or set whether
   * output is turned on or off.
   *****/
  public static void main(String[] args) {
    // This is the N that is the max of the numbers that we're looking for
    int N = 1000;

    // This is the boolean that controls which algorithm is run,
    // iterative = true for the iterative version,
    // iterative = false for the sieve
    boolean iterative = false;

    // This is the boolean that controls whether output is on.
    // You may want to turn off output when you have large N
    boolean output = true;

    // The rest of the code in main you should not mess with
    PrimeFinder pf = new PrimeFinder();
    
    boolean[] result = null;
    if(iterative == true) {
      result = pf.iterative(N);
    } else {
      result = pf.sieve(N);
    }

    if(output) {
      System.out.println("Prime Numbers:");
      for(int i = 0; i < N; i++) {
        if(result[i] == true) {
          System.out.print("" + i + ", ");
        }
      }
      System.out.println();
    }
  }

  /***********
   * This is the iterative version of the prime finding
   * method. It should call the isPrime method.
   * The output should be the array of booleans, with each 
   * index set to true if the index is a prime, false if it is not
   *****************/
  public boolean[] iterative(int max) {
    // setup primes as an array of booleans
    boolean[] primes = new boolean[max];
    
    for (int i=2; i<max; i++){
      primes[i]=isPrime(i);//sets the index of the array to the condition of isPrime at i that starts from 0.
    }


    return primes;
  }

  /***********
   * This is an (admittedly) inefficient version of isPrime
   * it could be slightly better
   ***********/
  public boolean isPrime(int n) {
    for(int i = 2; i < n; i++) {
      if(n % i == 0) {
        return false;
      }
    }
    return true;
  }

  /*************
   * Eratosthenes' Sieve prime finder method.
   * The output should be the array of booleans, with each 
   * index set to true if the index is a prime, false if it is not
   ************/
  public boolean[] sieve(int max) {
    // setup primes as an array of booleans, then initalize 2..(max-1) to true,
    // since the Sieve algorithm starts with everything "in" the list of primes
    boolean[] primes = new boolean[max];
    for(int i = 2; i < max; i++) { primes[i] = true; }
    
    for(int i = 2; i < max; i++){
      if (primes[i]){//if the number hasn't been cross off.
        for(int l= 2*i;l<max;l+=i){//creates a loop that will get rid of all the multiples of i for the rest of the numbers
          primes[l]=false;//shows that it is a prime
        }
      }
    }

    /* INSERT YOUR CODE HERE */

    return primes;
  }
}
