package main;


/**
 * 
 * La funzione Phi di Eulero di un numero naturale n e' definita come la quantita'
 * di numeri naturali coprimi con n ed inferiori ad n.
 * 
 * DA WIKIPEDIA:
 * In matematica, la funzione Phi di Eulero o semplicemente funzione di Eulero o toziente, è una funzione definita, per ogni intero positivo n, 
 * come il numero degli interi compresi tra 1 e n che sono coprimi con n. Ad esempio, Phi ( 8 ) = 4 poiché i numeri coprimi di 8 sono quattro: 1, 3, 5, 7.
 * 
 * 
 */

public class Main {

	
	public static void main(String[] args) {
		
		System.out.println(phiDiEulero(999999999));
		
	}
	
	
	
	public static int phiDiEulero(int n) {
		if(n==1) return 1;  // Per def. phi(1) = 1
		int k = 1;   // Esponente
		int p = primeFactors(n); // Fattore primo di n
		n /= p;  
		while(primeFactors(n) == p) {
			k++;
			p = primeFactors(n);
			n /= p;
		}
		
		int phi = (int) ((p - 1) * Math.pow(p, k-1));
		return phi * phiDiEulero(n);
	}
	
	

	/*
	 * https://math.mit.edu/~goemans/18310S15/factoring-notes.pdf
	 */
	public static int primeFactors(int n) {
 
        for (int i = 2; i*i <= n; i+= 2) {
            if (n%i == 0) {
                return i;
            }
        }
 
        if (n > 2)  // Restituisci n se n e' un numero primo maggiore di 2
            return n;
        
        return 0;
        
    }
	
	
	public static int mcd(int a, int b) {
		return b == 0 ? a : mcd(b, a%b);
	}
	
	
	
	
	public static boolean isPrime(int n) {
		if(n == 1) return false;
		for(int i=2; i*i<n; i++) {
			if(n%i == 0) return false;
		}
		return true;
	}
	
	
	
	
	

	
	
	
	
}
