package main;
import java.util.*;


public class InputDati {
	
	  private static final String MESSAGGIO_CARATTERE_AMMISSIBILE = "Attenzione! Il carattere non e' ammissibile";

	  private static Scanner lettore = creaScanner();
	  
	  private final static String ERRORE_FORMATO = "Attenzione! il dato inserito non e' nel formato corretto";
	  private final static String ERRORE_MINIMO= "Attenzione! e' richiesto un valore maggiore o uguale a ";
	  private final static String ERRORE_STRINGA_VUOTA= "Attenzione! non hai inserito alcun carattere";
	  private final static String ERRORE_MASSIMO= "Attenzione! e' richiesto un valore minore o uguale a ";
	  private final static String MESSAGGIO_AMMISSIBILI= "Attenzione! i caratteri ammissibili sono: ";

	  private final static char RISPOSTA_SI='S';
	  private final static char RISPOSTA_NO='N';

	  
 
	  private static Scanner creaScanner ()
	  {
	   Scanner creato = new Scanner(System.in);
	   creato.useDelimiter(System.getProperty("line.separator"));
	   return creato;
	  }
	  
	  
	  /*****/
	  
	  public static String leggiStringa (String messaggio)
	  {
		  System.out.print(messaggio);
		  return lettore.next();
	  }
	  
	  /*****/
	  
	  /*****/
	  
	  public static String leggiStringaNonVuota(String messaggio)
	  {
	   boolean finito=false;
	   String lettura = null;
	   do
	   {
		 lettura = leggiStringa(messaggio);
		 lettura = lettura.trim();
		 if (lettura.length() > 0)
		  finito=true;
		 else
		  System.out.println(ERRORE_STRINGA_VUOTA);
	   } while (!finito);
	   
	   return lettura;
	  }
	  
	  /*****/
	  
	  /*****/
	  
	  public static char leggiChar (String messaggio)
	  {
	   boolean finito = false;
	   char valoreLetto = '\0';
	   do
	    {
	     System.out.print(messaggio);
	     String lettura = lettore.next();
	     if (lettura.length() > 0)
	      {
	       valoreLetto = lettura.charAt(0);
	       finito = true;
	      }
	     else
	      {
	       System.out.println(ERRORE_STRINGA_VUOTA);
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	  
	  /*****/
	  
	  /*****/
	  
	  public static char leggiUpperChar (String messaggio, String ammissibili)
	  {
	   boolean finito = false;
	   char valoreLetto = '\0';
	   do
	   {
	    valoreLetto = leggiChar(messaggio);
	    valoreLetto = Character.toUpperCase(valoreLetto);
	    if (ammissibili.indexOf(valoreLetto) != -1)
		 finito  = true;
	    else
	     System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
	   } while (!finito);
	   return valoreLetto;
	  }
	  
	  /*****/
	  
	  /*****/
	  
	  public static char leggiCharCheck (String messaggio, char ... chars) {     // Leggi un carattere e controlla se e'ammissibile
		 
		  if(chars.length == 0)					// Se non vengono passati argomenti oltre al msg da stampare, leggi un char normalmente
			 return leggiChar(messaggio);
		 
		  boolean finito = false;
		  char valoreLetto = '\0';
		  do {
			 
			  valoreLetto = leggiChar(messaggio);				// Leggi un char e controlla se e' fra i char ammissibili
			  valoreLetto = Character.toUpperCase(valoreLetto);
			 
			  for(int i=0; i<chars.length; i++) {
				  if(valoreLetto == chars[i]) {
					  finito = true;
					  break;
				 }
			  }
			 
			  if(!finito)													// Se non e' stato letto un char ammissibile, ripeti la lettura
				  System.out.println(MESSAGGIO_CARATTERE_AMMISSIBILE);
			 	 
		  } while(!finito);
		  
		  return valoreLetto;
		 
	  }
	 
	  /*****/
	  
	  
	  /*****/
	  
	  public static int leggiIntero (String messaggio)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     System.out.print(messaggio);
	     try
	      {
	       valoreLetto = lettore.nextInt();
	       finito = true;
	      }
	     catch (InputMismatchException e)
	      {
	       System.out.println(ERRORE_FORMATO);
	       @SuppressWarnings("unused")
	       String daButtare = lettore.next();
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	  
	  /*****/
	  
	  /*****/

	  public static int leggiInteroPositivo(String messaggio)
	  {
		  return leggiInteroConMinimo(messaggio,1);
	  }
	  
	  /*****/
	  
	  /*****/
	  
	  public static int leggiInteroNonNegativo(String messaggio)
	  {
		  return leggiInteroConMinimo(messaggio,0);
	  }
	  
	  /*****/
	  
	  /*****/
	  
	  public static int leggiInteroConMinimo(String messaggio, int minimo)
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiIntero(messaggio);
	     if (valoreLetto >= minimo)
	      finito = true;
	     else
	      System.out.println(ERRORE_MINIMO + minimo);
	    } while (!finito);
	     
	   return valoreLetto;
	  }
	  
	  /*****/
	  
	  /*****/

	  public static int leggiInteroRange(String messaggio, int minimo, int massimo)   // Leggi un numeri intero compreso fra un minimo e un massimo
	  {
	   boolean finito = false;
	   int valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiIntero(messaggio);
	     if (valoreLetto >= minimo && valoreLetto<= massimo)
	      finito = true;
	     else
	      if (valoreLetto < minimo)
	         System.out.println(ERRORE_MINIMO + minimo);
	      else
	    	 System.out.println(ERRORE_MASSIMO + massimo); 
	    } while (!finito);
	     
	   return valoreLetto;
	  }
	  
	  /*****/

	  /*****/
	  
	  public static double leggiDouble (String messaggio)
	  {
	   boolean finito = false;
	   double valoreLetto = 0;
	   do
	    {
	     System.out.print(messaggio);
	     try
	      {
	       valoreLetto = lettore.nextDouble();
	       finito = true;
	      }
	     catch (InputMismatchException e)
	      {
	       System.out.println(ERRORE_FORMATO);
	       @SuppressWarnings("unused")
	       String daButtare = lettore.next();
	      }
	    } while (!finito);
	   return valoreLetto;
	  }
	  
	  /*****/
	  
	  /*****/
	 
	  public static double leggiDoubleConMinimo (String messaggio, double minimo)
	  {
	   boolean finito = false;
	   double valoreLetto = 0;
	   do
	    {
	     valoreLetto = leggiDouble(messaggio);
	     if (valoreLetto >= minimo)
	      finito = true;
	     else
	      System.out.println(ERRORE_MINIMO + minimo);
	    } while (!finito);
	     
	   return valoreLetto;
	  }
	  
	  /*****/
	  
	  /*****/

	  public static boolean yesOrNo(String messaggio)
	  {
		  String mioMessaggio = messaggio + "("+RISPOSTA_SI+"/"+RISPOSTA_NO+")";
		  char valoreLetto = leggiUpperChar(mioMessaggio, String.valueOf(RISPOSTA_SI)+String.valueOf(RISPOSTA_NO));
		  
		  if (valoreLetto == RISPOSTA_SI)
			return true;
		  else
			return false;
	  }
	  
	  /*****/

}

	
